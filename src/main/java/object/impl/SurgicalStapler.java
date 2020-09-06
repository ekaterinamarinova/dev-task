package object.impl;

import object.Stapler;

public class SurgicalStapler implements Stapler {

    private int staples;

    public SurgicalStapler(int staples) {
        this.staples = staples;
    }

    @Override
    public void pin() {
        //pins skin together during surgeries
        --staples;
    }

    @Override
    public void setStaples() {

    }

    @Override
    public int getStaples() {
        return 0;
    }
}
