package com.li64.tide.data;

public interface FreezableMob {
    int FROZEN_BIT = 16;

    boolean tide$isFrozen();

    void tide$setFrozen(boolean frozen);
}
