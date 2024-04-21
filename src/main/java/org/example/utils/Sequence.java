package org.example.utils;

public class Sequence {

    private int currentValue = 0;

    public int getNext() {
        if (currentValue == Integer.MAX_VALUE) {
            currentValue = 0;
        } else {
            ++currentValue;
        }
        return currentValue;
    }
}
