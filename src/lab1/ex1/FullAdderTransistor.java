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
		BooleanVar a = new BooleanVar(store, "A");
		BooleanVar b = new BooleanVar(store, "B");
		BooleanVar c = new BooleanVar(store, "C");
		BooleanVar s = new BooleanVar(store, "S");
		BooleanVar carry = new BooleanVar(store, "carry");
		BooleanVar[] io = new BooleanVar[] {a,b,c,carry,s};
		
		BooleanVar vdd = new BooleanVar(store, "Vdd", 1, 1);
		BooleanVar ground = new BooleanVar(store, "ground", 0, 0);
		
//		BooleanVar topJoint = new BooleanVar(store, "topJoint");
//		BooleanVar midJoint = new BooleanVar(store, "midJoint");
//		
		BooleanVar out1 = new BooleanVar(store, "1out", 0, 1);
		BooleanVar cOut = new BooleanVar(store, "cOut", 0, 1);
		BooleanVar out3 = new BooleanVar(store, "3out", 0, 1);
		BooleanVar out4 = new BooleanVar(store, "4out", 0, 1);
		BooleanVar out5 = new BooleanVar(store, "5out", 0, 1);
//		PrimitiveConstraint trans1 = FullAdderTransistor.pTrans(a, vdd, out1);
//		
//		BooleanVar out21 = new BooleanVar(store, "out21");
//		BooleanVar out22 = new BooleanVar(store, "out22");
//		PrimitiveConstraint trans2 = FullAdderTransistor.pTrans(c, out21, out22);
//		
//		BooleanVar topJoint = new BooleanVar(store, "topJoint");
//		OrBool topJointConstraint = new
		
		PrimitiveConstraint carryTrans1 = pTrans(a, vdd, out1);
		PrimitiveConstraint carryTrans2 = pTrans(c, out1, cOut);
		PrimitiveConstraint carryTrans3 = nTrans(c, cOut, out3);
		PrimitiveConstraint carryTrans4 = nTrans(a, out3, ground);
		PrimitiveConstraint carryTrans5 = pTrans(b, vdd, out1);
		PrimitiveConstraint carryTrans6 = nTrans(b, out3, ground);
		
		PrimitiveConstraint carryTrans7 = pTrans(b, vdd, out4);
		PrimitiveConstraint carryTrans8 = pTrans(a, out4, cOut);
		PrimitiveConstraint carryTrans9 = nTrans(a, cOut, out5);
		PrimitiveConstraint carryTrans10 = nTrans(b, out5, ground);
		
		
		BooleanVar sumOut1 = new BooleanVar(store, "sumOut1", 0, 1);
		BooleanVar sumOut2 = new BooleanVar(store, "sumOut2", 0, 1);
		BooleanVar sumOut3 = new BooleanVar(store, "sumOut3", 0, 1);
		BooleanVar sumOut4 = new BooleanVar(store, "sumOut4", 0, 1);
		BooleanVar sumOut5 = new BooleanVar(store, "sumOut5", 0, 1);
		BooleanVar sumOut6 = new BooleanVar(store, "sumOut6", 0, 1);
		BooleanVar sum = new BooleanVar(store, "sum",0,1);
		
		PrimitiveConstraint sumTrans1 = pTrans(a, vdd, sumOut1);
		PrimitiveConstraint sumTrans2 = nTrans(a, sumOut2, ground);
		
		PrimitiveConstraint sumTrans3 = pTrans(b, vdd, sumOut1);
		PrimitiveConstraint sumTrans4 = pTrans(cOut, sumOut1, sum);
		PrimitiveConstraint sumTrans5 = nTrans(cOut, sum, sumOut2);
		PrimitiveConstraint sumTrans6 = nTrans(b, sumOut2, ground);
		
		PrimitiveConstraint sumTrans7 = pTrans(c, vdd, sumOut1);
		PrimitiveConstraint sumTrans8 = nTrans(c, sumOut2, ground);
		
		PrimitiveConstraint sumTrans9 = pTrans(a, vdd, sumOut3);
		PrimitiveConstraint sumTrans10 = pTrans(b, sumOut3, sumOut4);
		PrimitiveConstraint sumTrans11 = pTrans(c, sumOut4, sum);
		PrimitiveConstraint sumTrans12 = nTrans(c, sum, sumOut5);
		PrimitiveConstraint sumTrans13 = nTrans(b, sumOut5, sumOut6);
		PrimitiveConstraint sumTrans14 = nTrans(a, sumOut6, ground);
		
		inverter(cOut, carry, store);
		inverter(sum, s, store);
		
		carryTrans1.impose(store);
		carryTrans2.impose(store);
		carryTrans3.impose(store);
		carryTrans4.impose(store);
		carryTrans5.impose(store);
		carryTrans6.impose(store);
		carryTrans7.impose(store);
		carryTrans8.impose(store);
		carryTrans9.impose(store);
		carryTrans10.impose(store);
		sumTrans1.impose(store);
		sumTrans2.impose(store);
		sumTrans3.impose(store);
		sumTrans4.impose(store);
		sumTrans5.impose(store);
		sumTrans6.impose(store);
		sumTrans7.impose(store);
		sumTrans8.impose(store);
		sumTrans9.impose(store);
		sumTrans10.impose(store);
		sumTrans11.impose(store);
		sumTrans12.impose(store);
		sumTrans13.impose(store);
		sumTrans14.impose(store);
		
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
		return new IfThen(new XeqC(b,0),new XeqY(x,y));
	}
	
	private static PrimitiveConstraint nTrans(BooleanVar b, BooleanVar x, BooleanVar y) {
		return new IfThen(new XeqC(b,1),new XeqY(x,y));
	}
	
	private static void inverter(BooleanVar a, BooleanVar b, Store s){
		PrimitiveConstraint nTrans = FullAdderTransistor.nTrans(a, new BooleanVar(s, "True", 1, 1), b);
		PrimitiveConstraint pTrans = FullAdderTransistor.pTrans(a, b, new BooleanVar(s, "False", 0, 0));
		pTrans.impose(s);
		nTrans.impose(s);
	}
}
