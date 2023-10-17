package com.example.course2.controller;

import com.example.course2.CourseGraph;
import com.example.course2.pojo.Course;
import com.example.course2.pojo.Semester;
import com.example.course2.service.SemesterService;
import com.example.course2.util.FileUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class HelloController {

    @FXML
    private Button search;

    @FXML
    private Button Graph;

    @FXML
    private TextField credits;
    @FXML
    private TextField courseName;

    @FXML
    private Button addCourse;

    @FXML
    private Button courseScheduling;

    @FXML
    private TableView<Semester> Courses;

    @FXML
    private Button delCourse;

    @FXML
    private TableView<Course> CourseInfo;

    @FXML
    void handleSchedule(ActionEvent event) {
        int sumHour = Integer.parseInt(credits.getText());
        System.out.println("正在编排课程，每学期课时为"+sumHour);
        CourseGraph.sumHour=sumHour;
        seData = service.getSemester();
        updateSemesterInfo();
    }
    @FXML
    void handleDel(ActionEvent event){

        ObservableList<Course> selectedItems = CourseInfo.getSelectionModel().getSelectedItems();
        for(Course c:selectedItems){
            System.out.println("删除了"+c.getName());
            CourseGraph.CourseList.remove(c);
        }
        updateCourseInfo();
    }

    @FXML
    void GenGraph(ActionEvent event) {

    }
    public static Stage modalStage = new Stage();


    @FXML
    void handleAddCourse(ActionEvent event) {
        try {
            // 加载模态对话框的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/course2/ModalDialog.fxml"));
            Parent root = loader.load();

            // 创建场景并设置根节点
            Scene scene = new Scene(root);
            modalStage.setScene(scene);

            // 显示模态对话框
            modalStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("添加课程");
            updateCourseInfo();
        }

    }

    @FXML
    void handleSearch(ActionEvent event) {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("错误");



        String name = courseName.getText();
        System.out.println("开始搜索名称为"+name+"的课程");
        if(name==null||"".equals(name)) {
            errorAlert.setHeaderText("未输入信息");
            errorAlert.showAndWait();
        }else {
            for (Course c : CourseGraph.CourseList) {
                if(c.getName().equals(name)){
                    courseInfo.clear();
                    courseInfo.add(c);
                    CourseInfo.refresh();
                    return;
                }
            }
            errorAlert.setHeaderText("未查到该课程");
            errorAlert.showAndWait();
        }


    }

    @FXML
    private TableColumn<Course, Integer> credit;
    @FXML
    private TableColumn<Course, List> after;
    @FXML
    private TableColumn<Course, String> name;
    @FXML
    private TableColumn<Course, Integer> id;
    @FXML
    private TableColumn<Course, Integer> hour;


    @FXML
    private TableColumn<Semester, String> seName;
    @FXML
    private TableColumn<Semester, Integer> seHour;
    @FXML
    private TableColumn<Semester, List> seCourses;


    SemesterService service = new SemesterService();
    List<Semester> seData = service.getSemester();
    private ObservableList<Course> courseInfo = FXCollections.observableArrayList(CourseGraph.CourseList);
    private ObservableList<Semester> semesterInfo = FXCollections.observableArrayList(seData);

    public void updateSemesterInfo() {
        semesterInfo.setAll(seData);
        Courses.refresh();
    }

    public void updateCourseInfo() {
        courseInfo.setAll(CourseGraph.CourseList);
        CourseInfo.refresh();
    }
    @FXML
    public void initialize() {
        System.out.println("程序启动了");
        //课程信息的表格
        modalStage.initModality(Modality.APPLICATION_MODAL); // 设置为应用程序模态
        modalStage.initStyle(StageStyle.UTILITY); // 可以根据需要设置窗口风格

        // 设置舞台的标题
        modalStage.setTitle("模态对话框");

        id.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        hour.setCellValueFactory(new PropertyValueFactory<Course, Integer>("hour"));
        after.setCellValueFactory(new PropertyValueFactory<Course, List>("after"));
        CourseInfo.setItems(courseInfo);


        //课程编排表格

        seName.setCellValueFactory(new PropertyValueFactory<Semester, String>("name"));
        seHour.setCellValueFactory(new PropertyValueFactory<Semester, Integer>("hour"));
        seCourses.setCellValueFactory(new PropertyValueFactory<Semester, List>("courses"));
        Courses.setItems(semesterInfo);

    }

    @FXML
    public void handleSave() {
        FileUtil.save(CourseGraph.CourseList);
    }

}
