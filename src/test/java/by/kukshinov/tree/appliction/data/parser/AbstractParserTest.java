package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class AbstractParserTest {
    private static final String PARAGRAPH_PATTERN = "(.|\\n)+?\\n";
    private static final String SENTENCE_PATTERN = ".*?[.!?]";
    private static final String EXPRESSION_PATTERN = "([\\s.?!]\\w+|\\s|,|\\.|\\?)|(\\[(\\d+|\\s|\\+|\\*|-|\\\\)+])";



    @Test
    public void testGetPatternShouldReturnParagraphPattern() {
	   Parser successor = Mockito.mock(Parser.class);
	   TextParser parser = new TextParser(successor);
	   Pattern pattern = parser.getPattern();

	   String actual = pattern.toString();

	   Assert.assertEquals(actual, PARAGRAPH_PATTERN);
    }

    @Test
    public void testGetPatternShouldReturnSentencePattern() {
	   Parser successor = Mockito.mock(Parser.class);
	   ParagraphParser parser = new ParagraphParser(successor);
	   Pattern pattern = parser.getPattern();

	   String actual = pattern.toString();

	   Assert.assertEquals(actual, SENTENCE_PATTERN);
    }

    @Test
    public void testGetPatternShouldReturnLexemePattern() {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   SentenceParser parser = new SentenceParser(recognizer);
	   Pattern pattern = parser.getPattern();

	   String actual = pattern.toString();

	   Assert.assertEquals(actual, EXPRESSION_PATTERN);
    }

}
