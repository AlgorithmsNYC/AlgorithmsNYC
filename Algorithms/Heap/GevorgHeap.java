/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Marcello
 */
public class GevorgHeap {

    /**
     * If the load factor exceeds this value, the underlying array size is doubled
     */
    private static int MAX_LOAD_FACTOR = 75;
    
    
    /**
     * If the load factor drops below this value, the underlying array size is halved
     */
    private static int MIN_LOAD_FACTOR = 25;
    
    
    /**
     * The minimum size of the underlying array
     */
    private static int MIN_HEAP_CAPACITY = 16;
    
    
    /**
     * The Comparator used to describe the Heap Property
     */
    private Comparator<Integer> Heap_Property;

    
    
    /**
     * The underlying array holding the data
     */
    private int[] data;
    
    
    /**
     * A mapping from the key_value to its position in the data array
     */
    private HashMap<Integer, Integer> mapping;
    
     
    
    /**
     * The size of the heap, not necessarily equals to the data array length
     */
    private int size;
    
    
    

    /**
     * Constructor
     */
    public GevorgHeap(Comparator<Integer> heapProperty) {
        this.Heap_Property = heapProperty;
        data = new int[16];
        mapping = new HashMap<Integer, Integer>();
        size = 0;
        System.out.println("New Heap:\n" + this);
    }
     
    
    
    /**
     * Constructor that builds a Heap given a generic array
     */
    public GevorgHeap(Comparator<Integer> heapProperty, int[] rawArray) {
        this.Heap_Property = heapProperty;
        size = rawArray.length;
        data = new int[size * 2];
        System.arraycopy(rawArray, 0, data, 0, size);

        int idx = 0;
        mapping = new HashMap<Integer, Integer>();
        for(int elem : rawArray)
            mapping.put(elem, idx++);
        
        this.buildHeap();
        

        System.out.println("New Heap:\n" + this);
    }
    
    
    
    /**
     * Insert an element in the heap keeping the LF between its boundaries
     * @param key
     * @return 
     */
    public int add(int key) {
        System.out.print("Inserting " + key + ":\n");
        
        if(size < data.length) {
            data[size] = key;
            
            mapping.put(key, size);
            
            bubbleUp(size++);
        }
        
        if(size * 100 / data.length >= MAX_LOAD_FACTOR)
            increaseHeapCapacity();
        
        System.out.println(this);
        
        return size;
    }


    
    
    /**
     * Retrieves the top element without removing it
     * @return 
     */
    public int getTop() {

        if(size > 0)
            return data[0];

        return -1;
    }
    
    
    
    /**
     * Retrieve and remove the first element of the heap
     * @return 
     */
    public int extractTop() {
        
        if(size > 0) {
            
            int result = data[0];
            
            swap(0, --size);
            
            heapify(0);
            

            if(size * 100 / data.length <= MIN_LOAD_FACTOR)
                decreaseHeapCapacity();
            
            mapping.remove(result);

            System.out.println("Extracting " + result + ":\n" + this);
            
            
            return result;
        }
        
        return -1;
    }

    
    
    
    /**
     * Function that modifies the value of a key depending on an offset (positive/negative)
     * @param key
     * @param offset 
     */
    public void changeKeyValue(int key, int offset) {
        int keyIndex = mapping.get(key);
        int newKey = key + offset;

        data[keyIndex] = newKey;
        
        mapping.remove(key);
        mapping.put(newKey, keyIndex);

        
        bubbleUp(keyIndex);
        heapify(keyIndex);
        
        System.out.println("Updating key " + key + " to " + (key + offset) + "\n" + this);
    
    }

    /**
     * Halves the data array when the LF falls below MIN_LOAD_FACTOR
     */
    private void decreaseHeapCapacity() {
        
        int newSize = data.length / 2;        
        
        if(newSize >= MIN_HEAP_CAPACITY) {            
        
            int[] newData = new int[newSize];
            
            System.arraycopy(data, 0, newData, 0, size);
            
            data = newData;

        }
    }
    
    
    /**
     * Doubles the data array when the LF exceeds the MAX_LOAD_FACTOR
     */
    private void increaseHeapCapacity() {
        
        int[] newData = new int[data.length * 2];
        
        System.arraycopy(data, 0, newData, 0, size);
        
        data = newData;
        
    }
    
    
    
    
    
