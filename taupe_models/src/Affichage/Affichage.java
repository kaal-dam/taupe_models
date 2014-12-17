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
import BDD.GTSFileChooser;

/**
 *
 * @author flo
 */
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
        this.setVisible(false);
        Collections.sort(model.triangle);
        for (Triangle i : model.triangle) {
            //on verifie que le triangle est bien dans le plan
            int demiLargeur = this.getHeight() / 2;
            int demiLongueur = this.getWidth() / 2;
            if (true) {
                int[] x, y;
                x = new int[3];
                y = new int[3];
                for (int idx = 0; idx < 3; idx++) {
                    // utiliser la matrice de point de chaque triangle
                    x[idx] = (int) (i.matrixPoint[idx][0]) + demiLongueur;
                    y[idx] = (int) (i.matrixPoint[idx][1]) + demiLargeur;
                }
                g.setColor(ToolBox.getColor(i));
                g.fillPolygon(x, y, 3);

                /*
                g.setColor(Color.BLACK);
                g.drawLine((int) (i.matrixPoint[0][0]) + demiLongueur,
                        (int) (i.matrixPoint[0][1]) + demiLargeur,
                        (int) (i.matrixPoint[1][0]) + demiLongueur,
                        (int) (i.matrixPoint[1][1]) + demiLargeur);
                g.drawLine((int) (i.matrixPoint[0][0]) + demiLongueur,
                        (int) (i.matrixPoint[0][1]) + demiLargeur,
                        (int) (i.matrixPoint[2][0]) + demiLongueur,
                        (int) (i.matrixPoint[2][1]) + demiLargeur);
                g.drawLine((int) (i.matrixPoint[2][0]) + demiLongueur,
                        (int) (i.matrixPoint[2][1]) + demiLargeur,
                        (int) (i.matrixPoint[1][0]) + demiLongueur,
                        (int) (i.matrixPoint[1][1]) + demiLargeur);
                 */
            }
        }
        this.setVisible(true);
    }
}
