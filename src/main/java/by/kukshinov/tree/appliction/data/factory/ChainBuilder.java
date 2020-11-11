package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.data.parser.ParagraphParser;
import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.SentenceParser;
import by.kukshinov.tree.appliction.data.parser.TextParser;

public class ChainBuilder {

    public Parser build() {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();
        SentenceParserFactory sentenceFactory = new SentenceParserFactory(recognizer);
	   Parser sentenceParser = sentenceFactory.createParser();

        ParagraphParserFactory paragraphFactory= new ParagraphParserFactory(sentenceParser);
	   Parser paragraphParser = paragraphFactory.createParser();

	   TextParserFactory textFactory= new TextParserFactory(paragraphParser);
	   return textFactory.createParser();
    }
}
