import java.lang.management.ManagementFactory;

import java.lang.management.ThreadMXBean;

import java.io.*;

import java.util.Arrays;

public class ThreeSumFaster {

    static ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

    //Setting max and min values for the random ints in the array
    //Having a larger MINVALUE seems to result in a greater chance there will be sets found that = 0.
    static long MAXVALUE = 10000;
    static long MINVALUE = -100000;
    static int numberOfTrials = 10;

    static int MAXINPUTSIZE  = 6000;

    static int MININPUTSIZE  =  10;

    static String ResultsFolderPath = "/home/marie/Results/"; // pathname to results folder

    static FileWriter resultsFile;

    static PrintWriter resultsWriter;

    static void threeSum(int[] arr, int n)
    {
        //Start count at zero, will eventually display the amount of sets that = 0.
        //Also, sort the array so a binary search can be performed.
        Arrays.sort(arr);
        int cnt = 0;

        for (int i=0; i<n; i++)
        {
            for (int j=i+1; j<n; j++)
            {

                int key = -(arr[i]+arr[j]);
                binarySearch(arr, key);

                //Using a binary search to find the number that will complete the set instead
                if(binarySearch(arr, key) != -100000000){
                    cnt++;
                }

            }
        }
        //Displays the amount of sets of three elements that added to zero
        System.out.println(cnt + " set(s) of 3 numbers found that add to 0.");
    }


    public static void main(String[] args)
    {
        runFullExperiment("ThreeSumFaster-Exp1-ThrowAway.txt");

        runFullExperiment("ThreeSumFaster-Exp2.txt");

        runFullExperiment("ThreeSumFaster-Exp3.txt");
    }

    static void runFullExperiment(String resultsFileName){

        try {

            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);

            resultsWriter = new PrintWriter(resultsFile);

        } catch(Exception e) {

            System.out.println("*****!!!!!  Had a problem opening the results file "+ResultsFolderPath+resultsFileName);

            return; // not very foolproof... but we do expect to be able to create/open the file...

        }



        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials

        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial



        resultsWriter.println("#InputSize    AverageTime"); // # marks a comment in gnuplot data

        resultsWriter.flush();

        for(int inputSize=MININPUTSIZE;inputSize<=MAXINPUTSIZE; inputSize*=2) {

            // progress message...

            System.out.println("Running test for input size "+inputSize+" ... ");



            /* repeat for desired number of trials (for a specific size of input)... */

            long batchElapsedTime = 0;

            // generate a list of randomly spaced integers in ascending sorted order to use as test input

            // In this case we're generating one list to use for the entire set of trials (of a given input size)

            // but we will randomly generate the search key for each trial






            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)

            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the

            // stopwatch methods themselves

            //BatchStopwatch.start(); // comment this line if timing trials individually



            // run the tirals

            for (long trial = 0; trial < numberOfTrials; trial++) {

                //Creates an array of random integers of the designated size using the createRandomIntegerList function.
                int[] arr = createRandomIntegerList(inputSize);

                //Code currently commented out, displays all numbers to check that the random generation is working
        /*for(int i = 0; i < arr.length; i++) {
          System.out.println(arr[i] + " ");
        }*/

                //Sets the int n to the length of the array previously generated
                int n = arr.length;


                TrialStopwatch.start(); // *** uncomment this line if timing trials individually

                /* run the function we're testing on the trial input */


                //Run the threeSum function using the randomly generated array and it's length.

                threeSum(arr, n);

                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime(); // *** uncomment this line if timing trials individually

            }

            //batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually

            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double)numberOfTrials; // calculate the average time per trial in this batch



            /* print data for this size of input */

            resultsWriter.printf("%12d  %15.2f \n",inputSize, averageTimePerTrialInBatch); // might as well make the columns look nice

            resultsWriter.flush();

            System.out.println(" ....done.");

        }

    }

    //Function to create the random integers that populate the array.

    public static int[] createRandomIntegerList(int size){

        int[] newList = new int[size];

        //For loop to generate random numbers within MAXVALUE and MINVALUE
        for(int j=0; j<size; j++){
            newList[j] = (int)(MAXVALUE + Math.random() * MINVALUE);
        }
        return newList;
    }

    public static int binarySearch(int[] list, int searchFor){
        int i = 0;
        int j = list.length -1;
        int k = (i+j)/2;
        while (i <= j){
            if (list[k] < searchFor){
                i = k + 1; }
            else if(list[k] == searchFor){
                return k; }
            else{
                j = k - 1; }
            k = (i+j)/2; }
        // Using -100000000 instead of -1 because k could equal -1
        return -100000000; }
}