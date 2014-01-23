package lab1;

import org.jacop.constraints.AndBool;
import org.jacop.constraints.OrBool;
import org.jacop.constraints.Xor;
import org.jacop.constraints.XorBool;
import org.jacop.core.BooleanVar;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.PrintOutListener;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSelect;

public class FullAdder {

	public static void main(String[] args) {
		long T1, T2, T;
		T1 = System.currentTimeMillis();
		doWork();
		T2 = System.currentTimeMillis();
		T = T2 - T1;
		System.out.println("\n\t*** Execution time = " + T + " ms");
	}
	private static void doWork() {
		Store store = new Store();
		// i/o
		BooleanVar in1 = new BooleanVar(store, "In1");
		BooleanVar in2 = new BooleanVar(store, "In2");
		BooleanVar c = new BooleanVar(store, "C");
		BooleanVar s = new BooleanVar(store, "S");
		BooleanVar carry = new BooleanVar(store, "Carry");
		BooleanVar[] io = new BooleanVar[] {in1,in2,c,carry,s};
		
		//intermediate results
		BooleanVar t1 = new BooleanVar(store, "T1");
		BooleanVar t2 = new BooleanVar(store, "T2");
		BooleanVar t3 = new BooleanVar(store, "T3");
		
		//gates
		BooleanVar[] inputs = new BooleanVar[] {in1,in2};
		XorBool x1 = new XorBool(inputs, t1);
		XorBool x2 = new XorBool(new BooleanVar[] {t1,c},s);
		AndBool a1 = new AndBool(inputs,t2);
		AndBool a2 = new AndBool(new BooleanVar[]{t1,c},t3);
		OrBool o1 = new OrBool(new BooleanVar[]{t2,t3},carry);
		
		x1.impose(store);
		x2.impose(store);
		a1.impose(store);
		a2.impose(store);
		o1.impose(store);
		
		Search<BooleanVar> search = new DepthFirstSearch<BooleanVar>(); 
		
		search.setSolutionListener(new PrintOutListener<BooleanVar>());
		
		search.getSolutionListener().searchAll(true);
		
		
		SelectChoicePoint<BooleanVar> select =
				new SimpleSelect<BooleanVar>(
				io,
				null, // input order
				new IndomainMin<BooleanVar>());
		boolean result = search.labeling(store, select);
		
		
		
		
		
		
		
	}
}
