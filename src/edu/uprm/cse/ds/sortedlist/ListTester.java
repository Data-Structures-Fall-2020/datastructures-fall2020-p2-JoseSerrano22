package edu.uprm.cse.ds.sortedlist;

public class ListTester {

	/**
	 * @param args d
	 */
	public static void main(String[] args) {
		SortedList<String> theList = new SortedCircularDoublyLinkedList<>();
		System.out.println("Empty List: " + theList.isEmpty());
		theList.add("Bob");
		theList.add("Ron");
		printList(theList);
		theList.add("Jil");
		System.out.println("Element 0 is Bob: " + theList.get(0).equals("Bob"));
		System.out.println("Element 1 is Jil: " + theList.get(1).equals("Jil"));
		theList.add("Amy");
		System.out.println("First element is Amy: " + theList.first().equals("Amy"));
		theList.add("Ned");
		System.out.println("Last element is Ned: " + theList.last().equals("Ned"));  // must be false
		System.out.println("Last element is Ron: " + theList.last().equals("Ron"));
		theList.add("Cal");
//		printReverseList(theList);
		System.out.println("Remove element at position 1: " + theList.remove(1));
//		printReverseList(theList);
		System.out.println("Remove last elements: " + theList.remove("Ron"));
		printList(theList);
		System.out.println();
//		printReverseList(theList);
		System.out.println("contains: " + theList.contains("Ron"));
		theList.clear();
		System.out.println(theList.last());
	}

	private static void printList(SortedList<String> theList) {
 		for (int i=0; i < theList.size(); i++){
 			System.out.println(theList.get(i));
		}

	}

//	private static void printReverseList(SortedList<String> theList) {
//		for (ReverseIterator<String> iter = theList.reverseIterator(); iter.hasPrevious(); ){
//			System.out.println(iter.previous());
//		}
//	}

}
