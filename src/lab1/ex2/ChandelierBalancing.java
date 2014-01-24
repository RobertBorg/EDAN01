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
		IntVar leftLeft = new IntVar(store, "leftLeft", 1, 100);
		IntVar leftRight = new IntVar(store, "leftRight", 1, 100);

		IntVar middleLeft = new IntVar(store, "middleRight", 1, 100);
		IntVar middleRight = new IntVar(store, "middleRight", 1, 100);
		
		IntVar rightLeft = new IntVar(store, "rightRight", 1, 100);
		IntVar rightRight = new IntVar(store, "rightRight", 1, 100);
		
		IntVar sumTopLeft = new IntVar(store, "sumTopLeft", 1, 100);
		IntVar sumTopRight = new IntVar(store, "sumTopRight", 1, 100);
		
		IntVar topLeft = new IntVar(store, "topLeft", 1, 100);
		IntVar topMiddle = new IntVar(store, "topMiddle", 1, 100);
		IntVar topRight = new IntVar(store, "topRight", 1, 100);
		
		SumWeight swleftleft = new SumWeight(new IntVar[] {w[i('a')]}, new int[] {2}, leftLeft);
		SumWeight swleftRight = new SumWeight(new IntVar[] {w[i('b')],w[i('c')]}, new int[] {1,2},leftRight);
		
		SumWeight swMiddleLeft = new SumWeight(new IntVar[] {w[i('d')],w[i('e')]}, new int[] {2,1},middleLeft);
		SumWeight swMiddleRight = new SumWeight(new IntVar[] {w[i('f')]}, new int[] {1},middleRight);
		
		SumWeight swRightLeft = new SumWeight(new IntVar[] {w[i('g')],w[i('h')]}, new int[] {2,1},rightLeft);
		SumWeight swRightRight = new SumWeight(new IntVar[] {w[i('i')]}, new int[] {3},rightRight);
		
		Sum sTopLeft = new Sum(new IntVar[] {w[i('a')],w[i('b')],w[i('c')]}, topLeft);
		Sum sTopMiddle = new Sum(new IntVar[] {w[i('d')],w[i('e')],w[i('f')]}, topMiddle);
		Sum sTopRight = new Sum(new IntVar[] {w[i('g')],w[i('h')],w[i('i')]}, topRight);
		
		SumWeight swTopLeft = new SumWeight(new IntVar[] {topLeft},new int[] {3},sumTopLeft);
		SumWeight swTopRight = new SumWeight(new IntVar[] {topMiddle, topRight},new int[] {2,3},sumTopRight);
		
		//intermediate constraints
		swleftleft.impose(store);
		swleftRight.impose(store);
		
		swMiddleLeft.impose(store);
		swMiddleRight.impose(store);
		
		swRightLeft.impose(store);
		swRightRight.impose(store);
		
		sTopLeft.impose(store);
		sTopMiddle.impose(store);
		sTopRight.impose(store);
		
		swTopLeft.impose(store);
		swTopRight.impose(store);
		
		//constraints
		Alldifferent alldiff = new Alldifferent(w);
		XeqY left = new XeqY(leftLeft, leftRight);
		XeqY middle = new XeqY(middleLeft, middleRight);
		XeqY right = new XeqY(rightLeft, rightRight);
		XeqY top = new XeqY(sumTopLeft, sumTopRight);
		
		alldiff.impose(store);
		left.impose(store);
		middle.impose(store);
		right.impose(store);
		top.impose(store);
		
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
