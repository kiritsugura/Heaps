 
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
public class Tester {
    public static boolean done;
    public static void main(String[] args) {
        HeapQueue<Float> nums=new HeapQueue<>(HeapType.MAXHEAP);
        System.out.println("Creating a heap Queue.");
        for(int i=0;i<10;i++){
            nums.push(new Float(new Random().nextFloat()*200.0f));
        }
        System.out.println("Printing the heap Queue:");
        Float[] testPop=new Float[5];
        System.out.println(nums);
        System.out.println("Removing 5 items from the heap queue.");
        for(int i=0;i<5;i++){
            testPop[i]=nums.pop();
        }
        System.out.println("Printing the heap queue.");
        System.out.println(nums);
        System.out.println("The items removed were:");
        for(Float f:testPop){
            System.out.println(f);
        }
        ArrayList<Integer> stuff=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            stuff.add(new Integer(i));
        }
        System.out.println("Testing the second constructor:");
        HeapQueue<Integer> ints=new HeapQueue(HeapType.MINHEAP,stuff);
        System.out.println(ints);
        System.out.println("Testing the heap and brute force queues(Press enter to stop)");
        ints=new HeapQueue(HeapType.MAXHEAP);
        BruteForceQueue intsCounter=new BruteForceQueue(HeapType.MINHEAP);
        done=false;
        while(!done){
            testTimes(ints,intsCounter);
        }
    }   
    private static void testTimes(HeapQueue<Integer> heap,BruteForceQueue<Integer> bfq){
        heap=new HeapQueue(HeapType.MAXHEAP);
        bfq=new BruteForceQueue(HeapType.MAXHEAP);
        System.out.println("Starting the test");
        Scanner r=new Scanner(System.in);
        System.out.print("Enter the size: ");
        int num=0;
        try{
            String thisThing=r.nextLine();
            if(thisThing.indexOf(0)=='\n'){
                done=true;
            }else{
                num=Integer.valueOf(thisThing);
            }
        }catch(Exception e){
            System.out.println("Exiting");
            done=true;
        }
        if(!done){
            long time=System.currentTimeMillis();
            for(int i=0;i<num;i++){
                heap.push(new Integer(new Random().nextInt(1000)));
            }
            System.out.println("Created Heap in:"+(System.currentTimeMillis()-time)+" milliseconds.");
            time=System.currentTimeMillis();
            for(int i=0;i<num;i++){
                bfq.push(new Integer(new Random().nextInt(1000)));
            }      
            System.out.println("Created Brute Force Queue in:"+(System.currentTimeMillis()-time)+" milliseconds.");
            System.out.println("It contains: "+bfq.toString());
            System.out.println("Switching the types.");
            time=System.currentTimeMillis();
            heap.changeType();
            System.out.println("Modifying Heap took: "+(System.currentTimeMillis()-time)+" milliseconds.");
            time=System.currentTimeMillis();
            bfq.changeType();
            System.out.println("Modifying BFQ took: "+(System.currentTimeMillis()-time)+" milliseconds.");
            System.out.println("Heap contains: "+heap);
            System.out.println("BFQ contains: "+bfq);
        }
    }
}
