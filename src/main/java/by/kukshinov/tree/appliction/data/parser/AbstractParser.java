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

    protected abstract String getPattern();

    protected void process(String value, List<Component> childrenComponents) {
        Parser successor = getSuccessor();
        Component parsed = successor.parse(value);
        childrenComponents.add(parsed);
    }


    @Override
    public Component parse(String text) {
        Pattern pattern = Pattern.compile(getPattern());
        Matcher matcher = pattern.matcher(text);
        List<Component> childrenComponents = new ArrayList<>();
        while (matcher.find()) {
            String foundValue = matcher.group();
            process(foundValue, childrenComponents);
        }
        return new CompositeComponent(childrenComponents);
    }
}
