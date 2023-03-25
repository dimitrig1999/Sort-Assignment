import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


class QuickSort {
    /**
     * Function to swap array values at array indexes i and j
     */
    private void swap(String[] names, int i, int j)
    {
        String temp = names[i];
        names[i] = names[j];
        names[j] = temp;
    }

    /**
     * Function to partition at the pivot and place all smaller elements than pivot on left and greater on right
     */
    private int partition(String[] names, int left, int right)
    {

        // taking the last element as pivot
        String pivot = names[right];

        // smaller element index and correct pivot position
        int i = (left - 1);

        // Iterate from current left to right
        for(int j = left; j <= right - 1; j++)
        {
            // if the current element is smaller than pivot
            if (names[j].compareTo(pivot) < 0)
            {
                // increment the smaller element index
                i++;
                // swap the elements as element at index j is smaller
                swap(names, i, j);
            }
        }
        swap(names, i + 1, right);
        return (i + 1);
    }


    /**
     * Function to do perform quick sort
     */
    public void doQuickSort(String[] names, int left, int right)
    {
        if (left < right)
        {
            int partitionIndex = partition(names, left, right);
            // Sort elements before partition
            doQuickSort(names, left, partitionIndex - 1);
            // Sort elements after partition
            doQuickSort(names, partitionIndex + 1, right);
        }
    }
}

class HeapSort {

    /**
     * Function to perform heap sort
     */
    public void doHeapSort(String[] names) {

        // Create a heap
        PriorityQueue<String> heap = new PriorityQueue<>();

        // Adding elements to the heap
        for(String name : names) {
            heap.add(name);
        }

        // Polling elements from the heap
        for(int i = 0; i < names.length; i++) {
            names[i] = heap.poll();
        }
    }
}

public class SortAssignment {

    private static final String COMMA_DELIMITER = ",";
    private static List<String> names = new ArrayList<>();

    /**
     * Function to add names from csv to arraylist
     */
    private static void addCsvNames(String value) {
        if(!value.isEmpty()) {
            names.add(value);
        }
    }

    public static void main(String[] args) {

        // reading the csv file
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("names.csv") , StandardCharsets.ISO_8859_1))
        {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                // Skip the header line
                if(lineNumber != 0) {
                    String[] values = line.split(COMMA_DELIMITER);
                    addCsvNames(values[1]); // read second column
                    if(values.length > 2) addCsvNames(values[2]); // read third column
                    if(values.length > 3) addCsvNames(values[3]); // read fourth column
                }
                lineNumber++;
            }

            System.out.println("Unsorted Names List:");
            System.out.println(names.toString());
            System.out.println("");

            // copying Arraylist to string array
            String[] namesArray = Arrays.copyOf(names.toArray(), names.size(), String[].class);

            QuickSort quickSort = new QuickSort();
            quickSort.doQuickSort(namesArray, 0, names.size()-1);
            System.out.println("Quick-Sort Sorted Names List(Asc Order):");
            System.out.println(Arrays.toString(namesArray));
            System.out.println("");

            // copying Arraylist to string array
            namesArray = Arrays.copyOf(names.toArray(), names.size(), String[].class);

            HeapSort heapSort = new HeapSort();
            heapSort.doHeapSort(namesArray);
            System.out.println("Heap-Sort Sorted Names List(Asc Order):");
            System.out.println(Arrays.toString(namesArray));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

