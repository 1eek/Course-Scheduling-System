package com.example.course2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Semester {
    private  String name;
    private  int hour;
    private List<String> courses;
}
