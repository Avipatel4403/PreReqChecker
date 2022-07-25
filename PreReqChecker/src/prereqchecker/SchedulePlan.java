package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the
 * course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

        // WRITE YOUR CODE HERE
        // get graph
        StdIn.setFile(args[0]);
        int numCourse = StdIn.readInt();
        Course a = new Course();
        // insert the courses
        for (int i = 0; i < numCourse; i++) {
            String course = StdIn.readString();
            a.addCourse(course);
        }
        int numPreReq = StdIn.readInt();
        for (int j = 0; j < numPreReq; j++) {
            String c = StdIn.readString();
            String pre = StdIn.readString();
            a.addPre(c, pre);
        }


        //Get target and dfs
        StdIn.setFile(args[1]);
        String target = StdIn.readString();
        HashMap<String,Boolean> pre = a.DFS(target);
        
        //take all courses that are need
        HashSet<String> courseNeeded = new HashSet<String>();
        Set<String> key = pre.keySet();
        for(String k:key){
            if(pre.get(k).booleanValue()){
                courseNeeded.add(k);
            }

        }

        //course taken
        int numPre = StdIn.readInt();
        ArrayList<String> taken = new ArrayList<String>();
        for(int j = 0; j < numPre;j++){
            taken.add(StdIn.readString());
        }

        //get all the taken courses
        HashSet<String> courseTaken = new HashSet<String>();
        for(int k = 0;k < taken.size();k++){
            courseTaken.add(taken.get(k));
            HashMap<String, Boolean> c = a.DFS(taken.get(k));
            Set<String> iter = c.keySet();
            for(String x: iter){
                if(c.get(x).booleanValue() && !(courseTaken.contains(x))){
                    courseTaken.add(x);
                }
            }
        }
        courseNeeded.removeAll(courseTaken);


        
        //need create semester
        HashMap<Integer, ArrayList<String>> semester = new HashMap<Integer, ArrayList<String>>();
        int numSem = 0;

        courseTaken.remove(target);
        
        while(courseNeeded.size() != 1){
            //get eligible from taken(making sure cource taken is updated)
            ArrayList<String> output =  new ArrayList<String>();
            Set<String> check = a.courseID.keySet();
            //iterate for hashmap
            for(String x: check){
            //check the arraylist in the hashmap
                Boolean in = true;
                for(int i = 0;i< a.courseID.get(x).size();i++){
                    if(!(courseTaken.contains(a.courseID.get(x).get(i)))){
                        in= false;

                    }
                }
                if(in && courseNeeded.contains(x)){
                    output.add(x);
                    courseNeeded.remove(x);
                }
                
            }
            for(int o = 0;o < output.size();o++){
                courseTaken.add(output.get(o));
            }
            semester.put(numSem, output);
            numSem++;
        }


        // output
        StdOut.setFile(args[2]);
        StdOut.println(numSem);
        Set<Integer> f = semester.keySet();
        for(int i:f){
            for(int k = 0;k < semester.get(i).size();k++){
                StdOut.print(semester.get(i).get(k) + " ");
            }
            StdOut.println();
        }
    

    }
}
