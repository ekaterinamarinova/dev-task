package object.impl;

import object.Stapler;

public class OfficeStapler implements Stapler {

    private int staples;

    public OfficeStapler(int staples) {
        this.staples = staples;
    }

    @Override
    public void pin() {
        //pins paper together
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
