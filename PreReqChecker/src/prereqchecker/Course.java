package prereqchecker;

import java.util.*;

public class Course {
    public HashMap<String, ArrayList<String>> courseID;
    //
    public Course(){
        courseID = new HashMap<String, ArrayList<String>>();
    }
    //
    public void addCourse(String course){
        courseID.put(course, new ArrayList<String>());
    }

    //insert prereg
    public void addPre(String course,String preReq){
        courseID.get(course).add(preReq);
    }

    public HashMap<String,Boolean> DFS(String course){
        //create check
        Set<String> key = courseID.keySet();
        HashMap<String, Boolean> check = new HashMap<String, Boolean>();
        for(String a:key){
            check.put(a, false);
        }
        helpDFS(course, check);
        return check;

    }

    public void helpDFS(String course,HashMap<String,Boolean> check){
        check.replace(course, false, true);

        ArrayList<String> next = courseID.get(course);
        for(int i = 0;i < next.size();i++){
            if(!check.get(next.get(i))){
                helpDFS(next.get(i), check);
            }
        }
    }

    public void print(String file){
        StdOut.setFile(file);
        Set<String> course = courseID.keySet();
        for(String key: course){
            StdOut.print(key + " ");
            for(int i = 0;i < courseID.get(key).size();i++){
                StdOut.print(courseID.get(key).get(i) + " ");
            }
            StdOut.println();
        }

    }

    
}
