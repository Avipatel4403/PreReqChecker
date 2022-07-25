package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
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

        //get courses
        StdIn.setFile(args[1]);
        int numPre = StdIn.readInt();
        ArrayList<String> taken = new ArrayList<String>();
        for(int j = 0; j < numPre;j++){
            taken.add(StdIn.readString());
        }
        //get all the taken courses
        ArrayList<String> courseTaken = new ArrayList<String>();
        for(int k = 0;k < taken.size();k++){
            HashMap<String, Boolean> c = a.DFS(taken.get(k));
            courseTaken.add(taken.get(k));
            Set<String> iter = c.keySet();
            for(String x: iter){
                if(c.get(x).booleanValue() && !(courseTaken.contains(x))){
                    courseTaken.add(x);
                }

            }

        }


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
            if(in && !(courseTaken.contains(x))){
                output.add(x);

            }

        }
        

        //output
        StdOut.setFile(args[2]);
        for(String out:output){
            StdOut.println(out);
        }


        
    
    }
}
