package com.example.course2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//一个课程的类

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
   private int id; //课程号
   private int credit; //学分
   private String name; //课程名
   private int hour; //学时
   private List<Integer> after ; //后续课程



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public List<Integer> getAfter() {
        return after;
    }

    public void setAfter(List<Integer> after) {
        this.after = after;
    }
}
