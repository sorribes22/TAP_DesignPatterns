package test.tap.datafile;

import com.tap.dataframe.CsvDataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

// https://www.vogella.com/tutorials/JUnit/article.html
public class CsvDataFrameTest {

	CsvDataFrame dataFrame;
//	@BeforeEach
//	void setUp() {
//
//	}

	@Test
	@DisplayName("Can load CSV file")
	void testLoadCsv() {
		// PREPARATION
		String fileContent = """
			Nom;"Número de pàgines";Editorial
			"Patrones de diseño";364;Pearson
			"Arquitectura limpia";320;Anaya
			""";

		// execution
		try {
			dataFrame = new CsvDataFrame(fakeScanner(fileContent));
		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			fail();
		}

		// assertion
		assert dataFrame.size() == 2;
		assert dataFrame.columns() == 3;

		assertAll("Content has been loaded",
			() -> assertEquals("Patrones de diseño", dataFrame.iat(0)),
			() -> assertEquals("364", dataFrame.iat(1)),
			() -> assertEquals("Pearson", dataFrame.iat(2)),
			() -> assertEquals("Arquitectura limpia", dataFrame.iat(3)),
			() -> assertEquals("320", dataFrame.iat(4)),
			() -> assertEquals("Anaya", dataFrame.iat(5))
		);
	}

	@Test
	@DisplayName("Cannot add row with different number of columns")
	void testCannotAddRowWithDifferentNumberOfColumns() {
		String fileContent = """
			Nom;"Número de pàgines";Editorial
			"Patrones de diseño";364;Pearson;"NOU CAMP ERRONI"
			"Arquitectura limpia";320;Anaya
			""";

		Exception exception = assertThrows(ItemWithIncorrectNumberOfAttributesException.class, () -> {
			dataFrame = new CsvDataFrame(fakeScanner(fileContent));
		});

		assertEquals("Item #1 expected to be 3 attributes long. Found 4", exception.getMessage());
	}

	/**
	 * TODO ASK cal filar tan prim?
	 *
	 * @author http://www.javased.com/?post=1647907
	 * @param fileContent content of the file to mock
	 * @return mocked scanner
	 */
	private Scanner fakeScanner(String fileContent) {
		System.setIn(new ByteArrayInputStream(fileContent.getBytes()));

		return new Scanner(System.in);
	}
}
