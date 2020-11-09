package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComponentProcessor {
    private static final int START_BRACKET_INDEX = 1;
    private static final char NEW_LINE = '\n';
    private final ExpressionRecognizer recognizer;
    private final Interpreter interpreter;

    public ComponentProcessor(
		  ExpressionRecognizer recognizer, Interpreter interpreter) {
	   this.recognizer = recognizer;
	   this.interpreter = interpreter;
    }

    public Component calculateExpressions(Component component) {
	   List<Component> newBuiltComponents = new ArrayList<>();
	   List<Component> children = component.getChildren();
	   if (children.size() != 0) {
		  for (Component child : children) {
			 Component builtChild = calculateExpressions(child);
			 newBuiltComponents.add(builtChild);
		  }
		  return new CompositeComponent(newBuiltComponents);
	   } else {
		  String stringValue = ((LexemeComponent) component).getValue();
		  if (recognizer.isExpression(stringValue)) {
			 String parsed = calculateExpression(stringValue, interpreter);
			 return LexemeComponent.word(parsed);
		  }
		  return component;
	   }
    }

    private String calculateExpression(String stringValue, Interpreter interpreter) {
	   int stringLength = stringValue.length();
	   String forCalculation = stringValue
			 .substring(START_BRACKET_INDEX, stringLength - 1);
	   return interpreter.calculate(forCalculation);
    }

    public String restoreText(Component root) {
	   StringBuilder text = new StringBuilder();
	   for (Component paragraph : root.getChildren()) {
		  for (Component sentence : paragraph.getChildren()) {
			 for (Component lexeme : sentence.getChildren()) {
				String lexemeValue = ((LexemeComponent) lexeme).getValue();
				text.append(lexemeValue);
			 }
		  }
		  text.append(NEW_LINE);
	   }
	   return text.toString();
    }

    public Component sortParagraphsBySentenceLength(Component root) {
	   List<Component> resultParagraphs = new ArrayList<>(root.getChildren());
	   resultParagraphs.sort(Comparator.comparingInt(sentence2 -> {
		  List<Component> sentences = sentence2.getChildren();
		  return sentences.size();
	   }));

	   return new CompositeComponent(resultParagraphs);
    }

    public Component sortSentenceByLexemeLength(Component root) {
	   List<Component> resultParagraphs = new ArrayList<>();
	   for (Component paragraph : root.getChildren()) {
		  List<Component> resultSentences = new ArrayList<>();
		  for (Component sentence : paragraph.getChildren()) {
			 List<Component> resultLexemes = getSortedLexemes(sentence);
			 resultSentences.add(new CompositeComponent(resultLexemes));
		  }
		  resultParagraphs.add(new CompositeComponent(resultSentences));
	   }
	   return new CompositeComponent(resultParagraphs);
    }

    private List<Component> getSortedLexemes(Component sentence) {
	   List<Component> resultLexemes = new ArrayList<>(sentence.getChildren());
	   resultLexemes.sort(Comparator.comparingInt(lexeme2 -> {
		  String lexemeValue = ((LexemeComponent) lexeme2).getValue();
		  return lexemeValue.length();
	   }));
	   return resultLexemes;
    }
}
