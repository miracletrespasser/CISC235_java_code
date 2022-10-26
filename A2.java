
public class A2 {

	/**
	 * Merges two sorted lists so that {@code s} contains all of the elements
	 * of both lists in sorted order and {@code t} ends up empty (in other words, 
	 * all of the elements {@code t} are inserted into {@code s} in sorted order).
	 * The lists are assumed to be sorted in ascending order.
	 * 
	 * <p>
	 * An efficient implementation of this method runs in O(n) time for two
	 * lists of size n.
	 * 
	 * @param s a sorted linked list
	 * @param t a sorted linked list
	 */
	public static void merge(LinkedList<Integer> s, LinkedList<Integer> t) {
		int indexS =0;
		while(indexS<=s.size()-1 && t.size()!=0) {
			if (s.get(indexS)<t.head().elem) {
				indexS++;
			}else {
				s.add(indexS,t.removeFront());
			}
		}
		while(t.size() != 0) {
			s.add(t.removeFront());
		}
	}
}
