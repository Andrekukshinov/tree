package by.kukshinov.tree.appliction.logics;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InterpreterTest {

    @DataProvider
    public Object[][] expressionCalculatorDataProvider() {
        String expression = "8 2 7 4 + * -";
        String expected = "14";
        Object[][] result = new Object[6][2];
        result[0][0] = "4 5 +";
        result[0][1] = "9";

        result[1][0] = "5 4 -";;
        result[1][1] = "-1";


        result[2][0] = "4 5 *";
        result[2][1] = "20";

        result[3][0] = "5 20 /";
        result[3][1] = "4";

        result[4][0] = "4 5 -";;
        result[4][1] = "1";

        result[5][0] = expression;
        result[5][1] = expected;
        return result;
    }

    @Test(dataProvider = "expressionCalculatorDataProvider")
    public void testCalculateShouldCalculateGivenExpressionToString(String expression, String result) {
	   Interpreter interpreter = new Interpreter();

	   String calculated = interpreter.calculate(expression);

	   Assert.assertEquals(calculated, result);
    }

}
