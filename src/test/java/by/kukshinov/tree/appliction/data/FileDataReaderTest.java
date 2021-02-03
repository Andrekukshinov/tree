package by.kukshinov.tree.appliction.data;


import org.testng.Assert;
import org.testng.annotations.Test;

public class FileDataReaderTest {
    private static final String FILE_PATH = "src/test/resources/txt.txt";
    private static final String EXPECTED =
		  " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ea, tenetur.\n" +
		  " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dicta, ratione recusandae!\n" +
		  " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda atque eaque neque?";
    public static final String INVALID_PATH = "FILE_PATH";

    @Test
    public void testReadDataShouldReadDataFromFileAndReturnString() throws DataException {
	   FileDataReader dataReader = new FileDataReader();

	   String actual = dataReader.read(FILE_PATH);

	   Assert.assertEquals(actual, EXPECTED);
    }

    @Test(expectedExceptions = DataException.class)//expected
    public void testReadDataShouldThrowException() throws DataException {
	   FileDataReader dataReader = new FileDataReader();

	   String actual = dataReader.read(INVALID_PATH);
    }
}
