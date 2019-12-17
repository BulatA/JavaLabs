package queue_pkg;

public class run {
	public static void main(String[] args) {
		queue q = new queue(5);
		q.inqueue(6);
		q.inqueue(7);
		q.inqueue(3);
		q.inqueue(1);
		q.inqueue(10);
		q.print();
		System.out.println(q.toArray().length);
		int a[] = q.sort(q.toArray());
		q.printInfo();
		q.dequeue();
		q.printInfo();
	}
}
