package by.kukshinov.tree.appliction.logics.expressions;

import by.kukshinov.tree.appliction.logics.Context;

public class NonTerminalExpressionNumber extends AbstractMathExpression {
    private final int number;

    public NonTerminalExpressionNumber(int number) {
	   this.number = number;
    }

    @Override
    public void interpret(Context c) {
	   c.pushValue(number);
    }
}
