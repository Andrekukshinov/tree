package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ComponentProcessorTest {
    private static final String TEXT_FOR_SPLITTING = "" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n" + " A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years. A 36 years.\n";

    @Test
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions() {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   Interpreter interpreter = Mockito.mock(Interpreter.class);
	   ComponentProcessor componentProcessor = new ComponentProcessor(recognizer,
			 interpreter);
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent expression = LexemeComponent.expression("[4 7 * 8 +]");
	   LexemeComponent nine = LexemeComponent.word("36");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");

	   List<Component> expectedLexemes = Arrays.asList(a, space, nine, years, dot);
	   Component expectedSentence = new CompositeComponent(expectedLexemes);
	   List<Component> expectedSentences = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence);
	   Component expectedParagraph = new CompositeComponent(expectedSentences);
	   List<Component> expectedParagraphs = Arrays
			 .asList(expectedParagraph, expectedParagraph, expectedParagraph,
				    expectedParagraph);
	   Component expected = new CompositeComponent(expectedParagraphs);


	   List<Component> startLexemes = Arrays.asList(a, space, expression, years, dot);
	   Component startSentence = new CompositeComponent(startLexemes);
	   List<Component> startSentences = Arrays
			 .asList(startSentence, startSentence, startSentence, startSentence,
				    startSentence, startSentence, startSentence);
	   Component startParagraph = new CompositeComponent(startSentences);
	   List<Component> startParagraphs = Arrays
			 .asList(startParagraph, startParagraph, startParagraph, startParagraph);
	   Component start = new CompositeComponent(startParagraphs);

	   when(recognizer.isExpression("[8 2 7 4 + * -]")).thenReturn(true);
	   when(recognizer.isExpression("[4 7 * 8 +]")).thenReturn(true);

	   when(interpreter.calculate("[8 2 7 4 + * -]")).thenReturn("14");
	   when(interpreter.calculate("4 7 * 8 +")).thenReturn("36");

	   Component actual = componentProcessor.calculateExpressions(start);

	   Assert.assertEquals(actual, expected);// TODO: 07.11.2020 refactor tests
    }


    @Test
    public void testRestoreTextShouldRestoreTextFromComponent() {
	   ComponentProcessor componentProcessor = new ComponentProcessor(null, null);
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent nine = LexemeComponent.word("36");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");

	   List<Component> expectedLexemes = Arrays.asList(a, space, nine, years, dot);
	   Component expectedSentence = new CompositeComponent(expectedLexemes);
	   List<Component> expectedSentences = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence);
	   Component expectedParagraph = new CompositeComponent(expectedSentences);
	   List<Component> expectedParagraphs = Arrays
			 .asList(expectedParagraph, expectedParagraph, expectedParagraph,
				    expectedParagraph);
	   Component restoreFrom = new CompositeComponent(expectedParagraphs);
	   String actual = componentProcessor.restoreText(restoreFrom);

	   Assert.assertEquals(actual, TEXT_FOR_SPLITTING);// TODO: 07.11.2020 refactor tests
    }
    @Test
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength() {
	   ComponentProcessor componentProcessor = new ComponentProcessor(null, null);
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent nine = LexemeComponent.word("36");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");

	   List<Component> expectedLexemes = Arrays.asList(a, space, nine, years, dot);
	   Component expectedSentence = new CompositeComponent(expectedLexemes);

	   List<Component> expectedSentences1 = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence);
	   List<Component> expectedSentences2 = Arrays
			 .asList(expectedSentence, expectedSentence);
	   List<Component> expectedSentences3 = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence);

	   Component expectedParagraph1 = new CompositeComponent(expectedSentences1);
	   Component expectedParagraph2 = new CompositeComponent(expectedSentences2);
	   Component expectedParagraph3 = new CompositeComponent(expectedSentences3);
	   List<Component> paragraphsForSorting = Arrays.asList(expectedParagraph1, expectedParagraph2, expectedParagraph3);
	   List<Component> expectedParagraphs = Arrays.asList(expectedParagraph2, expectedParagraph3, expectedParagraph1);
	   Component forSorting = new CompositeComponent(paragraphsForSorting);
	   Component expected = new CompositeComponent(expectedParagraphs);
	   Component actual = componentProcessor.sortParagraphsBySentenceLength(forSorting);

	   Assert.assertEquals(actual, expected);// TODO: 07.11.2020 refactor tests

    }
    
    @Test
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength() {
	   ComponentProcessor componentProcessor = new ComponentProcessor(null, null);
	   LexemeComponent a = LexemeComponent.word(" A ");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent nine = LexemeComponent.word(" 36 ");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(". ");

	   List<Component> expectedLexemes = Arrays.asList(space, dot, a, nine ,years);
	   Component expectedSentence = new CompositeComponent(expectedLexemes);
	   List<Component> expectedSentences = Arrays
			 .asList(expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence, expectedSentence, expectedSentence,
				    expectedSentence);
	   Component expectedParagraph = new CompositeComponent(expectedSentences);
	   List<Component> expectedParagraphs = Arrays
			 .asList(expectedParagraph, expectedParagraph, expectedParagraph,
				    expectedParagraph);
	   Component expected = new CompositeComponent(expectedParagraphs);



	   List<Component> startLexemes = Arrays.asList(a, space, nine, years, dot);
	   Component startSentence = new CompositeComponent(startLexemes);
	   List<Component> startSentences = Arrays
			 .asList(startSentence, startSentence, startSentence,
				    startSentence, startSentence, startSentence,
				    startSentence);
	   Component startParagraph = new CompositeComponent(startSentences);
	   List<Component> startParagraphs = Arrays
			 .asList(startParagraph, startParagraph, startParagraph,
				    startParagraph);
	   Component startComponent = new CompositeComponent(startParagraphs);

	   Component actual = componentProcessor.sortSentenceByLexemeLength(startComponent);

	   Assert.assertEquals(actual, expected);// TODO: 07.11.2020 refactor tests

    }
}
