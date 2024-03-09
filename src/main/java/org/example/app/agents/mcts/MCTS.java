package org.example.app.agents.mcts;

import org.example.app.agents.decision.Action;
import org.example.app.agents.decision.State;

import java.util.Comparator;
import java.util.List;

public class MCTS {
    static final int ITERATIONS = 100;
    static final double C = 1.4;
    Node root;

    public Action search(State state){
        Node root = new Node(null, state);

        for (int i = 0; i < ITERATIONS; i++) {
            Node node = select(root);
            assert node != null;
            if(!node.state.isTerminal()){
                expand(node);
            }
            Node newNode = node.isLeaf() ? node : select(node);
            double value = simulate(newNode);
            backpropagation(newNode, value);
        }

        Node best = root.children.stream()
                .max(Comparator.comparingDouble(n -> uctValue(n, C)))
                .orElseThrow(IllegalStateException::new);

        return best.state.action;
    }

    private Node select(Node node){
        Node current = node;
        while (!current.isLeaf()) {
            Node best = current.children.stream()
                    .max(Comparator.comparingDouble(n -> uctValue(n, C)))
                    .orElse(null);
            if (best == null) break;
            current = best;
        }
        return current;
    }

    private void expand(Node node){
        node.state.getPosibleStates().forEach(state -> {
            Node child = new Node(node, state);
            node.addChild(child);
        });
    }
    private double simulate(Node node){
        State tempState = node.state;
        while (!tempState.isTerminal()){
            List<State> states = tempState.getPosibleStates();
            tempState = tempState.getPosibleStates().get((int) (Math.random() * states.size()));
        }
        return 0;
    }
    private void backpropagation(Node node, double value){
        Node tempNode = node;
        while (tempNode != null) {
            tempNode.state.stats.update(value);
            tempNode = tempNode.parent;
        }
    }

    private double uctValue(Node node, double c) {
        int n = node.state.stats.getVisits();
        double q = node.state.stats.getScore();
        int N = node.parent.state.stats.getVisits();
        return n == 0 ? Integer.MAX_VALUE : (q / n) + c * Math.sqrt((Math.log(N)) / n);
    }
}
