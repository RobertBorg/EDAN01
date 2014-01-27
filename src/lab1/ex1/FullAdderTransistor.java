package lab1.ex1;

import org.jacop.constraints.*;
import org.jacop.core.*;
import org.jacop.search.*;

public class FullAdderTransistor {
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
		BooleanVar in1 = new BooleanVar(store, "In1");
		BooleanVar in2 = new BooleanVar(store, "In2");
		BooleanVar c = new BooleanVar(store, "C");
		BooleanVar s = new BooleanVar(store, "S");
		BooleanVar carry = new BooleanVar(store, "Carry");
		BooleanVar[] io = new BooleanVar[] {in1,in2,c,carry,s};
		
		//TODO create design
		
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
	private static PrimitiveConstraint pTrans(BooleanVar b, BooleanVar x, BooleanVar y) {
		return new IfThen(new XeqC(b,1),new XeqY(x,y));
	}
	
	private static PrimitiveConstraint nTrans(BooleanVar b, BooleanVar x, BooleanVar y) {
		return new IfThen(new XeqC(b,0),new XeqY(x,y));
	}
	
	private static Constraint inverter(BooleanVar a, BooleanVar b, Store s){
		PrimitiveConstraint pTrans = FullAdderTransistor.pTrans(a, b, new BooleanVar(s, "False", 0, 0));
		PrimitiveConstraint nTrans = FullAdderTransistor.nTrans(a, b, new BooleanVar(s, "True", 1, 1));
		return new And(pTrans, nTrans);
	}
}
