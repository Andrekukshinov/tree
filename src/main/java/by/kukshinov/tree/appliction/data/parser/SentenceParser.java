package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.LexemeComponent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final String WORD_PATTERN = "([\\s.?!]\\w+|\\s|,|\\.|\\?)";
    private static final String EXPRESSION_PATTERN = "(\\[(\\d+|\\s|\\+|\\*|-|/)+])";
    private static final String OR = "|";
    private static final String LEXEME_PATTERN = WORD_PATTERN + OR + EXPRESSION_PATTERN;
    private static final String EXPRESSION_PREFIX = "[";

    public SentenceParser() {
        super(null);
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(LEXEME_PATTERN);
    }


    @Override
    protected void process(String lexeme, List<Component> lexemesList) {
            if (isExpression(lexeme)) {
                lexemesList.add(LexemeComponent.expression(lexeme));
            } else {
                lexemesList.add(LexemeComponent.word(lexeme));
            }
    }
    private boolean isExpression(String lexeme) {
        return lexeme.startsWith(EXPRESSION_PREFIX);
    }
}

