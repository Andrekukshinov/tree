package by.kukshinov.tree.appliction.model;

import java.util.*;

public class CompositeComponent implements Component {
    private final List<Component> components;

    public CompositeComponent(
		  List<Component> components) {
	   this.components = components;
    }

    @Override
    public List<Component> getChildren() {
	   return new ArrayList<>(components);
    }

    @Override
    public String toString() {
	   return "CompositeComponent{" + "components=" + components + '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
	   if (this == o) {
		  return true;
	   }
	   if (o == null || getClass() != o.getClass()) {
		  return false;
	   }

	   CompositeComponent that = (CompositeComponent) o;

	   return components != null ? components
			 .equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
	   return components != null ? components.hashCode() : 0;
    }
}
