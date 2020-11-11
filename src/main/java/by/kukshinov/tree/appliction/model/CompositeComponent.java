package by.kukshinov.tree.appliction.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeComponent implements Component {
    private final List<Component> components;

    public CompositeComponent(List<Component> components) {
        this.components = components;
    }

    @Override
    public List<Component> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public String toString() {
        return "CompositeComponent{" + "components=" + components + '}' + '\n';
    }

    @Override
    public boolean equals(Object thatComponent) {
        if (this == thatComponent) {
            return true;
        }
        if (thatComponent == null || getClass() != thatComponent.getClass()) {
            return false;
        }

        CompositeComponent that = (CompositeComponent) thatComponent;

        List<Component> thatComponents = that.components;
        return components != null ? components.equals(thatComponents) : thatComponents == null;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
