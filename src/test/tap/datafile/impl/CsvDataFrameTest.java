package test.tap.datafile.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.factory.CsvDataFrameFactory;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.sort.NumberAscending;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

// https://www.vogella.com/tutorials/JUnit/article.html
public class CsvDataFrameTest {

	DataFrame dataFrame;
//	@BeforeEach
//	void setUp() {
//
//	}

//	@Test
//	@DisplayName("Can load CSV file")
//	void testLoadCsv() {
//		// PREPARATION
//		String fileContent = """
//			Name;"Number of pages";Editorial
//			"Design patterns";364;Pearson
//			"Clean architecture";320;Anaya
//			""";
//
//		// execution
//		dataFrame = new CsvDataFrameFactory().makeDataFrame();
//
//		try {
//			dataFrame.loadContent(fakeScanner(fileContent));
//		} catch (InvalidFileFormatException e) {
//			fail();
//		}
//
//		// assertion
//		assert dataFrame.size() == 2;
//		assert dataFrame.columns() == 3;
//
//		assertAll("Content has been loaded",
//			() -> assertEquals("Design patterns", dataFrame.iat(0, 0)),
//			() -> assertEquals("364", dataFrame.iat(0, 1)),
//			() -> assertEquals("Pearson", dataFrame.iat(0, 2)),
//			() -> assertEquals("Clean architecture", dataFrame.iat(1, 0)),
//			() -> assertEquals("320", dataFrame.iat(1, 1)),
//			() -> assertEquals("Anaya", dataFrame.iat(1, 2))
//		);
//	}
//
//	@Test
//	@DisplayName("Cannot add row with different number of columns")
//	void testCannotAddRowWithDifferentNumberOfColumns() {
//		String fileContent = """
//			Name;"Number of pages";Editorial
//			"Design patterns";364;Pearson;ERROR
//			"Clean architecture";320;Anaya
//			""";
//
//		dataFrame = new CsvDataFrameFactory().makeDataFrame();
//		Exception exception = assertThrows(ItemWithIncorrectNumberOfAttributesException.class, () -> {
//			dataFrame.loadContent(fakeScanner(fileContent));
//		});
//
//		assertEquals("Item #1 expected to be 3 attributes long. Found 4", exception.getMessage());
//	}
//
//	/**
//	 * @param fileContent content of the file to mock
//	 * @return mocked scanner
//	 * @author http://www.javased.com/?post=1647907
//	 */
//	private Scanner fakeScanner(String fileContent) {
//		System.setIn(new ByteArrayInputStream(fileContent.getBytes()));
//
//		return new Scanner(System.in);
//	}
}
