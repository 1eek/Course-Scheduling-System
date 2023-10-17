package com.example.course2.service;

import com.example.course2.CourseGraph;
import com.example.course2.pojo.Course;
import com.example.course2.pojo.Semester;

import java.util.ArrayList;
import java.util.List;


public class SemesterService {

    CourseGraph courseGraph = new CourseGraph();

    public List<Semester> getSemester() {
        List<Semester> res = new ArrayList<>();
        courseGraph.subsets.clear();
        boolean[] mark = new boolean[50];
        List<List<Course>> courses = courseGraph.topologicalSubset(mark, 0);
        for (int i = 0;i<courses.size();i++) {
            Semester semester = new Semester();
            semester.setName("第"+(i+1)+"学期");
            //将courses里的名称取出来放到列表里
            int sumOfAges =courses.get(i).stream()
                    .mapToInt(Course::getHour) // 映射为年龄字段
                    .sum(); // 对年龄字段求和
            semester.setHour(sumOfAges);

            semester.setCourses(List.of(courses.get(i).stream()
                    .map(Course::getName)
                    .toArray(String[]::new)));
            res.add(semester);
        }
        System.out.println("课程编排完成");
        return res;
    }



}
