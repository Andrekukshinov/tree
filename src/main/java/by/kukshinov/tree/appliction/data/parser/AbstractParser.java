package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser implements Parser {
    private final Parser successor;

    public AbstractParser(Parser successor) {
	   this.successor = successor;
    }

    protected Parser getSuccessor() {
	   return successor;
    }

    //
    protected abstract Pattern getPattern();

    @Override
    public Component parse(String text) {
	   Pattern pattern = getPattern();
	   Matcher matcher = pattern.matcher(text);
	   List<Component> paragraphsList = new ArrayList<>();
	   process(matcher, paragraphsList);
	   return new CompositeComponent(paragraphsList);
    }

    protected abstract void process(Matcher matcher, List<Component> paragraphsList);
}