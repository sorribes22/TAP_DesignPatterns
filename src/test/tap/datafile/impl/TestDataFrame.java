package test.tap.datafile.impl;


import com.tap.dataframe.query.IntComparasion;
import com.tap.dataframe.query.Operator;
import com.tap.dataframe.query.Query;
import com.tap.dataframe.sort.NumberAscending;
import com.tap.dataframe.visitor.DataFrameVisitor;
import com.tap.dataframe.visitor.SumVisitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class TestDataFrame {

	FakeDataFrame testDF = new FakeDataFrame();

	@After
	/**
	 * Tear down the DataFrame
	 */
	public void tearDown() {
		testDF = null;
	}

	@Before
	/**
	 * Initialize a new dummy DataFrame
	 */
	public void setup() {
		Map<String, List<String>> content = new LinkedHashMap<>();
		List<String> ages = new ArrayList<>(Arrays.asList("50", "32", "19", "22"));
		List<String> names = new ArrayList<>(Arrays.asList("Roger", "Alex", "Gerard", "Eric"));
		List<String> mobile = new ArrayList<>(Arrays.asList("111111", "222222", "333333", "444444"));
		List<String> workerID = new ArrayList<>(Arrays.asList("10", "20", "30", "40"));

		content.put("Age", ages);
		content.put("Name", names);
		content.put("Mobile", mobile);
		content.put("WorkerID", workerID);

		testDF.fillTestContent(content);
	}

	@Test
	/**
	 *  Test to check at operation
	 */
	public void testAtOperation() {
		String atResult = testDF.at(1, "Name");
		assertEquals("Alex", atResult);

	}

	@Test
	/**
	 * Test to check iat operation
	 */
	public void testIatOperation() {
		String iatResult = testDF.iat(2, 3);
		assertEquals("30", iatResult);
	}

	@Test
	/**
	 * Test to check sort
	 */
	public void testSort() {
		Comparator<String> comparator = new NumberAscending();
		List<String> sortAges = testDF.sort("Age", comparator);
		List<String> ages = new ArrayList<>(Arrays.asList("19", "22", "32", "50"));
		assertEquals(ages, sortAges);
	}

	@Test
	/**
	 * Test to chek queries
	 */
	public void testQuery() {
		Query<Map<String, String>> query = new IntComparasion("Age", Operator.GREATER, "30");
		Map<String, List<String>> queryMap = testDF.query(query);
		Map<String, List<String>> testMap = new HashMap() {{
			put("Name", new ArrayList<>(Arrays.asList("Roger", "Alex")));
			put("Age", new ArrayList<>(Arrays.asList("50", "32")));
			put("Mobile", new ArrayList<>(Arrays.asList("111111", "222222")));
			put("WorkerID", new ArrayList<>(Arrays.asList("10", "20")));
		}};
		assertEquals(queryMap, testMap);
	}

	@Test
	/**
	 * Test to check the Visitor Design pattern
	 */
	public void testVisitor() {
		DataFrameVisitor visitorDF = new SumVisitor();
		visitorDF.setLabelToApply("WorkerID");
		testDF.accept(visitorDF);
		double visitorResult = visitorDF.getResult();
		assertEquals(visitorResult, 100, 0);
	}
}
