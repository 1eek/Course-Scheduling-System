package com.example.course2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class EllipseArrowExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // 创建两个椭圆
        Ellipse ellipse1 = new Ellipse(100, 100, 50, 30);
        ellipse1.setFill(Color.LIGHTBLUE);

        Ellipse ellipse2 = new Ellipse(250, 200, 50, 30);
        ellipse2.setFill(Color.LIGHTCORAL);

        double angle = calculateAngle(ellipse1.getCenterX(), ellipse1.getCenterY(), ellipse2.getCenterX(), ellipse2.getCenterY());
        double startX = ellipse1.getCenterX()+20*Math.cos(angle);
        double startY = ellipse1.getCenterY()+20*Math.sin(angle);
        double endX = ellipse2.getCenterX()-20*Math.cos(angle);
        double endY = ellipse2.getCenterY()-20*Math.sin(angle);
        // 创建一条线连接两个椭圆
        Line arrow = new Line(startX,startY, endX, endY);
        arrow.setStrokeWidth(2);
        arrow.setStroke(Color.BLACK);

        // 将箭头放在椭圆下面
        arrow.toBack();

        // 将椭圆和箭头添加到Pane
        root.getChildren().addAll(ellipse1, ellipse2, arrow);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ellipse Arrow Example");
        primaryStage.show();
    }

    public  double calculateAngle(double x1,double y1, double x2,double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        return angle;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
