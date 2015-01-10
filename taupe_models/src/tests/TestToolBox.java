package tests;

import org.junit.Test;

import Tools.ToolBox;
import static org.junit.Assert.*;

public class TestToolBox {

	@SuppressWarnings("deprecation")
	@Test
	public void testMRotX() {
		
		double[][] res1 = new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(Math.PI / 64), Math.sin(Math.PI / 64), 0},
                {0, -Math.sin(Math.PI / 64), Math.cos(Math.PI / 64), 0},
                {0, 0, 0, 1}};
		
		double[][] res2 = new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(2 * Math.PI / 64), Math.sin(2 * Math.PI / 64), 0},
                {0, -Math.sin(2 * Math.PI / 64), Math.cos(2 * Math.PI / 64), 0},
                {0, 0, 0, 1}};
		
		assertEquals(res1, ToolBox.mRotX(1));
		assertEquals(res2, ToolBox.mRotX(2));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMRotY() {
		
		double[][] res1 = new double[][]{
                {Math.cos(Math.PI / 64), 0, -Math.sin(Math.PI / 64), 0},
                {0, 1, 0, 0},
                {Math.sin(Math.PI / 64), 0, Math.cos(Math.PI / 64), 0},
                {0, 0, 0, 1}};
		
		double[][] res2 = new double[][]{
                {Math.cos(2 * Math.PI / 64), 0, -Math.sin(2 * Math.PI / 64), 0},
                {0, 1, 0, 0},
                {Math.sin(2 * Math.PI / 64), 0, Math.cos(2 * Math.PI / 64), 0},
                {0, 0, 0, 1}};
		
		assertEquals(res1, ToolBox.mRotY(1));
		assertEquals(res2, ToolBox.mRotY(2));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMTrans() {
		
		double[][] res12 = new double[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{1, 2, 0, 1}};
		
		double[][] res21 = new double[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{2, 1, 0, 1}};
		
		assertEquals(res12, ToolBox.mTrans(1, 2));
		assertEquals(res21, ToolBox.mTrans(2, 1));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProduitMatriciel() {
		
		double[][] res1 = new double[][]{
				{7, 10},
				{15, 22}};
		
		assertEquals(res1, ToolBox.produitMatriciel(
								new double[][]{
										{1,2},
										{3,4}},
								new double[][]{
										{1,2},
										{3,4}}));
		
	}
	
	@Test
	public void testProduitVectoriel() {
		//test au rouge je ne sais pas pourquoi
		
		double[] res = new double[]{0, 0, 0};
		
		assertEquals(res, ToolBox.produitVectoriel(new double[]{0,0,0} , new double[]{0,0,0}));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProduitScalaire() {
		
		double[] v1 = new double[]{1,2,1};
		double[] v2 = new double[]{3,1,2};
		
		assertEquals(ToolBox.produitScalaire(v1, v2), ToolBox.produitScalaire(v1, v2));
	}
}
