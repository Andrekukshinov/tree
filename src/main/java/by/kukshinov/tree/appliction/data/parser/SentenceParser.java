package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.LexemeComponent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private static final String EXPRESSION_PATTERN = "([\\s.?!]\\w+|\\s|,|\\.|\\?)|(\\[(\\d+|\\s|\\+|\\*|-|\\\\)+])";
    private final ExpressionRecognizer recognizer;

    public SentenceParser(
		  ExpressionRecognizer recognizer) {
	   super(null);
	   this.recognizer = recognizer;
    }

    @Override
    protected Pattern getPattern() {
	   return Pattern.compile(EXPRESSION_PATTERN);
    }


    @Override
    protected void process(
		  Matcher matcher, List<Component> lexemesList) {
	   while (matcher.find()) {
		  String lexeme = matcher.group();
		  if (recognizer.isExpression(lexeme)) {
			 lexemesList.add(LexemeComponent.expression(lexeme));
		  } else {
			 lexemesList.add(LexemeComponent.word(lexeme));
		  }
	   }
    }
}
