package com.example.course2;

import com.example.course2.pojo.Course;
import com.example.course2.util.FileUtil;

import java.util.*;

public class CourseGraph {
    public int numCourses;
    private int[][] adjacencyList;
    private Map<Integer, Course> Courses = new HashMap<>();
    public static List<Course> CourseList = new ArrayList<>();
    public static int sumHour = 280;
    //拓扑子集
    public List<List<Course>> subsets = new ArrayList<List<Course>>();


    // 构造函数，初始化有向图的节点数
    public CourseGraph() {

        CourseList = FileUtil.read();
        for (Course c :
                CourseList) {
            Courses.put(c.getId(), c);
        }
        numCourses = Courses.size() + 1;
        adjacencyList = new int[numCourses][numCourses];
        for (int i = 1; i <= Courses.size(); i++) {
            // 添加一个键值对，键为节点索引 i，值为一个新的 ArrayList 用于存储该节点的邻接节点
            List<Integer> after = Courses.get(i).getAfter();
            for (int id : after) {
                adjacencyList[i][id] = 1;
            }
        }
    }

    public void addPrerequisite(int course, int prerequisite) {
        adjacencyList[course][prerequisite] = 1;
    }


    //拓扑排序 没有用到
    public List<Course> topologicalSort() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        //后序遍历
        for (int i = 1; i <= Courses.size(); i++) {
            if (!visited.contains(i)) {
                topologicalHelper(i, visited, stack);
            }
        }

        //倒序输出
        List<Course> sortedCourses = new ArrayList<>();
        while (!stack.empty()) {
            sortedCourses.add(Courses.get(stack.pop()));
        }

        return sortedCourses;
    }

    private void topologicalHelper(int course, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(course);
        for (int pre : adjacencyList[course]) {
            if (!visited.contains(pre)) {
                topologicalHelper(pre, visited, stack);
            }
        }
        stack.push(course);
    }

    //拓扑子集
    public List<List<Course>> topologicalSubset(boolean[] mark, int num) {

        System.out.println("课程编排中");
        //邻接矩阵
        int[][] subAdjacencyList = adjacencyList;
        boolean flag = true;
        List<Integer> helpArray = new ArrayList<>();
        //总时长
        int hour =0;
        List<Course> row = new ArrayList<>();
        for (int i = 1; i < subAdjacencyList.length; i++) {
            if (!mark[i]) {
                flag = true;
                for (int j = 1; j < subAdjacencyList.length; j++) {
                    if (!mark[j]) {
                        if (subAdjacencyList[j][i] == 1) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    hour+=Courses.get(i).getHour();
                    if(hour<=sumHour){
                        row.add(Courses.get(i));
                        helpArray.add(i);
                        num++;
                    }


                }
            }
        }
        subsets.add(row);
        for (int i :
                helpArray) {
            mark[i] = true;
        }
        if (num != numCourses - 1) {
            topologicalSubset(mark, num);
        }


        return subsets;
    }


    public static void main(String[] args) {
        CourseGraph graph = new CourseGraph();
        boolean[] mark = new boolean[graph.numCourses];
        List<List<Course>> sortedCourses = graph.topologicalSubset(mark, 0);
        //输出sortedCourses中的name字段
        for (List<Course> c :
                sortedCourses) {
            System.out.println(c.toString());
        }
    }
}
