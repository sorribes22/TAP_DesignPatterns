package test.tap.datafile;

import com.tap.datafile.CsvDataFrame;
import com.tap.datafile.ItemWithIncorrectNumberOfAttributesException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

// https://www.vogella.com/tutorials/JUnit/article.html
public class CsvDataFrameTest {
// TODO ASK estem seguin la convenció corresponent per l'estructura de fitxers dels tests?

	CsvDataFrame dataFrame;
//	@BeforeEach
//	void setUp() {
//
//	}

	@Test
	@DisplayName("Can load CSV file")
	void testLoadCsv() {
		String fileContent = """
			Nom;"Número de pàgines";Editorial
			"Patrones de diseño";364;Pearson
			"Arquitectura limpia";320;Anaya
			""";

		try {
			dataFrame = new CsvDataFrame(mockScanner(fileContent));
		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			fail();
		}

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
			dataFrame = new CsvDataFrame(mockScanner(fileContent));
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
	@Contract("_ -> new")
	private @NotNull Scanner mockScanner(@NotNull String fileContent) {
		System.setIn(new ByteArrayInputStream(fileContent.getBytes()));

		return new Scanner(System.in);
	}
}