    /**
     * Build a heap from a raw array that does not respect the heap property
     */
    public final void buildHeap() {

        for(int i = (size - 1) / 2; i >= 0; i--)
            heapify(i);
    
    }
    
    
    /**
     * Maintain the heap property by comparing the node at index with its children 
     * @param index 
     */
    private void heapify(int index) {
        
        int left = left(index);
        int right = right(index);
        int higherPriority;
        
        
        if(left != -1 && left < size && Heap_Property.compare(data[left], data[index]) > 0)
            higherPriority = left;           
        else
            higherPriority = index;
        
        if(right != -1 && right < size && Heap_Property.compare(data[right], data[higherPriority]) > 0)
            higherPriority = right;
        
        
        
        if(higherPriority != index) {
        
            swap(higherPriority, index);
            
            heapify(higherPriority);
        
        }
        
        
    }
    
    

    
    /**
     * Maintain the heap property by bubbling up the element at index
     * @param index 
     */
    private void bubbleUp(int index) {
        
        int parentIdx = parent(index);
        
        if(parentIdx >= 0 && Heap_Property.compare(data[index], data[parentIdx]) > 0) {

            swap(index, parentIdx);
           
            bubbleUp(parentIdx);
        }
        
    }

    
    
    
    /**
     * Swap two elements in the data array
     * @param idx1
     * @param idx2 
     */
    private void swap(int idx1, int idx2) {
        data[idx1] = data[idx1] + data[idx2];
        data[idx2] = data[idx1] - data[idx2];
        data[idx1] = data[idx1] - data[idx2];

        mapping.put(data[idx1], idx1);
        mapping.put(data[idx2], idx2);
    }
    
    
    /**
     * Return the parent index of the index passed as an argument
     * @param index
     * @return 
     */
    private int parent(int index) {
        if(index == 0)
            return -1;
        
        int parentIndex = (index - 1) / 2;
        
        if(parentIndex >= 0)
            return parentIndex;
        
        return -1;
    }
    
    
    
    
    /**
     * Return the left child of a node
     * @param index
     * @return 
     */
    private int left(int index) {
        int leftIndex = index * 2 + 1;
        
        if(leftIndex < size)
            return leftIndex;
        
        return -1;
    }
    
    
    
    
    /**
     * Return the right child of a node
     * @param index
     * @return 
     */
    private int right(int index) {
        int rightIndex = index * 2 + 2;
        
        if(rightIndex < size)
            return rightIndex;
        
        return -1;
    }    
    
    
    
    
    @Override
    public String toString() {
        return "Size=" + size + ", Capacity=" + data.length + ", LF=" + size * 100 / data.length + "\n" +
                "Data=" + Arrays.toString(data) + "\nMapping=" + mapping + "\n";
    }

    
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        Comparator<Integer> MAX_PROPERTY = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if(i1 > i2)
                    return 1;
                if(i1 == i2)
                    return 0;
                else
                    return -1;
            }
        };
        
        Comparator<Integer> MIN_PROPERTY = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if(i1 < i2)
                    return 1;
                if(i1 == i2)
                    return 0;
                else
                    return -1;
            }            
        };
        
        
        new GevorgHeap(MIN_PROPERTY, new int[]{7,4,5,8,6,0,9,2,3,1});
