package Affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Main.MainClass;
import Tools.*;

public class Decoupe extends JPanel implements MouseWheelListener{
	List<Polygon> list;
	double tr;
	Model model;
	int x[];
	int y[];
	
	public Decoupe(float t) {
		tr = t;
		model = MainClass.model;
		list = new ArrayList<Polygon>();
		
		list.add(new Polygon());
		tranche(20);
		this.addMouseWheelListener(this);
		this.repaint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(e.getPreciseWheelRotation() < 0){
			tr = tr + 5;
			tranche((int) tr);
			this.repaint();
		}else if(e.getPreciseWheelRotation() > 0){
			tr = tr - 5;
			tranche((int) tr);
			this.repaint();
		}
	}
	
	public void tranche(int z){
		list.get(0).points.clear();
		for(Triangle tri : model.triangle){
			try{
				double[] tmp = getInter(z, tri.matrixPoint[0], tri.matrixPoint[1]);
				list.get(0).points.add(new Point(tmp[0], tmp[1], tmp[2]));
			}catch(Exception e){}
			try{
				double[] tmp = getInter(z, tri.matrixPoint[0], tri.matrixPoint[2]);
				list.get(0).points.add(new Point(tmp[0], tmp[1], tmp[2]));
			}catch(Exception e){}
			try{
				double[] tmp = getInter(z, tri.matrixPoint[1], tri.matrixPoint[2]);
				list.get(0).points.add(new Point(tmp[0], tmp[1], tmp[2]));
			}catch(Exception e){}
		}
		list.get(0).initMatrice();
		
		x = new int[list.get(0).matrice.length];
		y = new int[list.get(0).matrice.length];
		for(int i = 0; i < list.get(0).matrice.length; i++){
			x[i] = (int) (list.get(0).matrice[i][0]) + 400;
			y[i] = (int) (list.get(0).matrice[i][1]) + 400;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.removeAll();
		g.setColor(Color.BLACK);
		for(int i =0; i < list.get(0).matrice.length; i++){
			g.fillOval((int) list.get(0).matrice[i][0] + 400, (int) list.get(0).matrice[i][1] + 400, 2, 2);
		}
		//g.fillPolygon(x, y, x.length);
	}
	
	public double[] getInter(double z, double[] a, double[] b) throws Exception{
		double[] i = new double[3];

		double[] ab = new double[] { b[0] - a[0], b[1] - a[1], b[2] - a[2] };
		double k = (z - a[2]) / ab[2];
		if (k <= 1 && k >= 0) {
			i[0] = a[0] + ab[0] * k;
			i[1] = a[1] + ab[1] * k;
			i[2] = z;
		}else{
			throw new Exception("Aucune intersection");
		}
		
		return i;
	}

}
