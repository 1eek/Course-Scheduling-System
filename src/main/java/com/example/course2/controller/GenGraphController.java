package com.example.course2.controller;

import com.example.course2.CourseGraph;
import com.example.course2.pojo.Course;
import com.example.course2.pojo.Semester;
import com.example.course2.service.SemesterService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.HashMap;
import java.util.List;


public class GenGraphController {
    HashMap<String,Ellipse> nameToNode = new HashMap<>();
    SemesterService service = new SemesterService();
    List<Semester>  semesters = service.getSemester();
    @FXML
    private AnchorPane Container;
    int RX= 40;
    int RY = 40;

    public void initialize(){
        int width = 100;
        int height = 200;

        //画节点
        for(Semester s: semesters){
            for(String courseName: s.getCourses()){
                Ellipse node = new Ellipse(RX,RY);
                node.setCenterX(width);
                node.setCenterY(height);
                node.setFill(Color.GREENYELLOW);

                Label name =  new Label(courseName);
                name.setLayoutX(width - name.getWidth()-RX/2);
                name.setLayoutY(height-name.getHeight()/2-10);
                nameToNode.put(courseName,node);

                Container.getChildren().add(node);
                Container.getChildren().add(name);
                width+=100;
            }
            height+=100;
            width=100;
        }

        //画线
        for(Course c: CourseGraph.CourseList){
            Ellipse from = nameToNode.get(c.getName());
            for(int id:c.getAfter()){
                Course  curCourse =CourseGraph.Courses.get(id);
                Ellipse to = nameToNode.get(curCourse.getName());
               Line line =  createLine(from,to);
                Polygon polygon =  createArrowHead(line);
               Container.getChildren().add(line);
                Container.getChildren().add(polygon);
            }
        }

    }
    private Line createLine(Ellipse startEllipse, Ellipse endEllipse) {
        double angle = calculateAngle(startEllipse.getCenterX(), startEllipse.getCenterY(), endEllipse.getCenterX(), endEllipse.getCenterY());

        double startX = startEllipse.getCenterX()+RX*Math.cos( Math.toRadians(angle));
        double startY = startEllipse.getCenterY()+RX*Math.sin(Math.toRadians(angle));
        double endX = endEllipse.getCenterX()-RX*Math.cos(Math.toRadians(angle));
        double endY = endEllipse.getCenterY()-RX*Math.sin(Math.toRadians(angle));

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);

        return line;
    }

    private Polygon createArrowHead(Line line) {
        double arrowLength = 10;
        double arrowWidth = 5;
        double endX = line.getEndX();
        double endY = line.getEndY();
        double angle = Math.atan2(endY - line.getStartY(), endX - line.getStartX());

        Polygon arrowHead = new Polygon(
                endX, endY,
                endX - arrowLength * Math.cos(angle - Math.PI / 6), endY - arrowLength * Math.sin(angle - Math.PI / 6),
                endX - arrowLength * Math.cos(angle + Math.PI / 6), endY - arrowLength * Math.sin(angle + Math.PI / 6)
        );
        arrowHead.setFill(Color.BLACK);

        return arrowHead;
    }
    public  double calculateAngle(double x1,double y1, double x2,double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        return angle;
    }
}



//
//        VBox vbox = new VBox(20);
//        Container.getChildren().add(vbox);
//        for (Semester s : seData) {
//            HBox box = new HBox(10); //横向布局
//            vbox.getChildren().add(box);
//            for (String c : s.getCourses()) {
//                Ellipse ellipse = new Ellipse(66,58);
//                ellipse.setFill(Color.GREENYELLOW); // 设置椭圆的背景颜色
//                Label label = new Label(c);
//                label.setTranslateX( 0-label.getWidth() -96);
//                label.setTranslateY(45+label.getHeight()/2);
//                nameToNode.put(c,ellipse);
//                box.getChildren().add(ellipse);
//                box.getChildren().add(label);
//            }
//
//        }
//
//        System.out.println("开始划线");
//        for(Course c : CourseGraph.CourseList){
//            Ellipse ellipse1 = nameToNode.get(c.getName());
//            for(int id :c.getAfter()){
//                String name = CourseGraph.Courses.get(id).getName();
//                Line line = createLine(ellipse1, nameToNode.get(name));
//                System.out.println("链接"+c.getName()+"与"+name);
//                vbox.getChildren().add(line);
//            }
//        }
//
