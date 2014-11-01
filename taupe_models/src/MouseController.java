import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseController implements MouseWheelListener {

	float Rotation = (float) Math.PI/2;
	
	public MouseController() {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getPreciseWheelRotation() > 0){
			for(Triangle t :Projet_modelisasion_S3_test.model.triangle){
				for(Point p : t.point){
					float tmpY = p.y;
					float tmpZ = p.z;
					p.x = p.x;
					p.y = (0 - (float) Math.sin(Rotation))*tmpZ +  ((float) Math.cos(Rotation))*tmpY;
					p.z = ((float) Math.cos(Rotation)*tmpZ) + ((float) Math.sin(Rotation))*tmpY;
				}
			}
			System.out.println(Projet_modelisasion_S3_test.model.triangle.get(0).point.get(0));
			Projet_modelisasion_S3_test.aff.repaint();
		}
	}
}
