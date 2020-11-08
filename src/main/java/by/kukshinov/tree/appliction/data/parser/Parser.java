package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.model.Component;

import java.util.Comparator;

public interface Parser {
    Component parse	(String component);
}
// TODO: 07.11.2020 create factory and tests
