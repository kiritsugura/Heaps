

/*Interface for priority queue derived objects.*/
public interface PriorityQueue<E extends Comparable>{
    /*Adds 'newItem' to the priority queue.*/
    public void push(E newItem);
    /*Removes the highest priority item in the queue and returns it.*/
    public E pop();
    /*Swaps two the values at 'childIndex' and 'parentIndex'.*/
    public void swap(int childIndex,int parentIndex);
}
