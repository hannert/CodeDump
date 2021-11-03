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

    // ! TEST FUNCTION!
    public static void printMatrix(int[][] a){
        System.out.println("Matrix: ");
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void printArrayList(ArrayList<int[]> a){
        for(int i = 0; i < a.size(); i++){
            for(int j = 0; j < a.get(i).length; j++){
                System.out.print(a.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
    public static void printArrayAddress(ArrayList<int[]> a){
        for(int i = 0; i < a.size(); i++){
            System.out.println(a.get(i));
        }
    }
    // ! TEST FUNCTION!

    public static void backTrack(int[][] a, int k, int[][] input,
                                int MAXCANDIDATES, int[] universalSet){
        int[][] c = new int[MAXCANDIDATES][];
        dummyValue nc = new dummyValue(0);
        int i;
        if (isSolution(a, k, input, universalSet)){
            processSolution(a); 
        }
        else{
            k += 1;
            constructCandidates(a, k, input, c, nc, MAXCANDIDATES);     // updates c and nc

            for (i = 0; i < nc.getVal(); i++){
                a[i] = c[i];

                // System.out.println("A: ----------------");
                // printMatrix(a);
                backTrack(a, k, input, MAXCANDIDATES, universalSet);
                if (finished) {
                    return;
                }
            }
        }

    }
    // ! Get all unique numbers and put in currentSolution to compare to universal set
    public static boolean isSolution(int[][] a, int k, int[][] input, int[] universalSet){
        ArrayList<Integer> currentSolution = new ArrayList<>();
        for (int i = 0; i < a.length; i++){ 
            for (int j = 0; j < a[i].length; j++){
                if(!currentSolution.contains(a[i][j])){
                    currentSolution.add(a[i][j]);
                }
            }
        }
        for (int i = 0; i < universalSet.length; i++){
            if(!currentSolution.contains(universalSet[i])) return false;
        }
        return true;
    }
    public static void processSolution(int[][] a){
        printMatrix(a);
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
    public static void constructCandidates(int[][] a, int k, int[][] n,
                                     int[][] c, dummyValue nc, int MAX){
        int i;
        boolean inPerm[] = new boolean[MAX];
        for (i = 1; i < k; i++){
            for(int j = 0; j < n.length; j++){
                inPerm[j] = true;
            }
        }
        nc.setVal(0);
        for(i = 1; i < n.length - 1; i++){
            if (!inPerm[i]) { // ! ??????
                c[nc.getVal()] = n[i];
                nc.addOneToVal(); // nc++
            }
        }
    }

    public static void generatePermutations(int[][] masterSet, int MAX, int[] univ){
        int[][] a = new int[MAX][0];

        backTrack(a, 0, masterSet, MAX, univ);
    }
    // public static void generatePermutations(ArrayList<int[]> masterSet, int MAX, int[] univ){
    //     int[][] a = new int[MAX][0];

    //     backTrack(a, 0, masterSet, MAX, univ);
    // }
    public static void main(String[] args) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        int MAXCANDIDATES = 0;
        int UNIVERSALSET = 0;


        //ArrayList<int[]> myList = new ArrayList<int[]>();
        File file = new File("s-X-12-6.txt");
        int lineNumber = 0;
        int[] myArray = new int[0];
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int[][] myList = new int[0][0];
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
                    myList = new int[MAXCANDIDATES][];

                }
                lineNumber++;
            }
            for(int i = 0; i < MAXCANDIDATES; i++){
                line = br.readLine();
                System.out.println(line);
                String[] temp = line.split(" ");
                int[] tempSet = new int[temp.length];
                for(int j = 0; j < temp.length; j++){
                    int tem = Integer.parseInt(temp[j]);
                    //tempSet[i] = Integer.parseInt(temp[i]);
                    tempSet[j] = tem;
                }
                myList[i] = tempSet; // Add to myList
            }

            generatePermutations(myList, MAXCANDIDATES, myArray);
                // lineNumber++;
            
        } catch(Exception e){
            System.out.println(e);
        }

        long stopTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (stopTime - startTime) + "ms");
    }


}