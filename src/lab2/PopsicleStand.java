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
		IntVar mondayWorkersFullTime = new IntVar(store, "mondayFullTime");
		IntVar tuesdayWorkersFullTime = new IntVar(store, "tuesdayFullTime");
		IntVar wednesdayWorkersFullTime = new IntVar(store, "wednesdayFullTime");
		IntVar thursdayWorkersFullTime = new IntVar(store, "thursdayFullTime");
		IntVar fridayWorkersFullTime = new IntVar(store, "fridayFullTime");
		IntVar saturdayWorkersFullTime = new IntVar(store, "saturdayFullTime");
		IntVar sundayWorkersFullTime = new IntVar(store, "sundayFullTime");

		IntVar mondayWorkersPartTime = new IntVar(store, "mondayPartTime");
		IntVar tuesdayWorkersPartTime = new IntVar(store, "tuesdayPartTime");
		IntVar wednesdayWorkersPartTime = new IntVar(store, "wednesdayPartTime");
		IntVar thursdayWorkersPartTime = new IntVar(store, "thursdayPartTime");
		IntVar fridayWorkersPartTime = new IntVar(store, "fridayPartTime");
		IntVar saturdayWorkersPartTime = new IntVar(store, "saturdayPartTime");
		IntVar sundayWorkersPartTime = new IntVar(store, "sundayPartTime");

		IntVar mondayWorkers = new IntVar(store, "monday");
		IntVar tuesdayWorkers = new IntVar(store, "tuesday");
		IntVar wednesdayWorkers = new IntVar(store, "wednesday");
		IntVar thursdayWorkers = new IntVar(store, "thursday");
		IntVar fridayWorkers = new IntVar(store, "friday");
		IntVar saturdayWorkers = new IntVar(store, "saturday");
		IntVar sundayWorkers = new IntVar(store, "sunday");

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

		IntVar mondayWorkersPay = new IntVar(store, "mondayPay");
		IntVar tuesdayWorkersPay = new IntVar(store, "tuesdayPay");
		IntVar wednesdayWorkersPay = new IntVar(store, "wednesdayPay");
		IntVar thursdayWorkersPay = new IntVar(store, "thursdayPay");
		IntVar fridayWorkersPay = new IntVar(store, "fridayPay");
		IntVar saturdayWorkersPay = new IntVar(store, "saturdayPay");
		IntVar sundayWorkersPay = new IntVar(store, "sundayPay");

		IntVar fullTimeJobTime = new IntVar(store, 5, 5);
		IntVar partTimeJobTime = new IntVar(store, 2, 2);

		Sum mondayWorkersSum = new Sum(
				new IntVar[] { mondayWorkersFullTime, mondayWorkersPartTime }, mondayWorkers);
		store.impose(mondayWorkersSum);
		Sum tuesdayWorkersSum = new Sum(new IntVar[] { tuesdayWorkersFullTime,
				tuesdayWorkersPartTime }, tuesdayWorkers);
		store.impose(tuesdayWorkersSum);
		Sum wednesdayWorkersSum = new Sum(new IntVar[] { wednesdayWorkersFullTime,
				wednesdayWorkersPartTime }, wednesdayWorkers);
		store.impose(wednesdayWorkersSum);
		Sum thursdayWorkersSum = new Sum(new IntVar[] { thursdayWorkersFullTime,
				thursdayWorkersPartTime }, thursdayWorkers);
		store.impose(thursdayWorkersSum);
		Sum fridayWorkersSum = new Sum(
				new IntVar[] { fridayWorkersFullTime, fridayWorkersPartTime }, fridayWorkers);
		store.impose(fridayWorkersSum);
		Sum saturdayWorkersSum = new Sum(new IntVar[] { saturdayWorkersFullTime,
				saturdayWorkersPartTime }, saturdayWorkers);
		store.impose(saturdayWorkersSum);
		Sum sundayWorkersSum = new Sum(
				new IntVar[] { sundayWorkersFullTime, sundayWorkersPartTime }, sundayWorkers);
		store.impose(sundayWorkersSum);
		// SumWeight mondayPay = new SumWeight(new IntVar[] {
		// mondayWorkersFullTime,
		// mondayWorkersPartTime }, new int[] { 100, 150 }, mondayWorkersPay);
		// SumWeight tuesdayPay = new SumWeight(new IntVar[] {
		// tuesdayWorkersFullTime,
		// tuesdayWorkersPartTime }, new int[] { 100, 150 }, tuesdayWorkersPay);
		// SumWeight wednesdayPay = new SumWeight(new IntVar[] {
		// wednesdayWorkersFullTime,
		// wednesdayWorkersPartTime }, new int[] { 100, 150 },
		// wednesdayWorkersPay);
		// SumWeight thursdayPay = new SumWeight(new IntVar[] {
		// thursdayWorkersFullTime,
		// thursdayWorkersPartTime }, new int[] { 100, 150 },
		// thursdayWorkersPay);
		// SumWeight fridayPay = new SumWeight(new IntVar[] {
		// fridayWorkersFullTime,
		// fridayWorkersPartTime }, new int[] { 100, 150 }, fridayWorkersPay);
		// SumWeight saturdayPay = new SumWeight(new IntVar[] {
		// saturdayWorkersFullTime,
		// saturdayWorkersPartTime }, new int[] { 100, 150 },
		// saturdayWorkersPay);
		// SumWeight sundayPay = new SumWeight(new IntVar[] {
		// sundayWorkersFullTime,
		// sundayWorkersPartTime }, new int[] { 100, 150 }, sundayWorkersPay);

		store.impose(new XgteqC(mondayWorkers, 5));
		store.impose(new XgteqC(tuesdayWorkers, 7));
		store.impose(new XgteqC(wednesdayWorkers, 7));
		store.impose(new XgteqC(thursdayWorkers, 10));
		store.impose(new XgteqC(fridayWorkers, 16));
		store.impose(new XgteqC(saturdayWorkers, 18));
		store.impose(new XgteqC(sundayWorkers, 12));

		Search<IntVar> search = new DepthFirstSearch<IntVar>();

		search.setSolutionListener(new PrintOutListener<IntVar>());

		search.getSolutionListener().searchAll(true);

		IntVar[] workers = { mondayWorkersFullTime, tuesdayWorkersFullTime,
				wednesdayWorkersFullTime, thursdayWorkersFullTime, fridayWorkersFullTime,
				saturdayWorkersFullTime, sundayWorkersFullTime, mondayWorkersPartTime,
				tuesdayWorkersPartTime, wednesdayWorkersPartTime, thursdayWorkersPartTime,
				fridayWorkersPartTime, saturdayWorkersPartTime, sundayWorkersPartTime };
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(workers, null, // input
																					// order
				new IndomainMin<IntVar>());
		boolean result = search.labeling(store, select);
	}
}
