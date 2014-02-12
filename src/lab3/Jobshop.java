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
		
		IntVar startTime21 = new IntVar(store, "Job 2 - Task 1 - S", 0, 100);
		IntVar startTime22 = new IntVar(store, "Job 2 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime21, 8, startTime22)); //And so on...
		IntVar startTime23 = new IntVar(store, "Job 2 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime22, 5, startTime23));
		IntVar startTime24 = new IntVar(store, "Job 2 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime23, 10, startTime24));
		IntVar startTime25 = new IntVar(store, "Job 2 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime24, 10, startTime25));
		IntVar startTime26 = new IntVar(store, "Job 2 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime25, 10, startTime26));
		IntVar endTime2 = new IntVar(store, "Job 2 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime26, 4, endTime2));
		
		IntVar startTime31 = new IntVar(store, "Job 3 - Task 1 - S", 0, 100);
		IntVar startTime32 = new IntVar(store, "Job 3 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime31, 5, startTime32)); //And so on...
		IntVar startTime33 = new IntVar(store, "Job 3 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime32, 4, startTime33));
		IntVar startTime34 = new IntVar(store, "Job 3 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime33, 8, startTime34));
		IntVar startTime35 = new IntVar(store, "Job 3 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime34, 9, startTime35));
		IntVar startTime36 = new IntVar(store, "Job 3 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime35, 1, startTime36));
		IntVar endTime3 = new IntVar(store, "Job 3 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime36, 7, endTime3));
		
		IntVar startTime41 = new IntVar(store, "Job 4 - Task 1 - S", 0, 100);
		IntVar startTime42 = new IntVar(store, "Job 4 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime41, 5, startTime42)); //And so on...
		IntVar startTime43 = new IntVar(store, "Job 4 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime42, 5, startTime43));
		IntVar startTime44 = new IntVar(store, "Job 4 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime43, 5, startTime44));
		IntVar startTime45 = new IntVar(store, "Job 4 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime44, 3, startTime45));
		IntVar startTime46 = new IntVar(store, "Job 4 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime45, 8, startTime46));
		IntVar endTime4 = new IntVar(store, "Job 4 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime46, 9, endTime4));
		
		IntVar startTime51 = new IntVar(store, "Job 5 - Task 1 - S", 0, 100);
		IntVar startTime52 = new IntVar(store, "Job 5 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime51, 9, startTime52)); //And so on...
		IntVar startTime53 = new IntVar(store, "Job 5 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime52, 3, startTime53));
		IntVar startTime54 = new IntVar(store, "Job 5 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime53, 5, startTime54));
		IntVar startTime55 = new IntVar(store, "Job 5 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime54, 4, startTime55));
		IntVar startTime56 = new IntVar(store, "Job 5 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime55, 3, startTime56));
		IntVar endTime5 = new IntVar(store, "Job 5 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime56, 1, endTime5));
		
		IntVar startTime61 = new IntVar(store, "Job 6 - Task 1 - S", 0, 100);
		IntVar startTime62 = new IntVar(store, "Job 6 - Task 2 - S", 1, 100);
		store.impose(new XplusClteqZ(startTime61, 3, startTime62)); //And so on...
		IntVar startTime63 = new IntVar(store, "Job 6 - Task 3 - S", 4, 100);
		store.impose(new XplusClteqZ(startTime62, 3, startTime63));
		IntVar startTime64 = new IntVar(store, "Job 6 - Task 4 - S", 10, 100);
		store.impose(new XplusClteqZ(startTime63, 9, startTime64));
		IntVar startTime65 = new IntVar(store, "Job 6 - Task 5 - S", 17, 100);
		store.impose(new XplusClteqZ(startTime64, 10, startTime65));
		IntVar startTime66 = new IntVar(store, "Job 6 - Task 6 - S", 20, 100);
		store.impose(new XplusClteqZ(startTime65, 4, startTime66));
		IntVar endTime6 = new IntVar(store, "Job 6 - E", 0, 100);
		store.impose(new XplusCeqZ(startTime66, 1, endTime6));
		
		//Make similar varables for all jobs
		
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
