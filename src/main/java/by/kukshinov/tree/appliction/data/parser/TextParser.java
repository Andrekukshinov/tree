package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;

import java.util.List;
import java.util.regex.Pattern;

public class TextParser extends AbstractParser {

    private static final String PARAGRAPH_PATTERN = "(.|\\n)+?\\n";

    public TextParser(Parser successor) {
	   super(successor);
    }

    @Override
    protected Pattern getPattern() {
	   return Pattern.compile(PARAGRAPH_PATTERN);
    }
    @Override
    protected void process(
		  List<Component> paragraphsList, String paragraph, Parser successor) { {
	   Component parsed = successor.parse(paragraph);
	   paragraphsList.add(parsed);
    }
    }
}
