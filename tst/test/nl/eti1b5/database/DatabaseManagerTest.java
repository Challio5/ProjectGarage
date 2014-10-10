package test.nl.eti1b5.database;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import nl.eti1b5.database.DatabaseManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		DatabaseManager manager = DatabaseManager.getInstance();
		assertNotNull(manager);
	}

	@Test
	public void testGetConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

}
