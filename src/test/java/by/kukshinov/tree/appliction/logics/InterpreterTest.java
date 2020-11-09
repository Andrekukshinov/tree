package by.kukshinov.tree.appliction.logics;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InterpreterTest {
    private static final String EXPRESSION = "8 2 7 4 + * -";
    private static final String EXPECTED = "14";

    @Test
    public void testCalculateShouldCalculateGivenExpressionToString() {
	   Interpreter interpreter = new Interpreter();

	   String calculated = interpreter.calculate(EXPRESSION);

	   Assert.assertEquals(calculated, EXPECTED);
    }

}
