package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.SentenceParser;

public class SentenceParserFactory implements ParserFactory {
    private final ExpressionRecognizer recognizer;

    public SentenceParserFactory(
		  ExpressionRecognizer recognizer) {
	   this.recognizer = recognizer;
    }

    @Override
    public Parser createParser() {
	   return new SentenceParser(recognizer);
    }
}
