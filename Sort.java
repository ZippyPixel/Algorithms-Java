import java.util.Random;

public class Sort {
    public static void main(String[] args){
        int[] arr = new int[10000];
        for (int i= 0; i < arr.length; i++){
            arr[i] = new Random().nextInt(10000);
        }
//        int[] arr = {9,8,7,6,5,4,3,1,2};
        long start = System.currentTimeMillis();
        merge(arr);
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
    public static void counting(int[] arr){
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
        for (int i=0; i< arr.length; i++){
            arr[i] = sorted[i];
        }
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
    /*Merge Sort
    Time complexity: O(NlgN)
    Space Complexity: O(N)
    #Fast in all cases
    */
    public static void merge(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }
    public static void mergeSort(int[] arr, int low, int high){
        if (low < high){
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            mergeSorted(arr, low, mid, high);
        }
    }

    public static void mergeSorted(int[] arr, int low, int mid, int high) {
        int pointer1 = low, pointer2 = mid+1, index = 0;
        int[] merged = new int[high-low+1];
        while ( pointer1 <= mid && pointer2 <= high ){
            if (arr[pointer1] < arr[pointer2]){
                merged[index] = arr[pointer1];
                pointer1++;
            } else{
                merged[index] = arr[pointer2];
                pointer2++;
            }
            index++;
        }
        while (pointer1 <= mid){
            merged[index] = arr[pointer1];
            pointer1++;
            index++;
        }
        while (pointer2 <= high){
            merged[index] = arr[pointer2];
            pointer2++;
            index++;
        }
        for (int i=0; i< merged.length; i++){
            arr[low+i] = merged[i];
        }
    }
    public static void heap(int arr[]){
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    public static void heapify(int arr[], int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }
}
