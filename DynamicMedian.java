package a4;

public class DynamicMedian {
	private MaxBinaryHeap max_heap;
	private MinBinaryHeap min_heap;
	private int median;
	private int size;
	private int sign;//tells the place of median in the class
	/**
	 * Initializes this collection to have no elements.
	 */
	public DynamicMedian() {
		this.max_heap=new MaxBinaryHeap();
		this.min_heap=new MinBinaryHeap();
		this.size=0;
		this.median=0;
		this.sign=0;
	}
	

	/**
	 * Returns the number of values in this collection.
	 * 
	 * @return the number of values in this collection
	 */
	public int size() {
		return this.size;
	}
	public String max_arr() {
		return this.max_heap.toString();
	}
	public String min_arr() {
		return this.min_heap.toString();
	}
	/**
	 * Adds an element to this collection.
	 * 
	 * <p>
	 * This method should have complexity in O(log n) where n is the size of this
	 * collection if there is capacity for the element in the collection.
	 * 
	 * <p>
	 * If insertion causes this collection to grow in capacity then
	 * O(n) time is required to re-size the collection and copy existing
	 * elements.
	 * 
	 * @param val the value to add to this collection
	 */
	public void insert(int val) {
		if(this.size==0) {
			this.median=val;
		}
		if(val<=this.median) {
			this.max_heap.insert(val);
		}
		else if(val>=this.median) {
			this.min_heap.insert(val);
		}
		this.size++;
		//restore balance of two heaps
		if(this.max_heap.size()>this.min_heap.size()+1) {
			this.min_heap.insert(this.max_heap.removeMax());
		}else if(this.min_heap.size()>this.max_heap.size()+1) {
			this.max_heap.insert(this.min_heap.removeMin());
		}
		
		//decide the new median
		if(this.max_heap.size()>this.min_heap.size()) {
			this.median=this.max_heap.max();
			this.sign=0;
		}else if(this.min_heap.size()>this.max_heap.size()) {
			this.median=this.min_heap.min();
			this.sign=1;
		}else {
			if((this.max_heap.size()!=0)&&(this.min_heap.size()!=0)) {
				if(this.max_heap.max()>this.min_heap.min()) {
					this.median=this.min_heap.min();
					//indicating the position of median
					this.sign=1;
				}else {
					this.median=this.max_heap.max();
					this.sign=0;
				}
			
			}
		
		}
	}

	/**
	 * Returns the median element of this collection in O(1) time.
	 * 
	 * <p>
	 * If there are an even number of elements in this collection then the smaller
	 * of the two middle elements are returned. For example, if this collection has
	 * the elements [3, 5, 9, 10] then the median element is taken to be 5.
	 * 
	 * @return the median element of this collection
	 * @throws RuntimeException if this collection is empty
	 */
	public int median() {
		if (this.size == 0) {
			throw new RuntimeException();
		}
		return this.median;
	}

	/**
	 * Removes and returns the median element of this collection in O(log n) time.
	 * 
	 * <p>
	 * If there are an even number of elements in this collection then the smaller
	 * of the two middle elements are returned. For example, if this collection has
	 * the elements [3, 5, 9, 10] then the median element is taken to be 5.
	 * 
	 * @return the median element of this collection
	 * @throws RuntimeException if this collection is empty
	 */
	public int removeMedian() {
		int temp;
		if (this.size == 0) {
			throw new RuntimeException();
		}
		if (sign==1) {
			temp = this.min_heap.removeMin();
		}else {
			temp =this.max_heap.removeMax();
		}
		this.size--;
		//reset the median and restore balance between two heaps
		//decide the new median
				if(this.max_heap.size()>this.min_heap.size()) {
					this.median=this.max_heap.max();
					this.sign=0;
				}else if(this.min_heap.size()>this.max_heap.size()) {
					this.median=this.min_heap.min();
					this.sign=1;
				}else {
					if((this.max_heap.size()!=0)&&(this.min_heap.size()!=0)) {
						if(this.max_heap.max()>this.min_heap.min()) {
							this.median=this.min_heap.min();
							//indicating the position of median
							this.sign=1;
						}else {
							this.median=this.max_heap.max();
							this.sign=0;
						}
					
					}
				
				}
		return temp;

	}
	public static void main(String[] args) {

		DynamicMedian h = new DynamicMedian();
		h.insert(8);
		h.insert(24);
		h.insert(12);
		h.insert(1);
		h.insert(10);
		h.insert(7);
		h.insert(3);
		System.out.println(h.max_arr());
		System.out.println(h.min_arr());
		while(h.size()>0){
			System.out.println(h.median());
			System.out.println(h.removeMedian());
			System.out.println(h.max_arr());
			System.out.println(h.min_arr());
		}
		
	}
}
