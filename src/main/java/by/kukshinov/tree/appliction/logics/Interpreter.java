package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.logics.expressions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
    private static final String SPACES = "\\s";
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private final ArrayList<AbstractMathExpression> listExpression = new ArrayList<>();


    private void parse(String expression) {
	   for (String lexeme : expression.split(SPACES)) {
		  if (lexeme.isEmpty()) {
			 continue;
		  }
		  char temp = lexeme.charAt(0);
		  switch (temp) {
			 case PLUS:
				listExpression.add(new TerminalExpressionPlus());
				break;
			 case MINUS:
				listExpression.add(new TerminalExpressionMinus());
				break;
			 case MULTIPLY:
				listExpression.add(new TerminalExpressionMultiply());
				break;
			 case DIVIDE:
				listExpression.add(new TerminalExpressionDivide());
				break;
			 default:
				Scanner scan = new Scanner(lexeme);
				if (scan.hasNextInt()) {
				    listExpression.add(new NonTerminalExpressionNumber(scan.nextInt()));
				}
		  }
	   }
    }

    public String calculate(String expression) {
	   parse(expression);
	   Context context = new Context();
	   for (AbstractMathExpression terminal : listExpression) {
		  terminal.interpret(context);
	   }
	   return context.popValue().toString();
    }
}
