package test.tap.datafile;

import com.tap.dataframe.DataFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

// https://www.vogella.com/tutorials/JUnit/article.html
public class DataFrameTest {

	DataFrame dataFrame;


	@BeforeEach
	void setUp() {

	}
	// TODO fer test més genèric. No de una implementació
	@Test
	@DisplayName("Can sort column")
	void testCanSortColumn() {
		String fileContent = """
			Name;"Number of pages";Editorial
			"Design patterns";364;Pearson
			"Clean architecture";320;Anaya
			""";


//		dataFrame = new CsvDataFrameFactory().makeDataFrame();
//		try {
//			dataFrame.loadContent(fakeScanner(fileContent));
//		} catch (InvalidFileFormatException e) {
//			fail();
//		}
//
//		List<String> result = dataFrame.sort("Number of pages", new NumberAscending());
//
//		assert result.get(0).equals("320");
//		assert result.get(1).equals("364");
	}

	/**
	 * @param fileContent content of the file to mock
	 * @return mocked scanner
	 * @author http://www.javased.com/?post=1647907
	 */
	private Scanner fakeScanner(String fileContent) {
		System.setIn(new ByteArrayInputStream(fileContent.getBytes()));
		File fake = new File()

		return new Scanner(System.in);
	}
}
