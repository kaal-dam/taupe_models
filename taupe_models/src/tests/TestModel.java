package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;

import org.junit.Test;

import Affichage.Model;

public class TestModel {
	
	Model model = new Model("model/epcot.gts");
	BufferedReader bufferReader = null;
	
	
	
	@Test
	public void testIsNumber() {
		assertTrue(Model.isNumber("123"));
		assertTrue(Model.isNumber("3.14"));
		assertFalse(Model.isNumber("test"));
	}

}
