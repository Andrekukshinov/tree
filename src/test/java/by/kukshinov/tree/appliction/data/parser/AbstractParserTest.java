package by.kukshinov.tree.appliction.data.parser;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class AbstractParserTest {
    private static final String PARAGRAPH_PATTERN = "(.|\\n)+?\\n";
    private static final String SENTENCE_PATTERN = ".*?[.!?]";
    private static final String EXPRESSION_PATTERN = "([\\s.?!]\\w+|\\s|,|\\.|\\?)|(\\[(\\d+|\\s|\\+|\\*|-|/)+])";


    @Test
    public void testGetPatternShouldReturnParagraphPattern() {
	   Parser successor = Mockito.mock(Parser.class);
	   TextParser parser = new TextParser(successor);

	   String actual = parser.getPattern();

	   Assert.assertEquals(actual, PARAGRAPH_PATTERN);
    }

    @Test
    public void testGetPatternShouldReturnSentencePattern() {
	   Parser successor = Mockito.mock(Parser.class);
	   ParagraphParser parser = new ParagraphParser(successor);

	   String actual = parser.getPattern();

	   Assert.assertEquals(actual, SENTENCE_PATTERN);
    }

    @Test
    public void testGetPatternShouldReturnLexemePattern() {
	   SentenceParser parser = new SentenceParser(null);

	   String actual = parser.getPattern();

	   Assert.assertEquals(actual, EXPRESSION_PATTERN);
    }

}
