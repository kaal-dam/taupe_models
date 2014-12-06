package Tools;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import Main.Projet_modelisasion_S3_test;


public class MouseController implements MouseWheelListener, MouseListener,
		MouseMotionListener {

	private boolean clic = false;
	private int x, y;

	public MouseController() {
	}

	// utiliser le produit matricielle !
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getPreciseWheelRotation() < 0) {
			for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint,
						ToolBox.mZoomIn);
			}
		}
		if (e.getPreciseWheelRotation() > 0) {
			for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint,
						ToolBox.mZoomOut);
			}
		}
		Projet_modelisasion_S3_test.aff.repaint();
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
	public void mouseDragged(MouseEvent e) {
		if(!clic){
			x=e.getX();
			y=e.getY();
			clic = true;
		}
		// ROTATION
		//**************************************************************************************
		if (SwingUtilities.isLeftMouseButton(e)) {
			if(x!=e.getX()) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle)
					t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotY(0.1*(double) (e.getX()-x)));
			}
			
			if(y!=e.getY()) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle)
					t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotX(-0.1*(double) (e.getY()-y)));
			}
			
			/*for (Triangle t : Projet_modelisasion_S3_test.model.triangle)
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRot(-0.1*((double) (e.getY()-y)), 0.1*((double) (e.getX()-x))));*/
		}
		// TRANSLATION
		// *************************************************************************
		if (SwingUtilities.isRightMouseButton(e)) {
			for (Triangle t : Projet_modelisasion_S3_test.model.triangle)
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTrans(0.1*((double) (e.getX()-x)), 0.1*((double) (e.getY()-y)))); 
		}
		Projet_modelisasion_S3_test.aff.repaint();
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}