package by.kukshinov.tree.appliction.data;

import by.kukshinov.tree.appliction.data.parser.ParagraphParser;
import by.kukshinov.tree.appliction.data.parser.Parser;
import by.kukshinov.tree.appliction.data.parser.SentenceParser;
import by.kukshinov.tree.appliction.data.parser.TextParser;

public class ChainBuilder {

    public Parser build() {
        Parser sentenceParser = new SentenceParser();

        Parser paragraphParser = new ParagraphParser(sentenceParser);

        return new TextParser(paragraphParser);
    }
}
