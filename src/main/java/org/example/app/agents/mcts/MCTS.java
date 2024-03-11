package org.example.app.agents.mcts;

import org.example.app.agents.decision.Action;
import org.example.app.agents.decision.State;
import org.example.app.controller.players.AbstractPlayer;

import java.util.Comparator;
import java.util.List;

public class MCTS {
    static final int ITERATIONS = 10000;
    static final double C = 1.4;
    Node root;

    public Action search(State state){
        long start = System.currentTimeMillis();
        root = new Node(null, state);

        for (int i = 0; i < ITERATIONS; i++) {
            Node node = select(root);

            if(!node.state.isTerminal()){ // si no es terminal
                expand(node); // expande el nodo
            }
            Node newNode = node.isLeaf() ? node : select(node); // si es terminal devuelve el node
            double value = simulate(newNode); // simula y obtén el valor
            backpropagation(newNode, value); // propaga el valor a todos los nodos
        }


        Node best = root.children.stream()
                .max(Comparator.comparingDouble(n -> uctValue(n, C)))
                .orElse(root.children.get(0));

        long end = System.currentTimeMillis();
        System.out.println(end - start);
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

    private void expand(Node node) {

        node.state.getRandomPosibleStates(5)
                .forEach(state -> {
                    Node child = new Node(node, state);
                    node.addChild(child);
                });
    }

    private double simulate(Node node){
        State tempState = node.state;
        while (!tempState.isTerminal()){
            tempState = tempState.getRandomPosibleStates(1).get(0);
        }

        return tempState.getReward();
    }
    private void backpropagation(Node node, double value){
        Node tempNode = node;
        State tempState = node.state;
        while (tempNode != null) {
            tempNode.state.stats.update(value);
            tempNode = tempNode.parent;
        }
    }

    private double uctValue(Node node, double c) {
        if (node.parent == null) return 0; // Añadido como precaución para el nodo raíz.

        int n = node.state.stats.getVisits();
        double q = node.state.stats.getScore();
        int N = node.parent.state.stats.getVisits();

        if (n == 0) return Integer.MAX_VALUE;

        double avgReward = q / n;
        double variance = node.state.stats.getVariance() + Math.sqrt((2 * Math.log(N)) / n); // Asume que tienes un método para calcular la varianza.
        double minVariance = Math.min(0.25, variance); // Limita la varianza para asegurar la estabilidad numérica.

        return avgReward + c * Math.sqrt(Math.log(N) * minVariance / n);
    }

}
