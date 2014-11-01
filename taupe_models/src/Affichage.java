/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
/**
 *
 * @author flo
 */
public class Affichage extends JPanel{
    public static Model model;
    
    public int ZOOM = 10;
    
    public Affichage(Model model){
        this.model = model;
        this.addMouseWheelListener(new MouseController());
    }

    public void setListTriangle(List<Triangle> listTriangle) {
        this.model.triangle = listTriangle;
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	this.setVisible(false);
        Collections.sort(model.triangle);
        for(Triangle i : model.triangle){
            int[] x, y;
            x = new int[3];
            y = new int[3];
            for(int idx = 0; idx < 3; idx++){
                x[idx] = (int) (i.point.get(idx).x ) + 400;
                y[idx] = (int) (i.point.get(idx).y ) + 300;
            }
            g.setColor(Color.LIGHT_GRAY);
            g.fillPolygon(x, y, 3);
        }
        this.setVisible(true);
    }
    
   
}
