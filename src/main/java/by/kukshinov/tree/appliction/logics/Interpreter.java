package by.kukshinov.tree.appliction.logics;

import by.kukshinov.tree.appliction.logics.expressions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
    private final ArrayList<AbstractMathExpression> listExpression = new ArrayList<>();


    private void parse(String expression) {
	   for (String lexeme : expression.split("\\s")) {
		  if (lexeme.isEmpty()) {
			 continue;
		  }
		  char temp = lexeme.charAt(0);
		  switch (temp) {
			 case '+':
				listExpression.add(new TerminalExpressionPlus());
				break;
			 case '-':
				listExpression.add(new TerminalExpressionMinus());
				break;
			 case '*':
				listExpression.add(new TerminalExpressionMultiply());
				break;
			 case '/':
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
