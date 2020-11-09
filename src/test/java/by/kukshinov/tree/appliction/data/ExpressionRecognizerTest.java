package by.kukshinov.tree.appliction.data;


import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpressionRecognizerTest {
    private static final String EXPRESSION = "[8 2 7 4 + * -]";
    private static final String WORD = "Sam5";

    @Test
    public void testIsExpressionShouldReturnTrue() {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();

	   boolean expression = recognizer.isExpression(EXPRESSION);

	   Assert.assertTrue(expression);
    }

    @Test
    public void testIsExpressionShouldReturnFalse() {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();

	   boolean expression = recognizer.isExpression(WORD);

	   Assert.assertFalse(expression);
    }
}
