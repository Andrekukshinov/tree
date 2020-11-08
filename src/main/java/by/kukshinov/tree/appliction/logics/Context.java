package by.kukshinov.tree.appliction.logics;

import java.util.ArrayDeque;

public class Context {
    private final ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    public Integer popValue() {
	   return contextValues.pop();
    }

    public void pushValue(Integer value) {
	   contextValues.push(value);
    }
}
// TODO: 07.11.2020 create test
