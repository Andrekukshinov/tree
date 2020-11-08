package by.kukshinov.tree.appliction.model;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface Component {
    List<Component> getChildren();
}
