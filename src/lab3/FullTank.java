package lab3;

import org.jacop.constraints.Cumulative;
import org.jacop.constraints.Max;
import org.jacop.constraints.XplusCeqZ;
import org.jacop.constraints.XplusClteqZ;
import org.jacop.core.IntDomain;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.PrintOutListener;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSelect;
import org.jacop.search.SmallestDomain;

public class FullTank {
	public static void main(String[] args) {
		long T1, T2, T;
		T1 = System.currentTimeMillis();
		doWork();
		T2 = System.currentTimeMillis();
		T = T2 - T1;
		System.out.println("\n\t*** Execution time = " + T + " ms");
	}

	/**
	 * This does work
	 */
	public static void doWork() {
		Store store = new Store();
		IntVar node1 = new IntVar(store, "1", 2, 4);
		IntVar node2 = new IntVar(store, "1", 3, 4);
		node2.addDom(1, 1);
		node2.addDom(2, 2);
		IntVar node3 = new IntVar(store, "1", 5, 6);
		node3.addDom(1, 2);
		node3.addDom(3, 3);
		IntVar node4 = new IntVar(store, "1", 5, 7);
		node4.addDom(1, 2);
		node4.addDom(4, 4);
		IntVar node5 = new IntVar(store, "1", 7, 7);
		node5.addDom(3, 4);
		node5.addDom(5, 5);
		IntVar node6 = new IntVar(store, "1", 8, 8);
		node6.addDom(4, 4);
		node6.addDom(6, 6);
		IntVar node7 = new IntVar(store, "1", 8, 9);
		node7.addDom(4, 5);
		node7.addDom(7, 7);
		IntVar node8 = new IntVar(store, "1", 9, 10);
		node8.addDom(6, 7);
		node8.addDom(8, 8);
		IntVar node9 = new IntVar(store, "1", 10, 10);
		node9.addDom(7, 8);
		node9.addDom(9, 9);
		IntVar node10 = new IntVar(store, "1", 1, 1);
		node10.addDom(8, 9);
		
		
		
		
	}
}
