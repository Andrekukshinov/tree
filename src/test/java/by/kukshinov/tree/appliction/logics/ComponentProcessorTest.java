package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.data.ComponentDataProvider;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ComponentProcessorTest {
    private static final String TEXT_FOR_SPLITTING = " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n";
    private static final String EXPRESSION_RESULT = "36";

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentDataProvider.class)
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions(Component expected, Component start) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);
	   when(interpreter.calculate(anyString())).thenReturn(EXPRESSION_RESULT);

	   Component actual = componentProcessor.calculateExpressions(start);

	   Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentDataProvider.class)
    public void testRestoreTextShouldRestoreTextFromComponent(Component restoreFrom) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   String actual = componentProcessor.restoreText(restoreFrom);

	   Assert.assertEquals(actual, TEXT_FOR_SPLITTING);
    }

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentDataProvider.class)
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength(Component expected, Component forSorting) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   Component actual = componentProcessor.sortParagraphsBySentenceLength(forSorting);

	   Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentDataProvider.class)
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength(Component expected, Component startComponent) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   Component actual = componentProcessor.sortSentenceByLexemeLength(startComponent);

	   Assert.assertEquals(actual, expected);

    }
}
