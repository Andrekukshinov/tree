package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ParserTest {
    private static final String SENTENCE_FOR_SPLITTING = " A [4 5+] years.";
    private static final String PARAGRAPH_FOR_SPLITTING = " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.";
    private static final String TEXT_FOR_SPLITTING = "" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" + " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n";

    @DataProvider
    public Object[][] getComponentWithExpression(Method method) {
	   Object[][] result;
	   LexemeComponent a = LexemeComponent.word(" A");
	   LexemeComponent space = LexemeComponent.word(" ");
	   LexemeComponent expression = LexemeComponent.expression("[4 5+]");
	   LexemeComponent years = LexemeComponent.word(" years");
	   LexemeComponent dot = LexemeComponent.word(".");
	   List<Component> lexemes = Arrays.asList(a, space, expression, years, dot);
	   Component sentence = new CompositeComponent(lexemes);
	   List<Component> sentences = Arrays
			 .asList(sentence, sentence, sentence, sentence, sentence, sentence,
				    sentence);
	   Component paragraph = new CompositeComponent(sentences);
	   List<Component> paragraphs = Arrays
			 .asList(paragraph, paragraph, paragraph, paragraph);
	   Component expected = new CompositeComponent(paragraphs);
	   String methodName = method.getName();
	   if ("testParseShouldParseGivenSentenceToComponent".equalsIgnoreCase(methodName)) {
		  result = new Object[1][1];
		  result[0][0] = sentence;
		  return result;
	   }
	   if ("testParseShouldParseGivenParagraphToComponent".equalsIgnoreCase(methodName)) {
		  result = new Object[1][2];
		  result[0][0] = paragraph;
		  result[0][1] = sentence;
		  return result;
	   }
//	   if ("testParseShouldParseGivenTextToComponent".equalsIgnoreCase(methodName)) {
	   result = new Object[1][2];
	   result[0][0] = expected;
	   result[0][1] = paragraph;
	   return result;

    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testParseShouldParseGivenSentenceToComponent(Component expected) {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();
	   SentenceParser parser = new SentenceParser(recognizer);
	   Component parsed = parser.parse(SENTENCE_FOR_SPLITTING);
	   Assert.assertEquals(parsed, expected);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testParseShouldParseGivenParagraphToComponent (Component expected, Component sentence) {
	   SentenceParser successor = Mockito.mock(SentenceParser.class);
	   ParagraphParser parser = new ParagraphParser(successor);
	   when(successor.parse(anyString())).thenReturn(sentence);


	   Component parsed = parser.parse(PARAGRAPH_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }

    @Test(dataProvider = "getComponentWithExpression")
    public void testParseShouldParseGivenTextToComponent(Component expected, Component paragraph) {
	   ParagraphParser successor = Mockito.mock(ParagraphParser.class);
	   TextParser parser = new TextParser(successor);
	   when(successor.parse(anyString())).thenReturn(paragraph);
	   Component parsed = parser.parse(TEXT_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }
}
// TODO: 09.11.2020 refactor 2 data providers into 1
