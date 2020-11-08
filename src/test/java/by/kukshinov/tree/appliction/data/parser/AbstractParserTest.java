package by.kukshinov.tree.appliction.data.parser;

import by.kukshinov.tree.appliction.data.ExpressionRecognizer;
import by.kukshinov.tree.appliction.model.Component;
import by.kukshinov.tree.appliction.model.CompositeComponent;
import by.kukshinov.tree.appliction.model.LexemeComponent;
import by.kukshinov.tree.appliction.model.LexemeType;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AbstractParserTest {
    private static final String SENTENCE_FOR_SPLITTING = " A [4 5+] years.";
    private static final String PARAGRAPH_FOR_SPLITTING = " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.";
    private static final String TEXT_FOR_SPLITTING = "" +
		  " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" +
		  " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" +
		  " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n" +
		  " A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years. A [4 5+] years.\n";

    @Test
    public void testParseShouldParseGivenSentenceToComponent() {
	   ExpressionRecognizer recognizer = new ExpressionRecognizer();
	   SentenceParser parser = new SentenceParser(recognizer);
	   LexemeComponent a = LexemeComponent.word( " A");
	   LexemeComponent space = LexemeComponent.word( " ");
	   LexemeComponent expression = LexemeComponent.expression( "[4 5+]");
	   LexemeComponent years = LexemeComponent.word( " years");
	   LexemeComponent dot = LexemeComponent.word( ".");
	   List<Component> lexemes = Arrays.asList(a, space, expression, years, dot);
	   Component expected = new CompositeComponent(lexemes);

	   Component parsed = parser.parse(SENTENCE_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }

    @Test
    public void testParseShouldParseGivenParagraphToComponent() {
	   SentenceParser successor = Mockito.mock(SentenceParser.class);
	   ParagraphParser parser = new ParagraphParser(successor);
	   LexemeComponent a = LexemeComponent.word( " A");
	   LexemeComponent space = LexemeComponent.word( " ");
	   LexemeComponent expression = LexemeComponent.expression( "[4 5+]");
	   LexemeComponent years = LexemeComponent.word( " years");
	   LexemeComponent dot = LexemeComponent.word( ".");
	   List<Component> lexemes = Arrays.asList(a, space, expression, years, dot);
	   Component sentence = new CompositeComponent(lexemes);
	   when(successor.parse(anyString())).thenReturn(sentence);
	   List<Component> sentences = Arrays
			 .asList(sentence, sentence, sentence, sentence, sentence, sentence,
				    sentence);
	   Component expected = new CompositeComponent(sentences);


	   Component parsed = parser.parse(PARAGRAPH_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }

    @Test
    public void testParseShouldParseGivenTextToComponent() {
	   ParagraphParser successor = Mockito.mock(ParagraphParser.class);
	   TextParser parser = new TextParser(successor);
	   LexemeComponent a = LexemeComponent.word( " A");
	   LexemeComponent space = LexemeComponent.word( " ");
	   LexemeComponent expression = LexemeComponent.expression( "[4 5+]");
	   LexemeComponent years = LexemeComponent.word( " years");
	   LexemeComponent dot = LexemeComponent.word( ".");
	   List<Component> lexemes = Arrays.asList(a, space, expression, years, dot);
	   Component sentence = new CompositeComponent(lexemes);
	   List<Component> sentences = Arrays
			 .asList(sentence, sentence, sentence, sentence, sentence, sentence,
				    sentence);
	   Component paragraph = new CompositeComponent(sentences);
	   when(successor.parse(anyString())).thenReturn(paragraph);
	   List<Component> paragraphs = Arrays
			 .asList(paragraph, paragraph, paragraph, paragraph);
	   Component expected = new CompositeComponent(paragraphs);

	   Component parsed = parser.parse(TEXT_FOR_SPLITTING);

	   Assert.assertEquals(parsed, expected);
    }
}
// TODO: 07.11.2020 create data provider
