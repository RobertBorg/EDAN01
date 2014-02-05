package lab2;

import org.jacop.constraints.Sum;
import org.jacop.constraints.SumWeight;
import org.jacop.constraints.XgteqC;
import org.jacop.constraints.XplusYgtC;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.PrintOutListener;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleSelect;
import org.jacop.search.SmallestDomain;

public class PopsicleStand {
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
		IntVar mondayWorkersFullTime = new IntVar(store, "mondayF", 0, 30);
		IntVar tuesdayWorkersFullTime = new IntVar(store, "tuesdayF", 0, 30);
		IntVar wednesdayWorkersFullTime = new IntVar(store, "wednesdayF", 0, 30);
		IntVar thursdayWorkersFullTime = new IntVar(store, "thursdayF", 0, 30);
		IntVar fridayWorkersFullTime = new IntVar(store, "fridayF", 0, 30);
		IntVar saturdayWorkersFullTime = new IntVar(store, "saturdayF", 0, 30);
		IntVar sundayWorkersFullTime = new IntVar(store, "sundayF", 0, 30);

		IntVar mondayWorkersPartTime = new IntVar(store, "mondayP", 0, 30);
		IntVar tuesdayWorkersPartTime = new IntVar(store, "tuesdayP", 0, 30);
		IntVar wednesdayWorkersPartTime = new IntVar(store, "wednesdayP", 0, 30);
		IntVar thursdayWorkersPartTime = new IntVar(store, "thursdayP", 0, 30);
		IntVar fridayWorkersPartTime = new IntVar(store, "fridayP", 0, 30);
		IntVar saturdayWorkersPartTime = new IntVar(store, "saturdayP", 0, 30);
		IntVar sundayWorkersPartTime = new IntVar(store, "sundayP", 0, 30);

		IntVar mondayWorkers = new IntVar(store, "monday", 5, 30);
		IntVar tuesdayWorkers = new IntVar(store, "tuesday", 7, 30);
		IntVar wednesdayWorkers = new IntVar(store, "wednesday", 7, 30);
		IntVar thursdayWorkers = new IntVar(store, "thursday", 10, 30);
		IntVar fridayWorkers = new IntVar(store, "friday", 16, 30);
		IntVar saturdayWorkers = new IntVar(store, "saturday", 18, 30);
		IntVar sundayWorkers = new IntVar(store, "sunday", 12, 30);

		// IntVar mondayWorkersFullTimePay = new IntVar(store,
		// "mondayFullTimePay");
		// IntVar tuesdayWorkersFullTimePay = new IntVar(store,
		// "tuesdayFullTimePay");
		// IntVar wednesdayWorkersFullTimePay = new IntVar(store,
		// "wednesdayFullTimePay");
		// IntVar thursdayWorkersFullTimePay = new IntVar(store,
		// "thursdayFullTimePay");
		// IntVar fridayWorkersFullTimePay = new IntVar(store,
		// "fridayFullTimePay");
		// IntVar saturdayWorkersFullTimePay = new IntVar(store,
		// "saturdayFullTimePay");
		// IntVar sundayWorkersFullTimePay = new IntVar(store,
		// "sundayFullTimePay");
		//
		// IntVar mondayWorkersPartTimePay = new IntVar(store,
		// "mondayPartTimePay");
		// IntVar tuesdayWorkersPartTimePay = new IntVar(store,
		// "tuesdayPartTimePay");
		// IntVar wednesdayWorkersPartTimePay = new IntVar(store,
		// "wednesdayPartTimePay");
		// IntVar thursdayWorkersPartTimePay = new IntVar(store,
		// "thursdayPartTimePay");
		// IntVar fridayWorkersPartTimePay = new IntVar(store,
		// "fridayPartTimePay");
		// IntVar saturdayWorkersPartTimePay = new IntVar(store,
		// "saturdayPartTimePay");
		// IntVar sundayWorkersPartTimePay = new IntVar(store,
		// "sundayPartTimePay");

		IntVar mondayWorkersPay = new IntVar(store, "mondayPay", 0, 100000);
		IntVar tuesdayWorkersPay = new IntVar(store, "tuesdayPay", 0, 100000);
		IntVar wednesdayWorkersPay = new IntVar(store, "wednesdayPay", 0, 100000);
		IntVar thursdayWorkersPay = new IntVar(store, "thursdayPay", 0, 100000);
		IntVar fridayWorkersPay = new IntVar(store, "fridayPay", 0, 100000);
		IntVar saturdayWorkersPay = new IntVar(store, "saturdayPay", 0, 100000);
		IntVar sundayWorkersPay = new IntVar(store, "sundayPay", 0, 100000);


