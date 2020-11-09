package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final String SENTENCE_PATTERN = ".*?[.!?]";

    public ParagraphParser(Parser successor) {
	   super(successor);
    }

    @Override
    protected Pattern getPattern() {
	   return Pattern.compile(SENTENCE_PATTERN);
    }


    @Override
    protected void process(
		  Matcher matcher, List<Component> paragraphsList) {
	   while (matcher.find()) {
		  String paragraph = matcher.group();
		  Parser successor = getSuccessor();
		  Component parsed = successor.parse(paragraph);
		  paragraphsList.add(parsed);
	   }
    }
}
