package lab3;

import org.jacop.constraints.Cumulative;
import org.jacop.constraints.Max;
import org.jacop.constraints.XplusCeqZ;
import org.jacop.constraints.XplusClteqZ;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.PrintOutListener;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSelect;
import org.jacop.search.SmallestDomain;

public class TaskScheduling {
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
		IntVar startTime11 = new IntVar(store, "Task 1 - S", 0, 100);
		IntVar startTime12 = new IntVar(store, "Task 4 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime11, 1, startTime12)); //And so on...
		IntVar startTime13 = new IntVar(store, "Task 7 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime12, 3, startTime13));
		IntVar endTime1 = new IntVar(store, "Series 1 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime13, 6, endTime1));
		
		IntVar startTime21 = new IntVar(store, "Task 2 - S", 0, 100);
		IntVar startTime22 = new IntVar(store, "Task 5 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime21, 8, startTime22)); //And so on...
		IntVar startTime23 = new IntVar(store, "Task 8 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime22, 5, startTime23));
		IntVar endTime2 = new IntVar(store, "Series 2 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime23, 4, endTime2));
		
		IntVar startTime31 = new IntVar(store, "Task 3 - S", 0, 100);
		IntVar startTime32 = new IntVar(store, "Task 6 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime31, 5, startTime32)); //And so on...
		IntVar startTime33 = new IntVar(store, "Task 9 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime32, 4, startTime33));
		IntVar endTime3 = new IntVar(store, "Series 3 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime33, 7, endTime3));
		
		store.impose(new XplusClteqZ(startTime32, 1, startTime33);
		
		//Make similar varables for all jobs
		IntVar one = new IntVar(store, 1, 1);
		IntVar[] resources = {one, one, one, one, one, one};
		
		IntVar[] durationsMachine0 = { new IntVar(store, 3, 3), new IntVar(store, 10, 10), new IntVar(store, 9, 9),
				new IntVar(store, 5, 5), new IntVar(store, 3, 3), new IntVar(store, 10, 10) };
		IntVar[] durationsMachine1 = { new IntVar(store, 6, 6), new IntVar(store, 8, 8), new IntVar(store, 1, 1), new IntVar(store, 5, 5),
				new IntVar(store, 3, 3), new IntVar(store, 3, 3) };
		IntVar[] durationsMachine2 = { new IntVar(store, 1, 1), new IntVar(store, 5, 5), new IntVar(store, 5, 5), new IntVar(store, 5, 5),
				new IntVar(store, 9, 9), new IntVar(store, 1, 1) };
		
		Cumulative cum0 = new Cumulative(new IntVar[] {startTime12, startTime25, startTime34,}, durationsMachine0, resources, new IntVar(store,3,3));
		
		
		cum0.impose(store);
		cum1.impose(store);
		cum2.impose(store);
		cum3.impose(store);
		cum4.impose(store);
		cum5.impose(store);
		
		IntVar maxEndTime = new IntVar(store, "maxendtime", 0, 1000);
		

		store.impose(new Max(new IntVar[] {endTime1, endTime2, endTime3, endTime4, endTime5, endTime6},maxEndTime));
		
		IntVar[] startTimes = {	startTime11, startTime12, startTime13, startTime14, startTime15, startTime16,
								startTime21, startTime22, startTime23, startTime24, startTime25, startTime26,
								startTime31, startTime32, startTime33, startTime34, startTime35, startTime36,
								startTime41, startTime42, startTime43, startTime44, startTime45, startTime46,
								startTime51, startTime52, startTime53, startTime54, startTime55, startTime56,
								startTime61, startTime62, startTime63, startTime64, startTime65, startTime66};
		
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(startTimes, new SmallestDomain<IntVar>(), // input
				// order
		new IndomainMin<IntVar>());
		
		Search<IntVar> search = new DepthFirstSearch<IntVar>();

		search.setSolutionListener(new PrintOutListener<IntVar>());
		
		boolean result = search.labeling(store, select, maxEndTime);
		
		
		
	}
}
