package lab3;

import org.jacop.constraints.Cumulative;
import org.jacop.constraints.XplusCeqZ;
import org.jacop.constraints.XplusClteqZ;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

public class Jobshop {
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
		IntVar startTime11 = new IntVar(store, "Job 1 - Task 1 - S", 0, 100);
		IntVar startTime12 = new IntVar(store, "Job 1 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime11, 1, startTime12)); //And so on...
		IntVar startTime13 = new IntVar(store, "Job 1 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime12, 3, startTime13));
		IntVar startTime14 = new IntVar(store, "Job 1 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime13, 6, startTime14));
		IntVar startTime15 = new IntVar(store, "Job 1 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime14, 7, startTime15));
		IntVar startTime16 = new IntVar(store, "Job 1 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime15, 3, startTime16));
		IntVar endTime1 = new IntVar(store, "Job 1 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime16, 6, endTime1));
		
		//Make similar varables for all jobs
		
		IntVar startTime61 = new IntVar(store, "Job 6 - Task 1 - S", 0, 100);
		IntVar startTime62 = new IntVar(store, "Job 6 - Task 2 - S", 3, 100);
		IntVar startTime63 = new IntVar(store, "Job 6 - Task 3 - S", 6, 100);
		IntVar startTime64 = new IntVar(store, "Job 6 - Task 4 - S", 17, 100);
		IntVar startTime65 = new IntVar(store, "Job 6 - Task 5 - S", 27, 100);
		IntVar startTime66 = new IntVar(store, "Job 6 - Task 6 - S", 31, 100);
		
		IntVar[] durationsMachine0 = { new IntVar(store, 3, 3), new IntVar(store, 10, 10), new IntVar(store, 9, 9),
				new IntVar(store, 5, 5), new IntVar(store, 3, 3), new IntVar(store, 10, 10) };
		IntVar[] durationsMachine1 = { new IntVar(store, 6, 6), new IntVar(store, 8, 8), new IntVar(store, 1, 1), new IntVar(store, 5, 5),
				new IntVar(store, 3, 3), new IntVar(store, 3, 3) };
		IntVar[] durationsMachine2 = { new IntVar(store, 1, 1), new IntVar(store, 5, 5), new IntVar(store, 5, 5), new IntVar(store, 5, 5),
				new IntVar(store, 9, 9), new IntVar(store, 1, 1) };
		IntVar[] durationsMachine3 = { new IntVar(store, 7, 7), new IntVar(store, 4, 4), new IntVar(store, 4, 4), new IntVar(store, 3, 3),
				new IntVar(store, 1, 1), new IntVar(store, 3, 3) };
		IntVar[] durationsMachine4 = { new IntVar(store, 6, 6), new IntVar(store, 10, 10), new IntVar(store, 7, 7), new IntVar(store, 8, 8),
				new IntVar(store, 5, 5), new IntVar(store, 4, 4) };
		IntVar[] durationsMachine5 = { new IntVar(store, 3, 3), new IntVar(store, 10, 10), new IntVar(store, 8, 8), new IntVar(store, 9, 9),
				new IntVar(store, 4, 4), new IntVar(store, 9, 9) };
		// Cumulative cum = new Cumulative(starts, durations, resources, limit)
		
		
		
	}
}
