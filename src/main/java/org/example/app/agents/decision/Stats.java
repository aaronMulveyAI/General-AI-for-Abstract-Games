package org.example.app.agents.decision;

public class Stats {
    private int visits;
    private double score;

    public Stats() {
        this.visits = 0;
        this.score = 0;
    }

    public void update(double score) {
        this.visits++;
        this.score += score;
    }

    public int getVisits() {
        return visits;
    }

    public double getScore() {
        return score;
    }


}
