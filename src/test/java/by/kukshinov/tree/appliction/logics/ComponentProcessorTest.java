package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
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

import static org.mockito.Mockito.when;

public class ComponentProcessorTest {
    private static final String TEXT_FOR_SPLITTING = " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n";
    private static final String LEXEME_EXPRESSION = "[4 7 * 8 +]";
    private static final String EXPRESSION = "4 7 * 8 +";
    private static final String EXPRESSION_RESULT = "36";

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
	   Object[][] result;
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
	   Component rootTextWithExpressions = getRootComponent(lexemesExpression);

	   Component sentenceExpression = new CompositeComponent(lexemesExpression);
	   List<Component> sentencesExpression = Arrays
			 .asList(sentenceExpression, sentenceExpression, sentenceExpression,
				    sentenceExpression, sentenceExpression, sentenceExpression,
				    sentenceExpression);
	   Component paragraphExpression = new CompositeComponent(sentencesExpression);


	   List<Component> lexemesNumber = Arrays.asList(a, space, number, years, dot);
	   Component rootTextWithNumbers = getRootComponent(lexemesNumber);

	   Component sentenceWithNumber = new CompositeComponent(lexemesNumber);
	   List<Component> longSentencesNumbers = Arrays
			 .asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber,
				    sentenceWithNumber);
	   List<Component> shortSentencesNumbers = Arrays
			 .asList(sentenceWithNumber, sentenceWithNumber);
	   List<Component> midSentencesNumbers = Arrays
			 .asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber);

	   Component longParagraphWithNumber = new CompositeComponent(longSentencesNumbers);
	   Component shortParagraphWithNumber = new CompositeComponent(
			 shortSentencesNumbers);
	   Component midParagraphWithNumber = new CompositeComponent(midSentencesNumbers);
	   List<Component> paragraphsForSorting = Arrays
			 .asList(longParagraphWithNumber, shortParagraphWithNumber,
				    midParagraphWithNumber);
	   List<Component> paragraphsSorted = Arrays
			 .asList(shortParagraphWithNumber, midParagraphWithNumber,
				    longParagraphWithNumber);
	   Component paragraphsStarting = new CompositeComponent(paragraphsForSorting);
	   Component rootTextSorted = new CompositeComponent(paragraphsSorted);

	   List<Component> sentenceLexemesBlanksExpected = Arrays
			 .asList(spaceBlank, dotBlank, aBlank, numberWithBlank, yearsBlank);
	   List<Component> startLexemesWithBlanks = Arrays
			 .asList(aBlank, spaceBlank, numberWithBlank, yearsBlank, dotBlank);
	   Component expectedTextWithBlanks = getRootComponent(
			 sentenceLexemesBlanksExpected);
	   Component startTextWithBlanks = getRootComponent(startLexemesWithBlanks);

	   String methodName = method.getName();
	   if ("testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions"
			 .equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = rootTextWithExpressions;
		  result[0][1] = rootTextWithExpressions;
		  return result;
	   }
	   if ("testRestoreTextShouldRestoreTextFromComponent"
			 .equalsIgnoreCase(methodName)) {
		  result = new Object[1][1];
		  result[0][0] = rootTextWithNumbers;
		  return result;
	   }
	   if ("testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength"
			 .equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = paragraphsStarting;
		  result[0][1] = rootTextSorted;
		  return result;
	   }
	   if ("testParseShouldParseGivenSentenceToComponent".equalsIgnoreCase(methodName)) {
		  result = new Object[1][1];
		  result[0][0] = sentenceExpression;
		  return result;
	   }
	   if ("testParseShouldParseGivenParagraphToComponent"
			 .equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = paragraphExpression;
		  result[0][1] = sentenceExpression;
		  return result;
	   }
	   if ("testParseShouldParseGivenTextToComponent".equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = rootTextWithExpressions;
		  result[0][1] = paragraphExpression;
		  return result;
	   }
//	   if ("testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength".equalsIgnoreCase(methodName)) {
	   result = new Object[1][2];
	   result[0][0] = startTextWithBlanks;
	   result[0][1] = expectedTextWithBlanks;
	   return result;
//	   }
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions(
		  Component start, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,
			 interpreter);
	   when(recognizer.isExpression(LEXEME_EXPRESSION)).thenReturn(true);
	   when(interpreter.calculate(EXPRESSION)).thenReturn(EXPRESSION_RESULT);

	   Component actual = componentProcessor.calculateExpressions(start);

	   Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testRestoreTextShouldRestoreTextFromComponent(Component restoreFrom) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,
			 interpreter);

	   String actual = componentProcessor.restoreText(restoreFrom);

	   Assert.assertEquals(actual, TEXT_FOR_SPLITTING);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength(
		  Component forSorting, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,
			 interpreter);

	   Component actual = componentProcessor.sortParagraphsBySentenceLength(forSorting);

	   Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength(
		  Component startComponent, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,
			 interpreter);

	   Component actual = componentProcessor.sortSentenceByLexemeLength(startComponent);

	   Assert.assertEquals(actual, expected);

    }
}
