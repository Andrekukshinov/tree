package by.kukshinov.tree.appliction.data;

public class ExpressionRecognizer {

    public static final String EXPRESSION_PREFIX = "[";

    public boolean isExpression(String lexeme) {
	   return lexeme.startsWith(EXPRESSION_PREFIX);
    }
}// TODO: 08.11.2020 test