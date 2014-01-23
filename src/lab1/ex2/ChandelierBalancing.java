package lab1.ex2;

import java.util.ArrayList;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

public class ChandelierBalancing {
	private static Store store;
	public static void main(String[] args) {
		long T1, T2, T;
		T1 = System.currentTimeMillis();
		doWork();
		T2 = System.currentTimeMillis();
		T = T2 - T1;
		System.out.println("\n\t*** Execution time = " + T + " ms");
	}
	private static void doWork() {
		store = new Store();
		// i/o
		IntVar[] w = new IntVar[9];
		for(int i = 0; i < 9; ++i){
			w[i] = new IntVar(store,Character.toString((char) ('a'+i)),1,9);
		}
		
		//intermediate variables
		IntVar leftLeft = new IntVar(store);
		IntVar leftRight = new IntVar(store);

		IntVar middleLeft = new IntVar(store);
		IntVar middleRight = new IntVar(store);
		
		IntVar rightLeft = new IntVar(store);
		IntVar rightRight = new IntVar(store);
		

		IntVar topLeft = new IntVar(store);
		IntVar topMiddle = new IntVar(store);
		IntVar topRight = new IntVar(store);
		
		
		//TODO create design
		
		Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
		
		search.setSolutionListener(new PrintOutListener<IntVar>());
		
		search.getSolutionListener().searchAll(true);
		
		
		SelectChoicePoint<IntVar> select =
				new SimpleSelect<IntVar>(
				w,
				null, // input order
				new IndomainMin<IntVar>());
		boolean result = search.labeling(store, select);
	}
	private static int i(char c) {
		return c-'a';
	}
}
