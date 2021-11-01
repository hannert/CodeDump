import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

class hw4{

    public boolean finished = false;
    
    public void backTrack(ArrayList<int[]> a, int k, ArrayList<int[]> input, int MAXCANDIDATES){
        int[][] c = new int[MAXCANDIDATES][];
        int nc = 0;
        int i;


        if (isSolution(a, k, input)){
            processSolution();
        }
        else{
            k += 1;
            constructCandidates(a, k, input, c, nc, MAXCANDIDATES);     // updates c and nc
            for (i = 0; i < nc; i++){
                a.add(i, c[i]); // Comment
                backTrack(a, k, input, MAXCANDIDATES);
                if (finished) {
                    return;
                }
            }
        }

    }
    
    public boolean isSolution(ArrayList<int[]> a, int k, ArrayList<int[]> input){
        
        return true;
    }
    public void processSolution(){
        System.out.println("Done");
    }
    /**
     * 
     * @param a     
     * @param k     counter variable
     * @param n     array of sets
     * @param c     w
     * @param nc    e
     * @param MAX   maximum size of answer set
     */
    public void constructCandidates(ArrayList<int[]> a, int k, ArrayList<int[]> n, int[][] c, int nc, int MAX){
        int i;
        boolean inPerm[] = new boolean[MAX];

        for (i = 1; i < k; i++){
            inPerm[n.indexOf(a.get(i))] = true;
        }

        nc = 0;
        for(i = 1; i <= n.size(); i++){
            if (!inPerm[i]) { // ??????
                c[nc] = n.get(i);
                nc += 1;
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        int MAXCANDIDATES = 0;
        int UNIVERSALSET = 0;


        ArrayList<int[]> myList = new ArrayList<int[]>();
        File file = new File("s-X-12-6.txt");
        int lineNumber = 0;
        int[] myArray;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while (lineNumber <= 1 && (line = br.readLine()) != null){
                if (lineNumber == 0){
                    UNIVERSALSET = Integer.parseInt(line);
                    System.out.println("UNIV " + UNIVERSALSET);
                }
                else if (lineNumber == 1) {
                    MAXCANDIDATES = Integer.parseInt(line);
                    System.out.println("MAX " + MAXCANDIDATES);

                }
                lineNumber++;
            }
            //myArray = new int[MAXCANDIDATES][];
            while ((line = br.readLine()) != null){
                String[] temp = line.split(" ");
                //     for(int i = 0; i < temp.length; i++){
                //         System.out.print(temp[i] + " ");
                //     }
                // System.out.println("");
                int[] tempSet = new int[temp.length];
                for(int i = 0; i < temp.length; i++){
                    tempSet[i] = Integer.parseInt(temp[i]);
                }
                //myArray[lineNumber - 2] = tempSet;
                myList.add(tempSet);
                lineNumber++;
            }           
            for(int i = 0; i < myList.size(); i++){
                for(int j = 0; j < myList.get(i).length; j++){
                    System.out.print(myList.get(i)[j]);
                }
                System.out.println("");
            }

                lineNumber++;
            
        } catch(Exception e){
            System.out.println(e);
        }
        // for(int i = 0; i < myArray.length; i++){
        //     for(int j = 0; i < myArray[i].length; j++){
        //         System.out.print(myArray[i][j]);
        //     }
        //     System.out.println("");
        // }
        long stopTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (stopTime - startTime) + "ms");
    }


}