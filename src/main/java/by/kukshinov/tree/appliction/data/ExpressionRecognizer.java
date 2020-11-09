package by.kukshinov.tree.appliction.data;

public class ExpressionRecognizer {
    private static final String EXPRESSION_PREFIX = "[";

    public boolean isExpression(String lexeme) {
	   return lexeme.startsWith(EXPRESSION_PREFIX);
    }
}
