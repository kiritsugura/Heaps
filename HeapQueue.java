import java.util.ArrayList;
import java.util.NoSuchElementException;

/*Heap data structure that supports min-heap and max-heat types.*/
public class HeapQueue<E extends Comparable> implements PriorityQueue<E>{
    /*The type of heap that this type is.*/
    private HeapType type;
    /*The items in the heap.*/
    private ArrayList<E> items;
    /*Default constructor for a heap object.*/
    public HeapQueue(HeapType ty){
        type=ty;
        items=new ArrayList();
        items.add(null);
    }
    /*Takes an arraylist and heapifies(word?) it.*/
    public HeapQueue(HeapType ty,ArrayList<E> other){
        type=ty;
        items=new ArrayList();
        items.add(null);
        for(E item:other){
            items.add(item);
        }
        reheapify();
    }
    /*Changes the type of heap from min to max or max to min.*/
    public void changeType(){
        type=type==HeapType.MAXHEAP ? HeapType.MINHEAP:HeapType.MAXHEAP;
        reheapify();
    }
    /*Pushes an item onto the Heap.
      @param: the item that is being added to the heap.*/
    public void push(E newItem){
        items.add(newItem);
        int index=items.size()-1;
        int parentIndex=items.size()>>1;
        //check to see correctness
        while(index>1 && isWay(type,index,parentIndex)){
            if(isWay(type,index,parentIndex)){
                swap(index,parentIndex);
                index=parentIndex;
                parentIndex=index>>1;
            }else{
                parentIndex=index;
            }
        }       
    }
    /*Determines if the parent and current item positioning needs to be modified.
      @param: type is an enum denotating the type this heap is, me is the index of the current value, while parent is its parent's index.
      @return: If a maxheap, then returns true if the current value at index me is greater than its parent value, else returns false;
               If a minheap, then returns true if the current value at index me is less than its parent value, else returns false.*/
    private boolean isWay(HeapType type,int me,int parent){
        return (type==HeapType.MAXHEAP) ? items.get(parent).compareTo(items.get(me))<0: items.get(parent).compareTo(items.get(me))>0;                 
    }
    /*Reorganizes the heap.*/
    protected void reheapify(){
        swap(1,items.size()-1);
        int index=items.size()-1,parent=items.size()>>1;
        while(index>1){
            if(isWay(type,index,parent)){
                swap(index,parent);
                index=items.size()-1;
            }else{
                index--;
                parent=index>>1;
            }
        }        
    }
    /*Method that swaps a parent and child node with each given index.*/
    public void swap(int childIndex,int parentIndex){
        E temp=items.get(parentIndex);
        items.set(parentIndex,items.get(childIndex));
        items.set(childIndex,temp);       
    }
    /*Takes the highest priority item off the heap and returns it after adjusting the heap.
      @returns: returns the highest priority item.*/
    public E pop() throws NoSuchElementException{
        swap(1,items.size()-1);
        int index=1;
        int leftChild=2;
        int rightChild=3;
        E priority=items.remove(items.size()-1);
        while(leftChild<items.size() || rightChild<items.size()){
            int domChild=(rightChild<items.size() && isWay(type,rightChild,leftChild)) ? rightChild:leftChild;
            if(isWay(type,domChild,index)){
                swap(domChild,index);
                index=domChild;
                leftChild=index+(int)Math.pow(2, log2(index));
                rightChild=index+(int)Math.pow(2, log2(index))+1;
            }else{
                break;
            }
        }
        return priority;
    }
    /*@return: returns a String representation of this heap queue object.*/
    @Override
    public String toString(){
        return getString("",1);
    }
    /*@return: builds the String for the String representation of this object.*/
    private String getString(String me,int currentIndex){
        me="";
        me+=getSpaces(currentIndex)+items.get(currentIndex)+"\n";
        int left=currentIndex+(int)Math.pow(2, log2(currentIndex)),right=currentIndex+(int)Math.pow(2, log2(currentIndex)+1);
        if(items.size()>left){
            me+=getString(me,left);
        }
        if(items.size()>right){
            me+=getString(me,right);
        }
        return me;
    }
    /*@return: returns the closest power of 2 that index is closest to.*/
    private int log2(int index){
        int times=0;
        while(index>1){
            times++;
            index>>=1;
        }
        return times;
    }
    /*@return: returns the number of spaces based on position in the heap queue.*/
    private String getSpaces(int current){
        String spaces="";
        while(current>1){
            spaces+=" ";
            current/=2;
        }
        return spaces;
    }
}