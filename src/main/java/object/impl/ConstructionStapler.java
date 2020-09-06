package object.impl;

import object.Stapler;

public class ConstructionStapler implements Stapler {

    private int staples;

    public ConstructionStapler(int staples) {
        this.staples = staples;
    }

    @Override
    public void pin() {
        //pins to/together on harder construction materials
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
