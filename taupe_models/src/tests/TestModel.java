package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Affichage.Model;

public class TestModel {
	
	@Test
	public void testIsNumber() {
		assertTrue(Model.isNumber("123"));
		assertTrue(Model.isNumber("3.14"));
		assertFalse(Model.isNumber("test"));
	}

}
