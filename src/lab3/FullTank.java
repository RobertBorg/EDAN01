package lab3;

import org.jacop.constraints.Cumulative;
import org.jacop.constraints.Element;
import org.jacop.constraints.IfThen;
import org.jacop.constraints.Max;
import org.jacop.constraints.Subcircuit;
import org.jacop.constraints.Sum;
import org.jacop.constraints.SumWeight;
import org.jacop.constraints.XeqC;
import org.jacop.constraints.XlteqY;
import org.jacop.constraints.XplusCeqZ;
import org.jacop.constraints.XplusClteqZ;
import org.jacop.constraints.XplusYlteqZ;
import org.jacop.constraints.XplusYplusCeqZ;
import org.jacop.constraints.XplusYplusQeqZ;
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
import org.jacop.search.WeightedDegree;

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
		int tankSize = 15;
		IntVar maxFuel = new IntVar(store,"maxFuel", tankSize, tankSize);
		
		IntVar fuelLevelsIn[] = new IntVar [10];
		fuelLevelsIn[0] = new IntVar(store, String.format("fuelin_%d", 1), 0, 0);
		for(int i = 1 ; i < 10; ++i) {
			fuelLevelsIn[i] = new IntVar(store, String.format("fuelin_%d", i+1), 0, tankSize);
		}
		
		IntVar fuelRefill[] = new IntVar [10];
		for(int i = 0 ; i < 10; ++i) {
			fuelRefill[i] = new IntVar(store, String.format("fuel refill in %d", i+1), 0, tankSize);
//			store.impose(new XlteqY(fuelRefill[i], maxFuel));
			store.impose(new XplusYlteqZ(fuelLevelsIn[i], fuelRefill[i], maxFuel));
		}
		
		
		
		
		IntVar node1 = new IntVar(store, "1", 2, 4);
		IntVar node2 = new IntVar(store, "2", 3, 4);
		node2.addDom(1, 1);
		node2.addDom(2, 2);
		IntVar node3 = new IntVar(store, "3", 5, 6);
		node3.addDom(1, 2);
		node3.addDom(3, 3);
		IntVar node4 = new IntVar(store, "4", 5, 7);
		node4.addDom(1, 2);
		node4.addDom(4, 4);
		IntVar node5 = new IntVar(store, "5", 7, 7);
		node5.addDom(3, 4);
		node5.addDom(5, 5);
		IntVar node6 = new IntVar(store, "6", 8, 8);
		node6.addDom(4, 4);
		node6.addDom(6, 6);
		IntVar node7 = new IntVar(store, "7", 8, 9);
		node7.addDom(4, 5);
		node7.addDom(7, 7);
		IntVar node8 = new IntVar(store, "8", 9, 10);
		node8.addDom(6, 7);
		node8.addDom(8, 8);
		IntVar node9 = new IntVar(store, "9", 10, 10);
		node9.addDom(7, 8);
		node9.addDom(9, 9);
		IntVar node10 = new IntVar(store, "10", 1, 1);
		
		IntVar nodes[] = {node1, node2, node3, node4, node5, node6 ,node7, node8, node9, node10};
//		10, 8), new XplusYplusCeqZ(fuelLevelsIn[9],fuelRefill[9], -9, fuelLevelsIn[7])));
		
		int max = Integer.MIN_VALUE;
		//DEfine adj matrix
		int[][] adjacency = {{0, -10, -11, -6, max, max, max, max, max, max},
		                     {-10, 0, -5, max, -8, max, max, max, max, max},
		                     {-11, -5, 0, max, -3, -5, max, max, max, max},
		                     {-6, -8, max, 0, -2, -6, -7, max, max, max},
		                     {max, max, -3, -2, 0, max, -12, max, max, max},
		                     {max, max, -5, -6, max, 0, max, -14, max, max},
		                     {max, max, max, -7, -12, max, 0, -5, -3, max},
		                     {max, max, max, max, max, -14,-5, 0, -1, -9},
		                     {max, max, max, max, max, max, -3, -1, 0, -2},
		                     {0, max, max, max, max, max, max, -9, -2, 0}};
		IntVar[] consumtion = new IntVar[10];
		//Save variables for consumption leaving each node
		for (int i = 0; i < consumtion.length; i++) {
			consumtion[i] = new IntVar(store, "consumtion "+i,-10000, 10000);
		}
		
		
		for (int i = 0; i< adjacency.length; i++) {
			//Set the consumption from that node to the correct value
			Element c =new Element(nodes[i], adjacency[i], consumtion[i]);
			store.impose(c);
//			System.out.println(c);
			for (int j = 0; j < adjacency[i].length; j++) {
				int targetNodeDistance = adjacency[i][j];
				if(targetNodeDistance != Integer.MIN_VALUE){ //If we can leave that node
					//Impose a constraint saying that if we go to the target node (j) fuellevel in j should be fuellevelIn[i]+fuelRefill[i]+consumption[i]
					IfThen it =new IfThen(new XeqC(nodes[i], j+1), new XplusYplusQeqZ(fuelLevelsIn[i],fuelRefill[i], consumtion[i], fuelLevelsIn[j]));
					store.impose(it);
					
//					System.out.println(it);
				}
			}
		}

		IntVar totalTankCost = new IntVar(store,"totalTankCost",0,50000);
		int[] fuelCost = new int[]{10,10,8,12,13,9,10,11,12,8};
		int[] disregardFuelCost = new int[]{1,1,1,1,1,1,1,1,1,1};
		store.impose(new SumWeight(fuelRefill, fuelCost, totalTankCost));
		
		IntVar[] route = { node1, node2, node3, node4, node5, node6, node7, node8, node9, node10};

		IntVar[] vars = { node1, node2, node3, node4, node5, node6, node7, node8, node9, node10,
				fuelRefill[0], fuelRefill[1], fuelRefill[2], fuelRefill[3], fuelRefill[4], fuelRefill[5],
				fuelRefill[6], fuelRefill[7], fuelRefill[8], fuelRefill[9]};
		
		
		store.impose(new Subcircuit(route));

		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(vars, new SmallestDomain<IntVar>(), // input
				// order
				new IndomainMin<IntVar>());

		Search<IntVar> search = new DepthFirstSearch<IntVar>();

		search.setSolutionListener(new PrintOutListener<IntVar>());

		boolean result = search.labeling(store, select, totalTankCost);
		System.out.println(store);

	}
}
