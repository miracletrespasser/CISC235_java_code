package a4;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * A binary heap where the minimum value in the heap can be retrieved in O(1)
 * time and removed in O(log n) time. Inserting a value into the heap has
 * worst-case O(log n) complexity if there is capacity for the inserted element.
 * 
 * <p>
 * A heap is dynamically re-sizable. If insertion causes the heap to grow then
 * O(n) time is required to re-size the heap and copy existing heap elements.
 *
 */
//we construct a minimum-heap here instead of maximum-heap
public class MinBinaryHeap {

	private int[] arr;
	private int size;
	/**
	 * Initializes an empty heap with capacity 16.
	 */
	public MinBinaryHeap() {
		this.arr = new int[16];
		this.size = 0;
	}

	/**
	 * Initializes a heap by copying the elements of the specified heap into the
	 * heap and then building a heap from the copied elements.
	 * 
	 * @param arr  an array of elements
	 * @param size
	 */
	public MinBinaryHeap(int[] arr, int size) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		// make the capacity of this heap a power of 2
		int capacity = size == 1 ? 1 : Integer.highestOneBit(size - 1) * 2;
		this.arr = Arrays.copyOf(arr, capacity);
		this.size = size;
		for (int i = size / 2 - 1; i >= 0; i--) {
			this.heapify(i);
		}
		
	}

	private static int left(int index) {
		return 2 * index + 1;
	}

	private static int right(int index) {
		return 2 * index + 2;
	}

	private static int parent(int index) {
		return (index - 1) / 2;
	}
	public int size() {
		return this.size;
	}

	/**
	 * Inserts an element into this heap.
	 * 
	 * <p>
	 * Inserting a value into the heap has worst-case O(log n) complexity if there
	 * is capacity for the inserted element.
	 * 
	 * <p>
	 * A heap is dynamically re-sizable. If insertion causes the heap to grow then
	 * O(n) time is required to re-size the heap and copy existing heap elements.
	 * 
	 * @param val the value to insert
	 */
	public void insert(int val) {
		if (this.size == this.arr.length) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length * 2);
		}
		int i = this.size;
		int pi = parent(i);
		while (i > 0 && this.arr[pi] > val) {
			this.arr[i] = this.arr[pi];
			i = pi;
			pi = parent(i);
		}
		this.arr[i] = val;
		this.size++;
		
	}

	/**
	 * Returns the minimum value in this heap in O(1) time.
	 * 
	 * @return the minimum value in this heap 
	 * @throws RuntimeException if this heap is empty
	 */
	public int min() {
		if (this.size == 0) {
			throw new RuntimeException();
		}
		return this.arr[0];
	}

	/**
	 * Removes and returns the minimum value in this heap in O(log n) time.
	 * 
	 * @return the minimum value in this heap 
	 * @throws RuntimeException if this heap is empty
	 */
	public int removeMin() {
		int result = this.min();
		this.arr[0] = this.arr[this.size - 1];
		// no need to null out this.arr[this.size - 1] for an array of primitive type
		this.heapify(0);
		this.size--;
		return result;
	}

	private void heapify(int i) {
		int iLeft = left(i);
		int iRight = right(i);
		int iMax = i;
		if (iLeft < this.size && this.arr[iLeft] < this.arr[iMax]) {
			iMax = iLeft;
		}
		if (iRight < this.size && this.arr[iRight] < this.arr[iMax]) {
			iMax = iRight;
		}
		if (iMax != i) {
			int tmp = this.arr[i];
			this.arr[i] = this.arr[iMax];
			this.arr[iMax] = tmp;
			this.heapify(iMax);
		}
	}

	/**
	 * Returns a string representation of this heap.
	 */
	@Override
	public String toString() {
		StringJoiner j = new StringJoiner(", ", "min heap: ", "");
		for (int i = 0; i < this.size; i++) {
			j.add(String.valueOf(this.arr[i]));
		}
		return j.toString();
	}
	public static void main(String[] args) {

		MinBinaryHeap h = new MinBinaryHeap();
		h.insert(8);
		h.insert(24);
		h.insert(12);
		h.insert(1);
		h.insert(10);
		h.insert(7);
		h.insert(3);
		System.out.println(h);

		int x[] = { 8, 24, 12, 1, 10, 7, 3 };
		MinBinaryHeap h2 = new MinBinaryHeap(x, x.length);
		System.out.println(h2);

		for (int i = 0; i < x.length; i++) {
			System.out.println(h2.removeMin() + ", " + h2);
		}
	}
	
}
