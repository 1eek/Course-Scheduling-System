package com.example.course2.controller;


import com.example.course2.CourseGraph;
import com.example.course2.pojo.Course;
import com.example.course2.service.SemesterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static com.example.course2.controller.HelloController.modalStage;

public class DialogController {

    @FXML
    private TextField hour;

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    private TextField credit;

    @FXML
    private VBox box;

    SemesterService service = new SemesterService();


    public void initialize() {
        for (Course c : CourseGraph.CourseList) {
            CheckBox checkBox = new CheckBox(c.getName());
            box.getChildren().add(checkBox);
        }

    }


    @FXML
    void cancel(ActionEvent event) {
        modalStage.close();
    }

    @FXML
    void handleAdd(ActionEvent event) {
        int hour1 = Integer.parseInt(hour.getText());
        String name1 = name.getText();
        int id1 = Integer.parseInt(id.getText());
        int credit1 = Integer.parseInt(credit.getText());
        Course course = new Course();
        course.setCredit(credit1);
        course.setHour(hour1);
        course.setId(id1);
        course.setName(name1);
        List<Integer> after = new ArrayList<>();
        course.setAfter(after);
        for (Node node : box.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String CourseName = checkBox.getText();
                    for (Course c : CourseGraph.CourseList) {
                        if (c.getName().equals(CourseName)) {
                            after.add(c.getId());
                        }
                    }
                }
            }
        }
        CourseGraph.CourseList.add(course);
        modalStage.close();
    }
}
