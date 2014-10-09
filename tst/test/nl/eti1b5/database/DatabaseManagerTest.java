package test.nl.eti1b5.database;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import nl.eti1b5.database.DatabaseManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseManagerTest {

	private DatabaseManager manager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = DatabaseManager.getInstance();
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
		Connection connection = manager.getConnection();
		assertNotNull(connection);
	}

	@Test
	public void testCreateConnection() throws IOException, SQLException {
		manager.createConnection();
	}

	@Test
	public void testCloseConnection() throws IOException, SQLException {
		manager.createConnection();
		manager.closeConnection();
		assertNull(manager.getConnection());
	}

}
