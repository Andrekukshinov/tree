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
    protected String getPattern() {
        return SENTENCE_PATTERN;
    }

}
