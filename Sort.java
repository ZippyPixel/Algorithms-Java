import java.util.Random;

public class Sort {
    public static void main(String[] args){
        int[] arr = new int[10000];
        for (int i= 0; i < arr.length; i++){
            arr[i] = new Random().nextInt(10000);
        }
//        int[] arr = {9,8,7,6,5,4,3,1,2};
        long start = System.currentTimeMillis();
        quick(arr);
        for (int i : arr){
            System.out.print(i+" ");
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println((end-start)*1.0 / 1000);
    }

    /*Bubble Sort
    Time complexity: O(N^2)
    Space Complexity: O(1)
    */
    public static void bubble(int[] arr){
        for (int i=0; i< arr.length - 1; i++){
            for (int j=i+1; j< arr.length; j++){
                if (arr[j] < arr[i]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    /*Selection Sort
    Time complexity: O(N^2)
    Space Complexity: O(1)
    */
    public static void selection(int[] arr){
        for (int i=0; i< arr.length - 1; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j=i; j< arr.length; j++){
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
        }
    }

    /*Insertion Sort
    Time complexity: Worst-O(N^2) Best-O(N)
    Space Complexity: O(1)
    #better than selection and bubble
    */
    public static void insertion(int[] arr){
        for (int i=1; i< arr.length; i++){
            int currentValue = arr[i];
            int hole = i;
            while(hole>0 && arr[hole-1] > currentValue){
                arr[hole] = arr[hole-1];
                hole--;
            }
            arr[hole] = currentValue;
        }
    }

    /*Counting Sort
    Time complexity: O(N+K) [N is the length of the input array and K is the length of counting array]
    Space Complexity: O(N+K)
    #Best for elements having small value
    */
    public static int[] counting(int[] arr){
        int[] sorted = new int[arr.length];
        int max = Integer.MIN_VALUE;
        for (int element : arr){
            if (element > max)
                max = element;
        }
        int[] counting = new int[max+1];
        for (int element: arr){
            counting[element]++;
        }
        for (int i=1; i<counting.length; i++){
            counting[i] += counting[i-1];
        }
        for (int i=counting.length-1; i > 0; i--){
            counting[i] = counting[i-1];
        }
        counting[0] = 0;
        for (int i : arr) {
            sorted[counting[i]] = i;
            counting[i]++;
        }
        return sorted;
    }

    /*Quick Sort
    Time complexity: Worst-O(N^2) Best-O(NlgN) Average-O(NlgN)
    Space Complexity: O(lgN)
    #Fast in average cases (randomized value)
    #Slow in worst case
    */
    public static void quick(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }
    public static void quickSort(int[] arr, int low, int high){
        if (low < high){
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex-1);
            quickSort(arr, pivotIndex+1, high);
        }
    }
    //partition function places pivot in the right position
    //which means all the elements less than the pivot goes to left
    // and all the elements greater than the pivot goes to right
    //by default the right most element is considered to be the pivot
    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int partitionIndex = low;
        for (int index=low; index<high; index++){
            if (arr[index] <= pivot){
                //swap a[index] and a[partitionIndex]
                swap(arr, index, partitionIndex);
                partitionIndex++;
            }
        }
        swap(arr, partitionIndex, high);

        return partitionIndex;
    }
    //swap two elements in an array based on index
    public static void swap(int[] arr, int a, int b){
        if(a < arr.length && b < arr.length){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        } else System.out.println("Cant Swap"+a+" "+b);
    }
}
