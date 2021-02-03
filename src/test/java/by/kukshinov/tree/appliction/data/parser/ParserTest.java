package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ParserTest {
    private static final String SENTENCE_FOR_SPLITTING = " A [4 5+]";
    private static final String PARAGRAPH_FOR_SPLITTING = " A [4 5+] years. A [4 5+] years. ";
    private static final String TEXT_FOR_SPLITTING = " A [4 5+] years. \n" + " A [4 5+] years. \n";
    private static final LexemeComponent PARAGRAPH = LexemeComponent.word(" A [4 5+] years. \n");
    private static final LexemeComponent SENTENCE = LexemeComponent.word(" A [4 5+] years.");
    private static final LexemeComponent A = LexemeComponent.word(" A");
    private static final LexemeComponent SPACE = LexemeComponent.word(" ");
    private static final LexemeComponent EXPRESSION = LexemeComponent.expression("[4 5+]");


    @Test()
    public void testParseShouldParseGivenSentenceToComponent() {
        Component expected = new CompositeComponent(Arrays.asList(A, SPACE, EXPRESSION));
        SentenceParser parser = new SentenceParser(null);

        Component parsed = parser.parse(SENTENCE_FOR_SPLITTING);

        Assert.assertEquals(parsed, expected);
    }

    @Test()
    public void testParseShouldParseGivenParagraphToComponent() {
        Component expected = new CompositeComponent(Arrays.asList(SENTENCE, SENTENCE));
        SentenceParser successor = Mockito.mock(SentenceParser.class);
        ParagraphParser parser = new ParagraphParser(successor);
        when(successor.parse(anyString()))
                .thenAnswer(invocation -> LexemeComponent.word(invocation.getArgument(0)));
        Component parsed = parser.parse(PARAGRAPH_FOR_SPLITTING);

        Assert.assertEquals(parsed, expected);
    }

    @Test()
    public void testParseShouldParseGivenTextToComponent() {
        ParagraphParser successor = Mockito.mock(ParagraphParser.class);
        Component expected = new CompositeComponent(Arrays.asList(PARAGRAPH, PARAGRAPH));

        TextParser parser = new TextParser(successor);
        when(successor.parse(anyString()))
                .thenAnswer(invocation -> LexemeComponent.word(invocation.getArgument(0)));

        Component parsed = parser.parse(TEXT_FOR_SPLITTING);

        Assert.assertEquals(parsed, expected);
    }
}
