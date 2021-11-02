import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

class hw4{

    static class dummyValue{
        int val;
        dummyValue(int n){
            this.val = n;
        }
        public int getVal(){return this.val;}
        
        public void setVal(int n){this.val = n;}
        public void addOneToVal(){this.val += 1;}
    }
    public static boolean finished = false;
    
    public static void backTrack(ArrayList<int[]> a, int k, ArrayList<int[]> input,
                                int MAXCANDIDATES, int[] universalSet){
        int[][] c = new int[MAXCANDIDATES][];
        dummyValue nc = new dummyValue(0);
        
        int i;

        System.out.println("Backtrack!");
        if (isSolution(a, k, input, universalSet)){
            processSolution();
        }
        else{
            k += 1;
            constructCandidates(a, k, input, c, nc, MAXCANDIDATES);     // updates c and nc
            System.out.println("nc: " + nc.getVal());
            for (i = 0; i < nc.getVal(); i++){
                System.out.println("constructed?!");
                
                a.set(i, c[i]); // Comment
                backTrack(a, k, input, MAXCANDIDATES, universalSet);
                if (finished) {
                    return;
                }
            }
        }

    }
    // ! Get all unique numbers and put in currentSolution to compare to universal set
    public static boolean isSolution(ArrayList<int[]> a, int k, ArrayList<int[]> input, int[] universalSet){
        ArrayList<Integer> currentSolution = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){ 
            for (int j = 0; j < a.get(i).length; j++){
                if(!currentSolution.contains(a.get(i)[j])){
                    currentSolution.add(a.get(i)[j]);
                }
            }
        }
        for (int i = 0; i < universalSet.length; i++){
            if(!currentSolution.contains(universalSet[i])) return false;
        }
        return true;
    }
    public static void processSolution(){
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
    public static void constructCandidates(ArrayList<int[]> a, int k, ArrayList<int[]> n,
                                     int[][] c, dummyValue nc, int MAX){
        System.out.println("constructedCandidate!");

        int i;
        boolean inPerm[] = new boolean[MAX];
        System.out.println("k: " + k);

        for (i = 1; i < k; i++){
            System.out.println("i: " + i);
            inPerm[n.indexOf(a.get(i))] = true;
        }
        System.out.println("Done with i");
        for(i = 0; i < MAX; i++){
            System.out.println(inPerm[i]);
        }
        

        nc.setVal(0);
        for(i = 1; i < n.size(); i++){
            System.out.println("k: " + i);

            if (!inPerm[i]) { // ! ??????
                c[nc.getVal()] = n.get(i);
                nc.addOneToVal(); // nc++
                System.out.println("nc: " + nc.getVal());
            }
        }
        System.out.println("Done with n");
        for(i = 0; i < nc.getVal(); i++){
            System.out.println("First elem: " + c[i][0]);
        }
    }


    public static void generatePermutations(ArrayList<int[]> masterSet, int MAX, int[] univ){
        ArrayList<int[]> a = new ArrayList<int[]>();
        
        backTrack(a, 0, masterSet, MAX, univ);
    }
    public static void main(String[] args) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        int MAXCANDIDATES = 0;
        int UNIVERSALSET = 0;


        ArrayList<int[]> myList = new ArrayList<int[]>();
        File file = new File("s-X-12-6.txt");
        int lineNumber = 0;
        int[] myArray = new int[0];

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while (lineNumber <= 1 && (line = br.readLine()) != null){
                if (lineNumber == 0){
                    UNIVERSALSET = Integer.parseInt(line);
                    System.out.println("UNIV " + UNIVERSALSET);
                    myArray = new int[UNIVERSALSET];
                    for (int i = 0; i < UNIVERSALSET; i++){
                        myArray[i] = i + 1;
                    }
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

            System.out.println("Universal Set: ");
            for(int i = 0; i < myArray.length; i++){
                System.out.print(myArray[i] + " ");
            }
            System.out.println();
            for(int i = 0; i < myList.size(); i++){
                for(int j = 0; j < myList.get(i).length; j++){
                    System.out.print(myList.get(i)[j]);
                }
                System.out.println("");
            }
            generatePermutations(myList, MAXCANDIDATES, myArray);
                // lineNumber++;
            
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