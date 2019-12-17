package queue_pkg;


public class queue {
	
	public queue(int _value) {
		value = _value;
	}
	
	private final int q_count = 10;
	
	private int value;
	
	public queue Next;
	
	public void inqueue(int data) {
		if(getSize()==q_count)
			dequeue();
		queue q = this;
		while(q.Next != null)
			q = q.Next;
		q.Next = new queue(data);
	}
	
	public void dequeue() {
		if(getSize()!=1) {
			this.value = this.Next.value;
			this.Next = this.Next.Next;
		}
	}
	
	public int[] toArray() {
		int[] array = new int[getSize()];
		queue q = this;
		int i = 0;
		while( q != null) {
			array[i] = q.value;
			q = q.Next;
			i++;
		}
		return array;
	}
	
	public int getSize() {
		queue q = this;
		int count = 0;
		while(q != null) {
			count++;
			q = q.Next;
		}
		return count;
	}
	//merge sort
	public int[] sort(int[] arr) {

        if (arr == null) {
            return null;
        }

        if (arr.length == 1) {
            return arr; 
        }

        int [] arrayB = new int[arr.length / 2];
        System.arraycopy(arr, 0, arrayB, 0, arr.length / 2);


        int [] arrayC = new int[arr.length - arr.length / 2];
        System.arraycopy(arr, arr.length / 2, arrayC, 0, arr.length - arr.length / 2);

        arrayB = sort(arrayB); 
        arrayC = sort(arrayC); 
        return mergeArray(arrayB, arrayC);
	}
	
	private int [] mergeArray(int[] array¿, int[] arrayB) {

		int [] arrayC = new int[array¿.length + arrayB.length];
		int positionA = 0, positionB = 0;

		for (int i = 0; i < arrayC.length; i++) {
			if (positionA == array¿.length){
				
				arrayC[i] = arrayB[positionB];
				positionB++;
				
			} else if (positionB == arrayB.length) {
				
				arrayC[i] = array¿[positionA];
				positionA++;
				
			} else if (array¿[positionA] < arrayB[positionB]) {
				
				arrayC[i] = array¿[positionA];
				positionA++;
				
			} else {
				
				arrayC[i] = arrayB[positionB];
				positionB++;
				
			}
		}
		return arrayC;
	}
	
	public double getMedian() {
		int[] arr = sort(toArray());
		int middle = getSize()/2;
		if(getSize()%2 == 0) 
		{
			return (double)(arr[middle - 1] + arr[middle])/2;
		}
		else 
		{
			return (double)arr[middle];
		}
	}
	
	public void print() {
		queue q = this;
		while(q != null) {
			System.out.print(q.value + "\n");
			q = q.Next;
		}
		System.out.println("\n");
	}
	
	public void printInfo() {
		int[] arr = toArray();
		int[] sort = sort(arr);
		System.out.println("[FIFO]|[Sorted]");
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] +"    |    "+ sort[i]);
		}
		System.out.println("Median:"+getMedian());
	}
}
