package Tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.SwingUtilities;

import Main.MainClass;

/**
 * 
 * Classe qui gere le controle du model a la souris
 *
 */
public class MouseController implements MouseWheelListener, MouseListener,
		MouseMotionListener {

	private boolean clic = false;
	private int x, y;


	// utiliser le produit matricielle !
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		class ThreadZoom extends Thread {
			List<Triangle> list;
			double[][] sens;

			public ThreadZoom(List<Triangle> l, double[][] s) {
				sens = s;
				list = l;
			}

			@Override
			public void run() {
				for (int i = list.size() / 2; i < list.size(); i++) {
					list.get(i).matrixPoint = ToolBox.produitMatriciel(list.get(i).matrixPoint,sens);
				}
			}
		}
		if (e.getPreciseWheelRotation() < 0) {
			ThreadZoom th = new ThreadZoom(MainClass.model.triangle, ToolBox.mZoomIn);
			th.start();
			for (int i = 0; i < MainClass.model.triangle.size()/2; i++) {
				MainClass.model.triangle.get(i).matrixPoint = ToolBox.produitMatriciel(MainClass.model.triangle.get(i).matrixPoint,
						ToolBox.mZoomIn);
			}
			while(th.isAlive());
		}
		if (e.getPreciseWheelRotation() > 0) {
			ThreadZoom th = new ThreadZoom(MainClass.model.triangle, ToolBox.mZoomOut);
			th.start();
			for (int i = 0; i < MainClass.model.triangle.size()/2; i++) {
				MainClass.model.triangle.get(i).matrixPoint = ToolBox.produitMatriciel(MainClass.model.triangle.get(i).matrixPoint,
						ToolBox.mZoomOut);
			}
			while(th.isAlive());
		}
		MainClass.aff.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clic = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		if (!clic) {
			x = e.getX();
			y = e.getY();
			clic = true;
		}
		// ROTATION
		// *******************************************************************
		if (SwingUtilities.isLeftMouseButton(e)) {
			// THREAD DE ROTATION X
			class ThreadRotationX extends Thread {
				List<Triangle> list;

				public ThreadRotationX(List<Triangle> t) {
					this.list = t;
				}

				@Override
				public void run() {
					for (int i = list.size() / 2; i < list.size(); i++) {
						list.get(i).matrixPoint = ToolBox.produitMatriciel(
								list.get(i).matrixPoint,
								ToolBox.mRotY(0.1 * (double) (e.getX() - x)));
					}
				}
			}

			if (x != e.getX()) {
				ThreadRotationX th = new ThreadRotationX(
						MainClass.model.triangle);
				th.start();
				for (int i = 0; i < MainClass.model.triangle.size() / 2; i++) {
					MainClass.model.triangle.get(i).matrixPoint = ToolBox
							.produitMatriciel(
									MainClass.model.triangle.get(i).matrixPoint,
									ToolBox.mRotY(0.1 * (double) (e.getX() - x)));
				}
				while (th.isAlive()) {
				}
			}

			// THREAD DE ROTATION Y
			class ThreadRotationY extends Thread {
				List<Triangle> list;

				public ThreadRotationY(List<Triangle> t) {
					this.list = t;
				}

				@Override
				public void run() {
					for (int i = list.size() / 2; i < list.size(); i++) {
						list.get(i).matrixPoint = ToolBox.produitMatriciel(
								list.get(i).matrixPoint,
								ToolBox.mRotX(0.1 * (double) (y - e.getY())));
					}
				}
			}

			if (y != e.getY()) {
				ThreadRotationY th = new ThreadRotationY(
						MainClass.model.triangle);
				th.start();
				for (int i = 0; i < MainClass.model.triangle.size() / 2; i++) {
					MainClass.model.triangle.get(i).matrixPoint = ToolBox
							.produitMatriciel(
									MainClass.model.triangle.get(i).matrixPoint,
									ToolBox.mRotX(0.1 * (double) (y - e.getY())));
				}
				while (th.isAlive()) {
				}
			}
		}
		// TRANSLATION
		// *************************************************************************
		if (SwingUtilities.isRightMouseButton(e)) {
			// THREAD DE TRANSLATION
			class ThreadTrans extends Thread {
				List<Triangle> list;

				public ThreadTrans(List<Triangle> l) {
					list = l;
				}

				@Override
				public void run() {
					for (int i = list.size() / 2; i < list.size(); i++) {
						list.get(i).matrixPoint = ToolBox.produitMatriciel(list
								.get(i).matrixPoint, ToolBox.mTrans(
								 ((double) (e.getX() - x)),
								 ((double) (e.getY() - y))));
					}
				}
			}

			ThreadTrans th = new ThreadTrans(MainClass.model.triangle);
			th.start();
			for (int i = 0; i < MainClass.model.triangle.size() / 2; i++)
				MainClass.model.triangle.get(i).matrixPoint = ToolBox
						.produitMatriciel(
								MainClass.model.triangle.get(i).matrixPoint,
								ToolBox.mTrans( ((double) (e.getX() - x)),
										 ((double) (e.getY() - y))));
			while (th.isAlive()) {
			}
		}
		MainClass.aff.repaint();
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}