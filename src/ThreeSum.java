import java.lang.management.ManagementFactory;

import java.lang.management.ThreadMXBean;

import java.io.*;

public class ThreeSum {

    static ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

    //Setting max and min values for the random ints in the array
    //Having a larger MINVALUE seems to result in a greater chance there will be sets found that = 0.
    static long MAXVALUE = 10000;
    static long MINVALUE = -100000;
    static int numberOfTrials = 10;

    static int MAXINPUTSIZE  = 1000;

    static int MININPUTSIZE  =  1;

    static String ResultsFolderPath = "/home/marie/Results/"; // pathname to results folder

    static FileWriter resultsFile;

    static PrintWriter resultsWriter;

    static void threeSum(int[] arr, int n)
    {
        //Start count at zero, will eventually display the amount of sets that = 0.
        int cnt = 0;

        //Run 3 loops using n (the length of the array) to cycle through the numbers in the array
        //Checks one by one to see if the sum of those three elements is zero
        for (int i=0; i<n; i++)
        {
            for (int j=i+1; j<n; j++)
            {
                for (int k=j+1; k<n; k++)
                {
                    if (arr[i]+arr[j]+arr[k] == 0)
                    {
                        //Code currently commented out, shows which sets add to 0.
                        /*System.out.print(arr[i]);
                        System.out.print(" ");
                        System.out.print(arr[j]);
                        System.out.print(" ");
                        System.out.print(arr[k]);
                        System.out.print("\n");*/

                        //If the sum of the elements is equal to zero the counter increments
                        cnt++;
                    }
                }
            }
        }
        //Displays the amount of sets of three elements that added to zero
        System.out.println(cnt + " set(s) of 3 numbers found that add to 0.");
    }

    public static void main(String[] args)
    {
        runFullExperiment("ThreeSum-Exp1-ThrowAway.txt");

        runFullExperiment("ThreeSum-Exp2.txt");

        runFullExperiment("ThreeSum-Exp3.txt");
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

            BatchStopwatch.start(); // comment this line if timing trials individually



            // run the tirals

            for (long trial = 0; trial < numberOfTrials; trial++) {

                //Creates an array of random integers of the designated size using the createRandomIntegerList function.
                int[] arr = createRandomIntegerList(1000);

                //Code currently commented out, displays all numbers to check that the random generation is working
        /*for(int i = 0; i < arr.length; i++) {
          System.out.println(arr[i] + " ");
        }*/

                //Sets the int n to the length of the array previously generated
                int n = arr.length;

                //Run the threeSum function using the randomly generated array and it's length.

                threeSum(arr, n);

            }

            batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually

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
}