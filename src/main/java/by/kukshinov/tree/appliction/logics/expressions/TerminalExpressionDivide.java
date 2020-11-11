package by.kukshinov.tree.appliction.logics.expressions;

import by.kukshinov.tree.appliction.logics.Context;

public class TerminalExpressionDivide implements AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        Integer firstOperand = c.popValue();
        Integer secondOperand = c.popValue();
        c.pushValue((firstOperand / secondOperand));
    }
}
