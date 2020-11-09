package by.kukshinov.tree.appliction.data;


import org.testng.Assert;
import org.testng.annotations.Test;

public class FileDataReaderTest {
    private static final String FILE_PATH = "src/test/resources/txt.txt";
    private static final String EXPECTED = "ejiafdoiJDFO EWPOJPF KPRO.\n" + " EFIJFIWEJF\n" + " ESOIJFIQESJFO'QWE\n";
    public static final String INVALID_PATH = "FILE_PATH";

    @Test
    public void testReadDataShouldReadDataFromFileAndReturnString() throws DataException {
	   FileDataReader dataReader = new FileDataReader();

	   String actual = dataReader.readData(FILE_PATH);

	   Assert.assertEquals(actual, EXPECTED);
    }

    @Test(expectedExceptions = DataException.class)//expected
    public void testReadDataShouldThrowException() throws DataException {
	   FileDataReader dataReader = new FileDataReader();

	   String actual = dataReader.readData(INVALID_PATH);
    }
}
