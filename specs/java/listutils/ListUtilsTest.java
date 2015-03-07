package listutils;


import org.concordion.integration.junit3.ConcordionTestCase;
import edu.holycross.shot.listutils.ListDiff;
import java.util.ArrayList;


public class ListUtilsTest extends ConcordionTestCase {


    /**
     * @param l1 Ordered list expressed as comma-delimited String.
     * @param l2 Ordered list expressed as comma-delimited String.
     */
    public Iterable<String> getLcs(String l1, String l2) {
	// prepare data for API:
	ArrayList<String> arrayList1 = new ArrayList<String> ();
	String[] elems1 = l1.split(",");
	for (int i = 0; i < elems1.length; i++) {
	    String elem = elems1[i];
	    arrayList1.add(elem);
	}
	
	ArrayList<String> arrayList2 = new ArrayList<String> ();

	String[] elems2 = l2.split(",");
	for (int i = 0; i < elems2.length; i++) {
	    String elem = elems2[i];
	    arrayList2.add(elem);
	    }

	ListDiff ldiff = new ListDiff(arrayList1, arrayList2);
	return (ldiff.getLcs());
    }





    public Iterable<String> getUnique1(String l1, String l2) {
	// prepare data for API:
	ArrayList<String> arrayList1 = new ArrayList<String> ();
	String[] elems1 = l1.split(",");
	for (int i = 0; i < elems1.length; i++) {
	    String elem = elems1[i];
	    arrayList1.add(elem);
	}
	
	ArrayList<String> arrayList2 = new ArrayList<String> ();
	String[] elems2 = l2.split(",");
	for (int i = 0; i < elems2.length; i++) {
	    String elem = elems2[i];
	    arrayList2.add(elem);
	}
	    
	ListDiff ldiff = new ListDiff(arrayList1, arrayList2);
	return (ldiff.getList1Only());
    }


    

    

    public Iterable<String> getUnique2(String l1, String l2) {
	// prepare data for API:
	ArrayList<String> arrayList1 = new ArrayList<String> ();
	String[] elems1 = l1.split(",");
	for (int i = 0; i < elems1.length; i++) {
	    String elem = elems1[i];
	    arrayList1.add(elem);
	}
	
	ArrayList<String> arrayList2 = new ArrayList<String> ();
	String[] elems2 = l2.split(",");
	for (int i = 0; i < elems2.length; i++) {
	    String elem = elems2[i];
	    arrayList2.add(elem);
	}
	    
	ListDiff ldiff = new ListDiff(arrayList1, arrayList2);
	return (ldiff.getList2Only());
    }


    
}
