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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
        //get graph
        StdIn.setFile(args[0]);
        int numCourse = StdIn.readInt();
        Course a = new Course();
        //insert the courses
        for(int i = 0; i < numCourse;i++){
            String course = StdIn.readString();
            a.addCourse(course);
        }
        int numPreReq = StdIn.readInt();
        for(int j = 0;j < numPreReq;j++){
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

        courseTaken.add(target);

        courseNeeded.removeAll(courseTaken);


        //output
        StdOut.setFile(args[2]);
        for(String course: courseNeeded){
            StdOut.println(course);
        }


    }
}
