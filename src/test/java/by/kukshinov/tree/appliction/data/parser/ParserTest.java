package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.logics.ComponentProcessorTest;
import by.kukshinov.tree.appliction.model.Component;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ParserTest {
    private static final String SENTENCE_FOR_SPLITTING = "  A [4 5+] years.";
    private static final String PARAGRAPH_FOR_SPLITTING = " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.";
    private static final String TEXT_FOR_SPLITTING = "" + "  A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n";


    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentProcessorTest.class)
    public void testParseShouldParseGivenSentenceToComponent(Component expected) {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();
	   SentenceParser parser = new SentenceParser(recognizer);

	   Component parsed = parser.parse(SENTENCE_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentProcessorTest.class)
    public void testParseShouldParseGivenParagraphToComponent(
		  Component expected, Component sentence) {
	   SentenceParser successor = Mockito.mock(SentenceParser.class);
	   ParagraphParser parser = new ParagraphParser(successor);
	   when(successor.parse(anyString())).thenReturn(sentence);

	   Component parsed = parser.parse(PARAGRAPH_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }

    @Test(dataProvider = "getComponentWithExpression", dataProviderClass = ComponentProcessorTest.class)
    public void testParseShouldParseGivenTextToComponent(
		  Component expected, Component paragraph) {
	   ParagraphParser successor = Mockito.mock(ParagraphParser.class);
	   TextParser parser = new TextParser(successor);
	   when(successor.parse(anyString())).thenReturn(paragraph);
	   Component parsed = parser.parse(TEXT_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }
}
