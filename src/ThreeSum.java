public class ThreeSum {

    static long MAXVALUE = 1000;
    static long MINVALUE = -10000;

    static void threeSum(int[] arr, int n)
    {
        int cnt = 0;
        for (int i=0; i<n-2; i++)
        {
            for (int j=i+1; j<n-1; j++)
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
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt + " set(s) of 3 numbers found that add to 0.");
    }

    public static void main(String[] args)
    {

        int[] arr = createRandomIntegerList(1000);

        //Code currently commented out, displays all numbers to check that the random generation is working
        /*for(int i = 0; i < arr.length; i++) {
          System.out.println(arr[i] + " ");
        }*/
        int n = arr.length;
        threeSum(arr, n);

    }


    public static int[] createRandomIntegerList(int size){

        int[] newList = new int[size];
        for(int j=0; j<size; j++){
            newList[j] = (int)(MAXVALUE + Math.random() * MINVALUE);
        }
        return newList;
    }
}