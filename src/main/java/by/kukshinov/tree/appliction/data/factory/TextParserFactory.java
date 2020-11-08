package by.kukshinov.tree.appliction.data.factory;

import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.TextParser;

public class TextParserFactory implements ParserFactory {
    private final Parser successor;

    public TextParserFactory(Parser successor) {
	   this.successor = successor;
    }

    @Override
    public Parser createParser() {
	   return new TextParser(successor);
    }
}
