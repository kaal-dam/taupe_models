/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flo
 */
public class Triangle implements Comparable<Triangle>{

    List<Segment> segment;
    List<Point> point;

    public Triangle(Segment s1, Segment s2, Segment s3) {
        segment = new ArrayList<>();
        point = new ArrayList<>();

        segment.add(s1);
        segment.add(s2);
        segment.add(s3);

        for (Segment s : segment) {
            if (!point.contains(s.getPt1())) {
                point.add(s.getPt1());
            }
            if (!point.contains(s.getPt2())) {
                point.add(s.getPt2());
            }
        }
    }

    public Point getBarycentre(){ 
        int bx = 0, by = 0, bz = 0;
        for(Point p : point){
            bx += p.x;
            by += p.y;
            bz += p.z;
        }
        
        return new Point(bx/3, by/3, bz/3);
    }
    
    @Override
    public int compareTo(Triangle t) {
        if(this.getBarycentre().z > t.getBarycentre().z){
            return -1;
        }else if(this.getBarycentre().z < t.getBarycentre().z){
            return 1;
        }else{
            return 0;
        }
    }

}
