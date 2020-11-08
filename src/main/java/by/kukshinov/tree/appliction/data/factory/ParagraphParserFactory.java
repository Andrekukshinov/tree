package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.parser.ParagraphParser;
import by.kukshinov.tree.appliction.data.parser.Parser;

public class ParagraphParserFactory implements ParserFactory {
    private final Parser successor;

    public ParagraphParserFactory(Parser successor) {
	   this.successor = successor;
    }

    @Override
    public Parser createParser() {
	   return new ParagraphParser(successor);
    }
}
