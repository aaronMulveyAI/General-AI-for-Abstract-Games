package org.example.app.agents.mcts;

import org.example.app.agents.decision.State;

import java.util.ArrayList;
import java.util.List;

public class Node {

    List<Node> children;
    Node parent;
    State state;
    Node(Node parent, State state) {
        children = new ArrayList<>();
        this.parent = parent;
        this.state = state;
    }

    void addChild(Node child) {
        children.add(child);
    }

    List<Node> getChildren() {
        return children;
    }

    boolean isLeaf() {
        return state.isTerminal();
    }

    boolean isRoot() {
        return parent == null;
    }


}
