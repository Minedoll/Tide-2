package com.li64.tide.data;

public interface ValidatableDataEntry {
    boolean isValid();

    default String invalidReason() {
        return "no reason provided";
    }
}
