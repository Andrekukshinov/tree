package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import by.kukshinov.tree.appliction.model.LexemeType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComponentProcessor {
    private static final int START_BRACKET_INDEX = 1;
    private static final char NEW_LINE = '\n';
    private final Interpreter interpreter;

    public ComponentProcessor(
            Interpreter interpreter) {

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
        }
        String stringValue = ((LexemeComponent) component).getValue();
        if (((LexemeComponent) component).getType() == LexemeType.EXPRESSION) {
            String parsed = calculateExpression(stringValue);
            return LexemeComponent.word(parsed);
        }
        return component;

    }

    private String calculateExpression(String stringValue) {
        int stringLength = stringValue.length();
        String forCalculation = stringValue.substring(START_BRACKET_INDEX, stringLength - 1);
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

        Comparator<Component> comparator = Comparator.comparingInt(thatSentence -> {
            List<Component> sentences = thatSentence.getChildren();
            return sentences.size();
        });
        resultParagraphs.sort(comparator);

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

        Comparator<Component> comparator = Comparator.comparingInt(thatLexeme -> {
            String lexemeValue = ((LexemeComponent) thatLexeme).getValue();
            return lexemeValue.length();
        });

        resultLexemes.sort(comparator);
        return resultLexemes;
    }
}
