package by.kukshinov.tree.appliction.logics;

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

    private static Component getCompositeComponent(List<Component> expectedLexemes) {
	   return new CompositeComponent(expectedLexemes);
    }

    private static Component getRootComponent(List<Component> expectedLexemes) {
	   Component expectedSentence = new CompositeComponent(expectedLexemes);
	   List<Component> expectedSentences = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence);
	   Component expectedParagraph = new CompositeComponent(expectedSentences);
	   List<Component> expectedParagraphs = Arrays
			 .asList(expectedParagraph, expectedParagraph, expectedParagraph,
				    expectedParagraph);
	   return new CompositeComponent(expectedParagraphs);
    }

    @DataProvider
    public static Object[][] getComponentWithExpression(Method method) {
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent expression = LexemeComponent.expression("[4 5+]");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");
	   LexemeComponent number = LexemeComponent.word("36");

	   LexemeComponent aBlank = LexemeComponent.word(" A ");
	   LexemeComponent spaceBlank = LexemeComponent.word(" ");
	   LexemeComponent numberWithBlank = LexemeComponent.word(" 36 ");
	   LexemeComponent yearsBlank = LexemeComponent.word(" years");
	   LexemeComponent dotBlank = LexemeComponent.word(". ");

	   List<Component> lexemesExpression = Arrays
			 .asList(a, space, expression, years, dot);
	   List<Component> lexemesNumber = Arrays.asList(a, space, number, years, dot);

	   Component sentenceExpressionAdditionalSpace = getCompositeComponent(
			 Arrays.asList(space, a, space, expression, years, dot));
	   Component sentenceExpression = getCompositeComponent(lexemesExpression);
	   Component paragraphExpression = getCompositeComponent(
			 Arrays.asList(sentenceExpression, sentenceExpression, sentenceExpression,
				    sentenceExpression, sentenceExpression, sentenceExpression,
				    sentenceExpression));
	   Component sentenceWithNumber = getCompositeComponent(lexemesNumber);
	   Component longParagraphWithNumber = getCompositeComponent(
			 Arrays.asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber,
				    sentenceWithNumber));
	   Component shortParagraphWithNumber = getCompositeComponent(
			 Arrays.asList(sentenceWithNumber, sentenceWithNumber));
	   Component midParagraphWithNumber = getCompositeComponent(
			 Arrays.asList(sentenceWithNumber, sentenceWithNumber,
				    sentenceWithNumber));
	   Component paragraphsStarting = getCompositeComponent(
			 Arrays.asList(longParagraphWithNumber, shortParagraphWithNumber,
				    midParagraphWithNumber));
	   Component rootTextSorted = getCompositeComponent(
			 Arrays.asList(shortParagraphWithNumber, midParagraphWithNumber,
				    longParagraphWithNumber));
	   Component rootTextWithExpressions = getRootComponent(lexemesExpression);
	   Component rootTextWithNumbers = getRootComponent(lexemesNumber);
	   Component expectedTextWithBlanks = getRootComponent(
			 Arrays.asList(spaceBlank, dotBlank, aBlank, numberWithBlank, yearsBlank));
	   Component startTextWithBlanks = getRootComponent(
			 Arrays.asList(aBlank, spaceBlank, numberWithBlank, yearsBlank, dotBlank));

	   String methodName = method.getName();
	   if ("testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions"
			 .equalsIgnoreCase(methodName)) {
		  return getTwoComponentsMethodArgs(rootTextWithExpressions,
				rootTextWithNumbers);
	   }
	   if ("testRestoreTextShouldRestoreTextFromComponent"
			 .equalsIgnoreCase(methodName)) {
		  return getOneComponentMethodArgs(rootTextWithNumbers);
	   }
	   if ("testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength"
			 .equalsIgnoreCase(methodName)) {
		  return getTwoComponentsMethodArgs(paragraphsStarting, rootTextSorted);
	   }
	   if ("testParseShouldParseGivenSentenceToComponent".equalsIgnoreCase(methodName)) {
		  return getOneComponentMethodArgs(sentenceExpressionAdditionalSpace);
	   }
	   if ("testParseShouldParseGivenParagraphToComponent"
			 .equalsIgnoreCase(methodName)) {
		  return getTwoComponentsMethodArgs(sentenceExpression, paragraphExpression);
	   }
	   if ("testParseShouldParseGivenTextToComponent".equalsIgnoreCase(methodName)) {
		  return getTwoComponentsMethodArgs(paragraphExpression,
				rootTextWithExpressions);
	   }
//	   if ("testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength".equalsIgnoreCase(methodName)) {
	   return getTwoComponentsMethodArgs(startTextWithBlanks, expectedTextWithBlanks);
//	   }
    }

    private static Object[][] getOneComponentMethodArgs(
		  Component sentenceExpressionAdditionalSpace) {
	   Object[][] result;
	   result = new Object[1][1];
	   result[0][0] = sentenceExpressionAdditionalSpace;
	   return result;
    }

    private static Object[][] getTwoComponentsMethodArgs(
		  Component expectedComponent, Component startComponent) {
	   Object[][] result;
	   result = new Object[1][2];
	   result[0][0] = startComponent;
	   result[0][1] = expectedComponent;
	   return result;
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions(
		  Component expected, Component start) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);
	   when(interpreter.calculate(anyString())).thenReturn(EXPRESSION_RESULT);

	   Component actual = componentProcessor.calculateExpressions(start);

	   Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testRestoreTextShouldRestoreTextFromComponent(Component restoreFrom) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   String actual = componentProcessor.restoreText(restoreFrom);

	   Assert.assertEquals(actual, TEXT_FOR_SPLITTING);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength(
		  Component expected, Component forSorting) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   Component actual = componentProcessor.sortParagraphsBySentenceLength(forSorting);

	   Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength(
		  Component expected, Component startComponent) {
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

	   Component actual = componentProcessor.sortSentenceByLexemeLength(startComponent);

	   Assert.assertEquals(actual, expected);

    }
}
