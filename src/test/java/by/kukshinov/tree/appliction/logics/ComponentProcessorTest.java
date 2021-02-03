package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ComponentProcessorTest {
    private final String TEXT_FOR_SPLITTING =
            " A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 . \n" +
                    " A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 . \n" +
                    " A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 . \n" +
                    " A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 .  A   36 . \n";


    private final LexemeComponent a = LexemeComponent.word(" A ");
    private final LexemeComponent space = LexemeComponent.word(" ");
    private final LexemeComponent number = LexemeComponent.word(" 36 ");
    private final LexemeComponent dot = LexemeComponent.word(". ");
    private final LexemeComponent expression = LexemeComponent.expression(" [4 5+] ");

    private final List<Component> lexemesNumber = Arrays.asList(a, space, number, dot);
    private final List<Component> lexemesExpr = Arrays.asList(a, space, expression, dot);
    private final Component rootTextWithNumbers = getRootComponent(lexemesNumber);
    private final Component rootTextWithExpressions = getRootComponent(lexemesExpr);
    private final Component expectedTextWithBlanks = getRootComponent(Arrays.asList(space, dot, a, number));

    private final Component paragraphsStarting = getUnsortedRootComponent(lexemesNumber);
    private final Component rootTextSorted = getSortedRootComponent(lexemesNumber);

    private Component getRootComponent(List<Component> expectedLexemes) {
        Component expectedSentence = new CompositeComponent(expectedLexemes);
        List<Component> expectedSentences =
                Arrays.asList(expectedSentence, expectedSentence, expectedSentence,
                        expectedSentence, expectedSentence, expectedSentence, expectedSentence);
        Component expectedParagraph = new CompositeComponent(expectedSentences);
        List<Component> expectedParagraphs =
                Arrays.asList(expectedParagraph, expectedParagraph, expectedParagraph, expectedParagraph);
        return new CompositeComponent(expectedParagraphs);
    }

    private Component getUnsortedRootComponent(List<Component> expectedLexemes) {
        Component sentenceWithNumber = new CompositeComponent(expectedLexemes);
        Component longParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber, sentenceWithNumber));
        Component shortParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber));
        Component midParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber));
        return new CompositeComponent(Arrays.asList(longParagraphWithNumber, shortParagraphWithNumber, midParagraphWithNumber));

    }

    private Component getSortedRootComponent(List<Component> expectedLexemes) {
        Component sentenceWithNumber = new CompositeComponent(expectedLexemes);
        Component longParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber, sentenceWithNumber));
        Component shortParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber));
        Component midParagraphWithNumber = new CompositeComponent(Arrays.asList(sentenceWithNumber, sentenceWithNumber, sentenceWithNumber));
        return new CompositeComponent(Arrays.asList(shortParagraphWithNumber, midParagraphWithNumber, longParagraphWithNumber));

    }

    @Test
    public void testRestoreTextShouldRestoreTextFromComponent() {
        Interpreter interpreter = Mockito.mock(Interpreter.class);
        ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

        String actual = componentProcessor.restoreText(rootTextWithNumbers);

        Assert.assertEquals(actual, TEXT_FOR_SPLITTING);
    }

    @Test
    public void testCalculateExpressionsShouldParseGivenComponentAndCalculateAllExpressions() {
        Interpreter interpreter = Mockito.mock(Interpreter.class);
        ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);
        when(interpreter.calculate(anyString())).thenReturn(" 36 ");

        Component actual = componentProcessor.calculateExpressions(rootTextWithExpressions);

        Assert.assertEquals(actual, rootTextWithNumbers);
    }


    @Test
    public void testSortSentenceByLexemeLengthShouldSortSentencesInComponentByLexemeLength() {
        Interpreter interpreter = Mockito.mock(Interpreter.class);
        ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

        Component actual = componentProcessor.sortSentenceByLexemeLength(rootTextWithNumbers);

        Assert.assertEquals(actual, expectedTextWithBlanks);

    }

    @Test
    public void testSortParagraphsByLengthShouldSortParagraphsInComponentBySentenceLength() {
        Interpreter interpreter = Mockito.mock(Interpreter.class);
        ComponentProcessor componentProcessor = new ComponentProcessor(interpreter);

        Component actual = componentProcessor.sortParagraphsBySentenceLength(paragraphsStarting);

        Assert.assertEquals(actual, rootTextSorted);

    }
}
