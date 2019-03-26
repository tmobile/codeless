package scratch;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void arrayListInsertionTest() {
		ArrayList<Integer> list=new ArrayList<Integer>();
	    list.add(0);
	    list.add(1);
	    list.add(2);
	    list.add(3); 
	    //Result = [0, 1, 2, 3]
	    list.add(1, 11);
	    list.add(2, 12);
	    //Result = [0, 11, 12, 1, 2, 3]
	    
	    System.out.println(list);
	    
	    // throws concurrent modification exception -> i think this is treated like a list.forEach({}) call, with internal iterator
//	    int i = 0;
//	    for(Integer element : list) {
//	    	if(element == 12) {
//	    		list.add(i+1, -10);
//	    	}
//	    	i=i++;
//	    }
	    ArrayList<Integer> secondList = new ArrayList<>();
	    secondList.add(-20);
	    secondList.add(-30);
	    
	    // no exception -> no iterator
	    for(int i = 0; i<list.size(); i++) {
	    	if(list.get(i) == 12) {
	    		list.add(i+1, -10);
	    		list.addAll(i+2, secondList);
	    	}
	    }
	    
	    System.out.println(list);
	  //Result = [0, 11, 12, -10, 1, 2, 3]
	}
}
