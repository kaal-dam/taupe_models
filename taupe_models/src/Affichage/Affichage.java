package Affichage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//projection perspective 
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.tools.Tool;

import Tools.MouseController;
import Tools.ToolBox;
import Tools.Triangle;
import BDD.GTSFileChooser;

/**
 *
 * @author flo
 */
@SuppressWarnings("serial")
public class Affichage extends JPanel {

	public static Model model;
	public int ZOOM = 10;

	public Affichage(Model model, int w, int h) {
		this.model = model;
		this.setSize(w, h);
		MouseController mc = new MouseController();
		this.addMouseWheelListener(mc);
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
	}

	public void setListTriangle(List<Triangle> listTriangle) {
		this.model.triangle = listTriangle;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//degradé debut
		Graphics2D g1 = (Graphics2D) g;

		if(Tools.ToolBox.antiAlia)
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
        Rectangle bounds = getBounds();
        g1.setPaint(new GradientPaint(0, bounds.y, Color.WHITE,
                0, bounds.y + bounds.width, Color.GRAY));
        g1.fillRect(0, 0, bounds.width, bounds.height);
		//degrade fin
        
		this.setVisible(false);
		Collections.sort(model.triangle);
		for (Triangle i : model.triangle) {
			// on verifie que le triangle est bien dans le plan
			int demiLargeur = this.getHeight() / 2;
			int demiLongueur = this.getWidth() / 2;

			if (ToolBox.arrete) {
				g1.setColor(Color.BLACK);
				g1.drawLine((int) (i.matrixPoint[0][0]) + demiLongueur,
						(int) (i.matrixPoint[0][1]) + demiLargeur,
						(int) (i.matrixPoint[1][0]) + demiLongueur,
						(int) (i.matrixPoint[1][1]) + demiLargeur);
				g1.drawLine((int) (i.matrixPoint[0][0]) + demiLongueur,
						(int) (i.matrixPoint[0][1]) + demiLargeur,
						(int) (i.matrixPoint[2][0]) + demiLongueur,
						(int) (i.matrixPoint[2][1]) + demiLargeur);
				g1.drawLine((int) (i.matrixPoint[2][0]) + demiLongueur,
						(int) (i.matrixPoint[2][1]) + demiLargeur,
						(int) (i.matrixPoint[1][0]) + demiLongueur,
						(int) (i.matrixPoint[1][1]) + demiLargeur);
			}
			if(ToolBox.face){
				int[] x, y;
				x = new int[3];
				y = new int[3];
				for (int idx = 0; idx < 3; idx++) {
					// utiliser la matrice de point de chaque triangle
					x[idx] = (int) (i.matrixPoint[idx][0]) + demiLongueur;
					y[idx] = (int) (i.matrixPoint[idx][1]) + demiLargeur;
				}
				g1.setColor(ToolBox.getColor(i));
				g1.fillPolygon(x, y, 3);
			}
			if(ToolBox.point){
				int tailleDot = 3;
				if(ToolBox.arrete)
					g1.setColor(Color.RED);
				else
					g1.setColor(Color.black);
				g1.fillOval((int) i.matrixPoint[0][0] + demiLongueur, 
						(int) i.matrixPoint[0][1] + demiLargeur, tailleDot, tailleDot);
				g1.fillOval((int) i.matrixPoint[1][0] + demiLongueur,
						(int) i.matrixPoint[1][1] + demiLargeur, tailleDot, tailleDot);
				g1.fillOval((int) i.matrixPoint[2][0] + demiLongueur,
						(int) i.matrixPoint[2][1] + demiLargeur, tailleDot, tailleDot);
			}
		}
		//on print l'axe
		g1.setStroke(new BasicStroke(4));
		g1.setColor(Color.RED);
		g1.drawLine(0+30, 0+30 , (int) ToolBox.axe[0][0] + 30, (int) ToolBox.axe[0][1] + 30);
		g1.setColor(Color.green);
		g1.drawLine(0+30, 0+30 , (int) ToolBox.axe[1][0] + 30, (int) ToolBox.axe[1][1] + 30);
		g1.setColor(Color.blue);
		g1.drawLine(0+30, 0+30 , (int) ToolBox.axe[2][0] + 30, (int) ToolBox.axe[2][1] + 30);
		this.setVisible(true);
	}
}
