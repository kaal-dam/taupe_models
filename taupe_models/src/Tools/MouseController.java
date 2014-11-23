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
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clic = false;
		System.out.println(clic);
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
		System.out.println(clic);
		// ROTATION
		//**************************************************************************************
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (e.getX() < x) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=x-e.getX(); i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationYAntiHorraire);
				}
				if (e.getY() < y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=y-e.getY(); i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXHorraire);
					}
				} else if (e.getY() > y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=e.getY()-y; i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXAntiHorraire);
					}
				}
			} else if (e.getX() > x) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=e.getX()-x; i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationYHorraire);
				}
				if (e.getY() < y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=y-e.getY(); i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXHorraire);
					}
				} else if (e.getY() > y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=e.getY()-y; i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXAntiHorraire);
					}
				}
			} else if (e.getY() < y) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=y-e.getY(); i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXHorraire);
				}
			} else if (e.getY() > y) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=e.getY()-y; i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mRotationXAntiHorraire);
				}
			}
		}
		// TRANSLATION
		// *************************************************************************
		if (SwingUtilities.isRightMouseButton(e)) {
			if (e.getX() < x) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=x-e.getX(); i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransXNeg);
				}
				if (e.getY() < y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=y-e.getY(); i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYNeg);
					}
				} else if (e.getY() > y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=e.getY()-y; i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYPos);
					}
				}
			} else if (e.getX() > x) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=e.getX()-x; i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransXPos);
				}
				if (e.getY() < y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=y-e.getY(); i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYNeg);
					}
				} else if (e.getY() > y) {
					for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
						//for(int i=e.getY()-y; i>0; --i)
							t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYPos);
					}
				}
			} else if (e.getY() < y) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=y-e.getY(); i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYNeg);
				}
			} else if (e.getY() > y) {
				for (Triangle t : Projet_modelisasion_S3_test.model.triangle) {
					//for(int i=e.getY(); i>0; --i)
						t.matrixPoint = ToolBox.produitMatriciel(t.matrixPoint, ToolBox.mTransYPos);
				}
			}
		}
		Projet_modelisasion_S3_test.aff.repaint();
		x = e.getX();
		y = e.getY();
		System.out.println(x+" "+y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}