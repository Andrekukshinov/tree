package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.parser.ParagraphParser;
import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.SentenceParser;
import by.kukshinov.tree.appliction.data.parser.TextParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserFactoryTest {

    @Test
    public void testCreateParserShouldCreateTextParser() {
	   ParserFactory parserFactory = new TextParserFactory(null);
	   Parser textParser = parserFactory.createParser();
	   Assert.assertTrue(textParser instanceof TextParser);
    }

    @Test
    public void testCreateParserShouldCreateParagraphParser() {
	   ParserFactory parserFactory = new ParagraphParserFactory(null);
	   Parser textParser = parserFactory.createParser();
	   Assert.assertTrue(textParser instanceof ParagraphParser);
    }

    @Test
    public void testCreateParserShouldCreateSentenceParser() {
	   ParserFactory parserFactory = new SentenceParserFactory(null);
	   Parser textParser = parserFactory.createParser();
	   Assert.assertTrue(textParser instanceof SentenceParser);
    }
}
