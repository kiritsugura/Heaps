
import java.util.ArrayList;
/*Brute force priority queue that stores the objects in a linear array list in a sorted format.*/
public class BruteForceQueue<E extends Comparable> implements PriorityQueue<E>{
    /*The items in the priority queue.*/
    private ArrayList<E> items;
    /*The type of priority queue that this is.*/
    private HeapType type;
    /*Constructor for the brute force solver.*/
    public BruteForceQueue(HeapType ty){
        type=ty;
        items=new ArrayList();
    }
    /*Adds an item to the ority queue.
      @param: newItem is the item that is being added to the queue.*/
    @Override
    public void push(E newItem){
        if(items.size()!=0){
            if(type==HeapType.MAXHEAP){
                for(int i=0;i<items.size();i++){
                    if(items.get(i).compareTo(newItem)<0){
                        items.add(i,newItem);
                        break;
                    }
                }
            }else if(type==HeapType.MINHEAP){
                for(int i=0;i<items.size();i++){
                    if(items.get(i).compareTo(newItem)>0){
                        items.add(i,newItem);
                        break;
                    }
                }            
            }
        }else{
            items.add(newItem);
        }
    }
    /*@return: returns the highest priority item in the queue.*/
    @Override
    public E pop() {
        return items.remove(1);
    }
    /*@return: returns a String representation of this object.*/
    @Override
    public String toString(){
        return items.toString();
    }
    /*Changes the type of heap from min to max or max to min.*/
    public void changeType(){
        type=type==HeapType.MAXHEAP ? HeapType.MINHEAP:HeapType.MAXHEAP;
        resort();
    }
    /*Resorts the items based on the priority queue type.*/
    protected void resort(){
        if(type==HeapType.MAXHEAP){
            for(int i=0;i<items.size()-1;i++){
                if(items.get(i).compareTo(items.get(i+1))<0){
                    swap(i,i+1);
                    i=0;
                }
            }
        }else if(type==HeapType.MINHEAP){
            for(int i=0;i<items.size()-1;i++){
                if(items.get(i).compareTo(items.get(i+1))>0){
                    swap(i,i+1);
                    i=0;
                }
            }            
        }        
    }
    /*Swaps two items in the queue.
      @param: childIndex and parentIndex are the indices of the items being swapped.*/
    @Override
    public void swap(int childIndex, int parentIndex) {
        E temp=items.get(childIndex);
        items.set(childIndex,items.get(parentIndex));
        items.set(parentIndex,temp);
    }
}
