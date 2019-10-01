public class ThreeSum {

    //Setting max and min values for the random ints in the array
    //Having a larger MINVALUE seems to result in a greater chance there will be sets found that = 0.
    static long MAXVALUE = 1000;
    static long MINVALUE = -10000;

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