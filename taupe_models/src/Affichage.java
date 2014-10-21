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
    List<Triangle> listTriangle;
    
    public int ZOOM = 10;
    public static float ROTATION_X = 1;
    
    public Affichage(Model model){
        listTriangle = model.triangle;
        this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				if(e.getPreciseWheelRotation() > 0){
					ROTATION_X = (float) Math.cos(15);
				}
			}
		});
    }

    public void setListTriangle(List<Triangle> listTriangle) {
        this.listTriangle = listTriangle;
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
        Collections.sort(listTriangle);
        for(Triangle i : listTriangle){
            int[] x, y;
            x = new int[3];
            y = new int[3];
            for(int idx = 0; idx < 3; idx++){
                x[idx] = (int) (i.point.get(idx).x)+ 400;
                y[idx] = (int) ( i.point.get(idx).y) + 300;
            }
            g.setColor(Color.LIGHT_GRAY);
            g.fillPolygon(x, y, 3);
        }
    }
    
   
}