		store.impose(new Sum(new IntVar[] { mondayWorkersFullTime, sundayWorkersFullTime, saturdayWorkersFullTime, fridayWorkersFullTime,
				thursdayWorkersFullTime, mondayWorkersPartTime, sundayWorkersPartTime }, mondayWorkers));
		
		store.impose(new Sum(new IntVar[] { tuesdayWorkersFullTime, mondayWorkersFullTime, sundayWorkersFullTime, saturdayWorkersFullTime,
				fridayWorkersFullTime , tuesdayWorkersPartTime, mondayWorkersPartTime}, tuesdayWorkers));
		
		store.impose(new Sum(new IntVar[] { wednesdayWorkersFullTime, tuesdayWorkersFullTime, mondayWorkersFullTime, sundayWorkersFullTime,
				saturdayWorkersFullTime, wednesdayWorkersPartTime, tuesdayWorkersPartTime }, wednesdayWorkers));
		
		store.impose(new Sum(new IntVar[] { thursdayWorkersFullTime, wednesdayWorkersFullTime, tuesdayWorkersFullTime, mondayWorkersFullTime,
				sundayWorkersFullTime, thursdayWorkersPartTime, wednesdayWorkersPartTime }, thursdayWorkers));

		store.impose(new Sum(new IntVar[] { fridayWorkersFullTime, thursdayWorkersFullTime, wednesdayWorkersFullTime, tuesdayWorkersFullTime,
				mondayWorkersFullTime, fridayWorkersPartTime, thursdayWorkersPartTime }, fridayWorkers));

		store.impose(new Sum(new IntVar[] { saturdayWorkersFullTime, fridayWorkersFullTime, thursdayWorkersFullTime, wednesdayWorkersFullTime,
				tuesdayWorkersFullTime, saturdayWorkersPartTime, fridayWorkersPartTime }, saturdayWorkers));
		
		store.impose(new Sum(new IntVar[] { sundayWorkersFullTime, saturdayWorkersFullTime, fridayWorkersFullTime, thursdayWorkersFullTime,
				wednesdayWorkersFullTime, sundayWorkersPartTime, saturdayWorkersPartTime }, sundayWorkers));


		int[] cost = { 500, 300 };
		SumWeight mondayPay = new SumWeight(new IntVar[] { mondayWorkersFullTime, mondayWorkersPartTime }, cost, mondayWorkersPay);
		SumWeight tuesdayPay = new SumWeight(new IntVar[] { tuesdayWorkersFullTime, tuesdayWorkersPartTime }, cost, tuesdayWorkersPay);
		SumWeight wednesdayPay = new SumWeight(new IntVar[] { wednesdayWorkersFullTime, wednesdayWorkersPartTime }, cost, wednesdayWorkersPay);
		SumWeight thursdayPay = new SumWeight(new IntVar[] { thursdayWorkersFullTime, thursdayWorkersPartTime }, cost, thursdayWorkersPay);
		SumWeight fridayPay = new SumWeight(new IntVar[] { fridayWorkersFullTime, fridayWorkersPartTime }, cost, fridayWorkersPay);
		SumWeight saturdayPay = new SumWeight(new IntVar[] { saturdayWorkersFullTime, saturdayWorkersPartTime }, cost, saturdayWorkersPay);
		SumWeight sundayPay = new SumWeight(new IntVar[] { sundayWorkersFullTime, sundayWorkersPartTime }, cost, sundayWorkersPay);

		IntVar weeksumasd = new IntVar(store, "total cost", 0, 100000);
		Sum weekSum = new Sum(new IntVar[] { mondayWorkersPay, tuesdayWorkersPay, wednesdayWorkersPay, thursdayWorkersPay, fridayWorkersPay,
				saturdayWorkersPay, sundayWorkersPay }, weeksumasd);
		weekSum.impose(store);

		mondayPay.impose(store);
		tuesdayPay.impose(store);
		wednesdayPay.impose(store);
		thursdayPay.impose(store);
		fridayPay.impose(store);
		saturdayPay.impose(store);
		sundayPay.impose(store);

		Search<IntVar> search = new DepthFirstSearch<IntVar>();

		search.setSolutionListener(new PrintOutListener<IntVar>());

		// search.getSolutionListener().searchAll(true);

		IntVar[] workers = { mondayWorkersFullTime, tuesdayWorkersFullTime, wednesdayWorkersFullTime, thursdayWorkersFullTime, fridayWorkersFullTime,
				saturdayWorkersFullTime, sundayWorkersFullTime, mondayWorkersPartTime, tuesdayWorkersPartTime, wednesdayWorkersPartTime,
				thursdayWorkersPartTime, fridayWorkersPartTime, saturdayWorkersPartTime, sundayWorkersPartTime };
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(workers, new SmallestDomain<IntVar>(), // input
																					// order
				new IndomainMin<IntVar>());
		boolean result = search.labeling(store, select, weeksumasd);
	}
}
