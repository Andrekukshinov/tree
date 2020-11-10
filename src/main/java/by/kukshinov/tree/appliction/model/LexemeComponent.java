package by.kukshinov.tree.appliction.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LexemeComponent implements Component {
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
	   return Collections.emptyList();
    }

    @Override
    public String toString() {
	   return "LexemeComponent{" + "type=" + type + ", value='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object thatComponent) {
	   if (this == thatComponent) {
		  return true;
	   }
	   if (thatComponent == null || getClass() != thatComponent.getClass()) {
		  return false;
	   }

	   LexemeComponent component = (LexemeComponent) thatComponent;

	   if (getType() != component.getType()) {
		  return false;
	   }
	   String thatComponentValue = component.getValue();
	   return getValue() != null ? getValue()
			 .equals(thatComponentValue) : thatComponentValue == null;
    }

    @Override
    public int hashCode() {
	   int result = getType() != null ? getType().hashCode() : 0;
	   result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
	   return result;
    }
}
