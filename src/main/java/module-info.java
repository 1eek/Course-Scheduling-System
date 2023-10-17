module com.example.course2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires lombok;
    requires com.fasterxml.jackson.databind;


    opens com.example.course2 to javafx.fxml;
    exports com.example.course2;
    exports com.example.course2.pojo;
    opens com.example.course2.pojo to javafx.fxml;
    exports com.example.course2.util;
    opens com.example.course2.util to javafx.fxml;
    exports com.example.course2.controller;
    opens com.example.course2.controller to javafx.fxml;
}