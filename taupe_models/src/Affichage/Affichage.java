package Affichage;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//projection perspective 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import Tools.MouseController;
import Tools.ToolBox;
import Tools.Triangle;
import BDD.GoToInsertFile;

/**
 *
 * @author flo
 */
public class Affichage extends JPanel {
	public static Model model;

	public int ZOOM = 10;

	public Affichage(Model model) {
		this.model = model;
		this.add(new GoToInsertFile(), BorderLayout.NORTH);
		this.addMouseWheelListener(new MouseController());
		this.addMouseListener(new MouseController());
		this.addMouseMotionListener(new MouseController());
	}

	public void setListTriangle(List<Triangle> listTriangle) {
		this.model.triangle = listTriangle;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setVisible(false);
		Collections.sort(model.triangle);
		for (Triangle i : model.triangle) {
			int[] x, y;
			x = new int[3];
			y = new int[3];
			for (int idx = 0; idx < 3; idx++) {
				// utiliser la matrice de point de chaque triangle
				x[idx] = (int) (i.matrixPoint[idx][0] * 10) + 400;
				y[idx] = (int) (i.matrixPoint[idx][1] * 10) + 300;
			}
			g.setColor(ToolBox.getColor(i));
			g.fillPolygon(x, y, 3);

			g.setColor(Color.BLACK);
			for (int idx = 0; idx < 3; idx++) {
				for (int idy = 0; idy < 3; idy++) {
					g.drawLine((int) (i.matrixPoint[idx][0] * 10) + 400,
							(int) (i.matrixPoint[idx][1] * 10) + 300,
							(int) (i.matrixPoint[idy][0] * 10) + 400,
							(int) (i.matrixPoint[idy][1] * 10) + 300);
				}
			}
		}
		this.setVisible(true);
	}

}
