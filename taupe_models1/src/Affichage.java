/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author flo
 */
public class Affichage extends JPanel{
    List<Triangle> listTriangle;
    public Affichage(Model model){
        listTriangle = model.triangle;
    }

    public void setListTriangle(List<Triangle> listTriangle) {
        this.listTriangle = listTriangle;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Collections.sort(listTriangle);
        for(Triangle i : listTriangle){
            int[] x, y;
            x = new int[3];
            y = new int[3];
            for(int idx = 0; idx < 3; idx++){
                x[idx] = i.point.get(idx).x;
                y[idx] = i.point.get(idx).y;
            }
            g.setColor(Color.GRAY);
            g.drawPolygon(x, y, 3);
        }
    }
}
