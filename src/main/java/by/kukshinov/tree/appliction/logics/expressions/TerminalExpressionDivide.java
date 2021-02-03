package by.kukshinov.tree.appliction.logics.expressions;

import by.kukshinov.tree.appliction.logics.Context;

public class TerminalExpressionDivide implements AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        Integer firstOperand = context.popValue();
        Integer secondOperand = context.popValue();
        context.pushValue((firstOperand / secondOperand));
    }
}
