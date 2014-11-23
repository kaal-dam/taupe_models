package Tools;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flo
 */
public class Triangle implements Comparable<Triangle>{

    List<Segment> segment;
    public double[][] matrixPoint;

    public Triangle(Segment s1, Segment s2, Segment s3) {
        segment = new ArrayList<Segment>();
        List<Point> point = new ArrayList<Point>();

        segment.add(s1);
        segment.add(s2);
        segment.add(s3);

        for (Segment s : segment) {
            if (!existIn(point, s.getPt1())) {
                point.add(s.getPt1());
            }
            if (!existIn(point, s.getPt2())) {
                point.add(s.getPt2());
            }
        }
        
        matrixPoint = new double[3][4];
        
        for(int i = 0; i < point.size(); i++){
        	//System.out.println("i=" + i + " list size = " + point.size() + " pt@i = " + point.get(i));
        	matrixPoint[i][0] = point.get(i).x;
        	matrixPoint[i][1] = point.get(i).y;
        	matrixPoint[i][2] = point.get(i).z;
        	matrixPoint[i][3] = 1;
        }
    }

    private boolean existIn(List<Point> list, Point p){
    	for(Point tmp: list){
    		if(tmp.x == p.x && tmp.y == p.y && tmp.z == p.z){
    			return true;
    		}
    	}
    	return false;
    }
    	
    public Point getBarycentre(){ 
        double bx = 0, by = 0, bz = 0;
        for(int idx = 0; idx < matrixPoint.length; idx++){
            bx += matrixPoint[idx][0];
            by += matrixPoint[idx][1];
            bz += matrixPoint[idx][2];
        }
        
        return new Point(bx/3, by/3, bz/3);
    }
    
    @Override
    public int compareTo(Triangle t) {
        if(this.getBarycentre().z > t.getBarycentre().z){
            return 1;
        }else if(this.getBarycentre().z < t.getBarycentre().z){
            return -1;
        }else{
            return 0;
        }
    }

}
