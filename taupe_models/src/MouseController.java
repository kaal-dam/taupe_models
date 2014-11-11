import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseController implements MouseWheelListener {
	
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
}
