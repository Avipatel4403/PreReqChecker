package prereqchecker;

import java.util.HashMap;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
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

        StdIn.setFile(args[1]);
        String course = StdIn.readString();
        String prereq = StdIn.readString();
        HashMap<String, Boolean> check  = a.DFS(prereq);        

        StdOut.setFile(args[2]);

        if(check.get(course)){
            StdOut.print("NO");
            
        }
        else{
            StdOut.print("YES");

        }
    }
}
