import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

public class MouseController implements MouseWheelListener, MouseListener, MouseMotionListener {
	
	private boolean clic = false;
	private int x, y;
	
	public MouseController() {
	}

	
	//utiliser le produit matricielle !
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getPreciseWheelRotation() > 0){
			for(Triangle t :Projet_modelisasion_S3_test.model.triangle){ 
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationY);
			}
			Projet_modelisasion_S3_test.aff.repaint();
		}
		if(e.getPreciseWheelRotation() < 0){
			for(Triangle t :Projet_modelisasion_S3_test.model.triangle){ 
				t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationX);
			}
			Projet_modelisasion_S3_test.aff.repaint();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		clic=true;
		x=e.getX();
		y=e.getY();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		clic=false;
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
		if(SwingUtilities.isLeftMouseButton(e)){
			if(e.getX()<x){
				for(Triangle t :Projet_modelisasion_S3_test.model.triangle){ 
					t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationY);
				}
				Projet_modelisasion_S3_test.aff.repaint();
			}
			if(e.getY()<y){
				for(Triangle t :Projet_modelisasion_S3_test.model.triangle){ 
					t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationX);
				}
				Projet_modelisasion_S3_test.aff.repaint();
			}
		}
		x=e.getX();
		y=e.getY();
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
}