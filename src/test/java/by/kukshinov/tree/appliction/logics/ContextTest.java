package by.kukshinov.tree.appliction.logics;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayDeque;

public class ContextTest {

    public static final Integer EXPECTED = 7;

    @Test
    public void testPushValueShouldPushNumberToContext() {
	   Context context = new Context();
	   context.pushValue(EXPECTED);
	   ArrayDeque<Integer> contextValues = context.getContextValues();

	   Integer actual = contextValues.pollLast();

	   Assert.assertEquals(actual, EXPECTED);
    }

    @Test
    public void testPopValueShouldPopNumberFromContext() {
	   Context context = new Context();
	   context.pushValue(EXPECTED);

	   Integer actual = context.popValue();

	   Assert.assertEquals(actual, EXPECTED);
    }
}
