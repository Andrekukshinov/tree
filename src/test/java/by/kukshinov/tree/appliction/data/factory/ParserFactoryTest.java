package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.data.parser.ParagraphParser;
import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.SentenceParser;
import by.kukshinov.tree.appliction.data.parser.TextParser;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserFactoryTest {

    @Test
    public void testCreateParserShouldCreateTextParser() {
	   Parser successor = Mockito.mock(Parser.class);
	   ParserFactory parserFactory = new TextParserFactory(successor);

	   Parser textParser = parserFactory.createParser();

	   Assert.assertTrue(textParser instanceof TextParser);
    }

    @Test
    public void testCreateParserShouldCreateParagraphParser() {
	   Parser successor = Mockito.mock(Parser.class);
	   ParserFactory parserFactory = new ParagraphParserFactory(successor);

	   Parser textParser = parserFactory.createParser();

	   Assert.assertTrue(textParser instanceof ParagraphParser);
    }

    @Test
    public void testCreateParserShouldCreateSentenceParser() {
	   ExpressionRecognizer recognizer = Mockito.mock(ExpressionRecognizer.class);
	   ParserFactory parserFactory = new SentenceParserFactory(recognizer);

	   Parser textParser = parserFactory.createParser();

	   Assert.assertTrue(textParser instanceof SentenceParser);
    }
}
