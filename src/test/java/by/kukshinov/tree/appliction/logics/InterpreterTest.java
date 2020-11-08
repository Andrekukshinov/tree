package by.kukshinov.tree.appliction.logics;

import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InterpreterTest {

    public static final String EXPRESSION = "8 2 7 4 + * -";
    public static final String EXPECTED = "14";

    @Test
    public void testCalculateShouldCalculateGivenExpressionToString() {
	   Interpreter interpreter = new Interpreter();

	   String calculated = interpreter.calculate(EXPRESSION);

	   Assert.assertEquals(calculated, EXPECTED);
    }

}