//        New Heap:
//        Size=10, Capacity=20, LF=50
//        Data=[0, 1, 5, 2, 4, 7, 9, 8, 3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
//        Mapping={0=0, 1=1, 2=3, 3=8, 4=4, 5=2, 6=9, 7=5, 8=7, 9=6}

        System.out.println();
        
        new GevorgHeap(MAX_PROPERTY) {{
            add(1);
            add(14);
            add(10);
            add(8);
            add(7);
            add(9);
            add(3);
            add(2);
            add(4);
            add(16);
            add(12);
            add(15);
            
            changeKeyValue(16, -10);
            changeKeyValue(9, 4);
            changeKeyValue(12, 6);
            
            extractTop();
            extractTop();
            extractTop();
            extractTop();
            
        }};
        
        //New Heap:
        //Size=0, Capacity=16, LF=0
        //Data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={}
        //
        //Inserting 1:
        //Size=1, Capacity=16, LF=6
        //Data=[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=0}
        //
        //Inserting 14:
        //Size=2, Capacity=16, LF=12
        //Data=[14, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=1, 14=0}
        //
        //Inserting 10:
        //Size=3, Capacity=16, LF=18
        //Data=[14, 1, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=1, 10=2, 14=0}
        //
        //Inserting 8:
        //Size=4, Capacity=16, LF=25
        //Data=[14, 8, 10, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=3, 8=1, 10=2, 14=0}
        //
        //Inserting 7:
        //Size=5, Capacity=16, LF=31
        //Data=[14, 8, 10, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=3, 7=4, 8=1, 10=2, 14=0}
        //
        //Inserting 9:
        //Size=6, Capacity=16, LF=37
        //Data=[14, 8, 10, 1, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=3, 7=4, 8=1, 9=5, 10=2, 14=0}
        //
        //Inserting 3:
        //Size=7, Capacity=16, LF=43
        //Data=[14, 8, 10, 1, 7, 9, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=3, 3=6, 7=4, 8=1, 9=5, 10=2, 14=0}
        //
        //Inserting 2:
        //Size=8, Capacity=16, LF=50
        //Data=[14, 8, 10, 2, 7, 9, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=3, 3=6, 7=4, 8=1, 9=5, 10=2, 14=0}
        //
        //Inserting 4:
        //Size=9, Capacity=16, LF=56
        //Data=[14, 8, 10, 4, 7, 9, 3, 1, 2, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 7=4, 8=1, 9=5, 10=2, 14=0}
        //
        //Inserting 16:
        //Size=10, Capacity=16, LF=62
        //Data=[16, 14, 10, 4, 8, 9, 3, 1, 2, 7, 0, 0, 0, 0, 0, 0]
        //Mapping={16=0, 1=7, 2=8, 3=6, 4=3, 7=9, 8=4, 9=5, 10=2, 14=1}
        //
        //Inserting 12:
        //Size=11, Capacity=16, LF=68
        //Data=[16, 14, 10, 4, 12, 9, 3, 1, 2, 7, 8, 0, 0, 0, 0, 0]
        //Mapping={16=0, 1=7, 2=8, 3=6, 4=3, 7=9, 8=10, 9=5, 10=2, 12=4, 14=1}
        //
        //Inserting 15:
        //Size=12, Capacity=32, LF=37
        //Data=[16, 14, 15, 4, 12, 10, 3, 1, 2, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={16=0, 1=7, 2=8, 3=6, 4=3, 7=9, 8=10, 9=11, 10=5, 12=4, 14=1, 15=2}
        //
        //Updating key 16 to 6
        //Size=12, Capacity=32, LF=37
        //Data=[15, 14, 10, 4, 12, 9, 3, 1, 2, 7, 8, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 6=11, 7=9, 8=10, 9=5, 10=2, 12=4, 14=1, 15=0}
        //
        //Updating key 9 to 13
        //Size=12, Capacity=32, LF=37
        //Data=[15, 14, 13, 4, 12, 10, 3, 1, 2, 7, 8, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 6=11, 7=9, 8=10, 10=5, 12=4, 13=2, 14=1, 15=0}
        //
        //Updating key 12 to 18
        //Size=12, Capacity=32, LF=37
        //Data=[18, 15, 13, 4, 14, 10, 3, 1, 2, 7, 8, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 18=0, 3=6, 4=3, 6=11, 7=9, 8=10, 10=5, 13=2, 14=4, 15=1}
        //
        //Extracting 18:
        //Size=11, Capacity=32, LF=34
        //Data=[15, 14, 13, 4, 8, 10, 3, 1, 2, 7, 6, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 6=10, 7=9, 8=4, 10=5, 13=2, 14=1, 15=0}
        //
        //Extracting 15:
        //Size=10, Capacity=32, LF=31
        //Data=[14, 8, 13, 4, 7, 10, 3, 1, 2, 6, 15, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 6=9, 7=4, 8=1, 10=5, 13=2, 14=0}
        //
        //Extracting 14:
        //Size=9, Capacity=32, LF=28
        //Data=[13, 8, 10, 4, 7, 6, 3, 1, 2, 14, 15, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=8, 3=6, 4=3, 6=5, 7=4, 8=1, 10=2, 13=0}
        //
        //Extracting 13:
        //Size=8, Capacity=16, LF=50
        //Data=[10, 8, 6, 4, 7, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0]
        //Mapping={1=7, 2=5, 3=6, 4=3, 6=2, 7=4, 8=1, 10=0}

    }

    
}
