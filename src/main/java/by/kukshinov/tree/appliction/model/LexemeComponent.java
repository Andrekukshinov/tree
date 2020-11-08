package by.kukshinov.tree.appliction.model;


import java.util.*;

public class LexemeComponent implements Component{
    private final LexemeType type;
    private final String value;

    private LexemeComponent(LexemeType type, String value) {
	   this.type = type;
	   this.value = value;
    }

    public static LexemeComponent word(String value) {
        return new LexemeComponent(LexemeType.WORD, value);
    }

    public static LexemeComponent expression(String value) {
        return new LexemeComponent(LexemeType.EXPRESSION, value);
    }


    public LexemeType getType() {
	   return type;
    }

    public String getValue() {
	   return value;
    }

    @Override
    public List<Component> getChildren() {
	   return Collections.unmodifiableList(new ArrayList<>());
    }

    @Override
    public String toString() {
	   return "LexemeComponent{" + "type=" + type + ", value='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
	   if (this == o) {
		  return true;
	   }
	   if (o == null || getClass() != o.getClass()) {
		  return false;
	   }

	   LexemeComponent component = (LexemeComponent) o;

	   if (getType() != component.getType()) {
		  return false;
	   }
	   return getValue() != null ? getValue().equals(component.getValue()) : component
			 .getValue() == null;
    }

    @Override
    public int hashCode() {
	   int result = getType() != null ? getType().hashCode() : 0;
	   result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
	   return result;
    }
}
