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
    public static final String LEXEME_EXPRESSION = "[4 7 * 8 +]";
    public static final String EXPRESSION = "4 7 * 8 +";
    public static final String EXPRESSION_RESULT = "36";

    private Component getRootComponent(List<Component> expectedLexemes) {
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
    public Object[][] getComponentWithExpression(Method method) {
	   Object[][] result;
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent expression = LexemeComponent.expression("[4 5+]");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");
	   LexemeComponent nine = LexemeComponent.word("36");

	   LexemeComponent aBlank = LexemeComponent.word(" A ");
	   LexemeComponent spaceBlank = LexemeComponent.word(" ");
	   LexemeComponent nineBlank = LexemeComponent.word(" 36 ");
	   LexemeComponent yearsBlank = LexemeComponent.word(" years");
	   LexemeComponent dotBlank = LexemeComponent.word(". ");

	   List<Component> lexemesExpression = Arrays.asList(a, space, expression, years, dot);
	   Component expectedExpression = getRootComponent(lexemesExpression);

	   List<Component> lexemesNine = Arrays.asList(a, space, nine, years, dot);
	   Component startNine = getRootComponent(lexemesNine);

	   Component sentenceNine = new CompositeComponent(lexemesNine);
	   List<Component> sentencesNines1 = Arrays
			 .asList(sentenceNine, sentenceNine, sentenceNine,
				    sentenceNine);
	   List<Component> sentencesNines2 = Arrays
			 .asList(sentenceNine, sentenceNine);
	   List<Component> sentencesNines3 = Arrays
			 .asList(sentenceNine, sentenceNine, sentenceNine);

	   Component paragraphNine1 = new CompositeComponent(sentencesNines1);
	   Component paragraphNine2 = new CompositeComponent(sentencesNines2);
	   Component paragraphNine3 = new CompositeComponent(sentencesNines3);
	   List<Component> paragraphsForSorting = Arrays.asList(paragraphNine1, paragraphNine2, paragraphNine3);
	   List<Component> paragraphNines = Arrays.asList(paragraphNine2, paragraphNine3, paragraphNine1);
	   Component paragraphsStarting = new CompositeComponent(paragraphsForSorting);
	   Component paragraphsNines = new CompositeComponent(paragraphNines);

	   List<Component> expectedLexemesWithBlanks = Arrays.asList(spaceBlank, dotBlank, aBlank, nineBlank ,yearsBlank);
	   List<Component> startLexemesWithBlanks = Arrays.asList(aBlank, spaceBlank, nineBlank ,yearsBlank, dotBlank);
	   Component expectedExpressionWithBlanks = getRootComponent(expectedLexemesWithBlanks);
	   Component startExpressionWithBlanks = getRootComponent(startLexemesWithBlanks);

	   String methodName = method.getName();
	   if ("testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions"
			 .equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = expectedExpression;
		  result[0][1] = expectedExpression;
		  return result;
	   }
	   if ("testRestoreTextShouldRestoreTextFromComponent".equalsIgnoreCase(methodName)) {
		  result = new Object[1][1];
		  result[0][0] = startNine;
		  return result;
	   }
	   if ("testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength".equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = paragraphsStarting;
		  result[0][1] = paragraphsNines;
		  return result;
	   }
//	   if ("testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength".equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = startExpressionWithBlanks;
		  result[0][1] = expectedExpressionWithBlanks;
		  return result;
//	   }
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions(
		  Component start, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,interpreter);
	   when(recognizer.isExpression(LEXEME_EXPRESSION)).thenReturn(true);
	   when(interpreter.calculate(EXPRESSION)).thenReturn(EXPRESSION_RESULT);

	   Component actual = componentProcessor.calculateExpressions(start);

	   Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testRestoreTextShouldRestoreTextFromComponent(Component restoreFrom) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,interpreter);

	   String actual = componentProcessor.restoreText(restoreFrom);

	   Assert.assertEquals(actual, TEXT_FOR_SPLITTING);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength(Component forSorting, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,interpreter);

	   Component actual = componentProcessor.sortParagraphsBySentenceLength(forSorting);

	   Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength(Component startComponent, Component expected) {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,interpreter);

	   Component actual = componentProcessor.sortSentenceByLexemeLength(startComponent);

	   Assert.assertEquals(actual, expected);

    }
}
