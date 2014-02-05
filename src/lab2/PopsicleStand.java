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
		IntVar mondayWorkersFullTime = new IntVar(store, "mondayFullTime", 0, 30);
		IntVar tuesdayWorkersFullTime = new IntVar(store, "tuesdayFullTime", 0, 30);
		IntVar wednesdayWorkersFullTime = new IntVar(store, "wednesdayFullTime", 0, 30);
		IntVar thursdayWorkersFullTime = new IntVar(store, "thursdayFullTime", 0, 30);
		IntVar fridayWorkersFullTime = new IntVar(store, "fridayFullTime", 0, 30);
		IntVar saturdayWorkersFullTime = new IntVar(store, "saturdayFullTime", 0, 30);
		IntVar sundayWorkersFullTime = new IntVar(store, "sundayFullTime", 0, 30);

		IntVar mondayWorkersPartTime = new IntVar(store, "mondayPartTime", 0, 30);
		IntVar tuesdayWorkersPartTime = new IntVar(store, "tuesdayPartTime", 0, 30);
		IntVar wednesdayWorkersPartTime = new IntVar(store, "wednesdayPartTime", 0, 30);
		IntVar thursdayWorkersPartTime = new IntVar(store, "thursdayPartTime", 0, 30);
		IntVar fridayWorkersPartTime = new IntVar(store, "fridayPartTime", 0, 30);
		IntVar saturdayWorkersPartTime = new IntVar(store, "saturdayPartTime", 0, 30);
		IntVar sundayWorkersPartTime = new IntVar(store, "sundayPartTime", 0, 30);

		IntVar mondayWorkers = new IntVar(store, "monday",5, 30);
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

		IntVar mondayWorkersPay = new IntVar(store, "mondayPay");
		IntVar tuesdayWorkersPay = new IntVar(store, "tuesdayPay");
		IntVar wednesdayWorkersPay = new IntVar(store, "wednesdayPay");
		IntVar thursdayWorkersPay = new IntVar(store, "thursdayPay");
		IntVar fridayWorkersPay = new IntVar(store, "fridayPay");
		IntVar saturdayWorkersPay = new IntVar(store, "saturdayPay");
		IntVar sundayWorkersPay = new IntVar(store, "sundayPay");


		IntVar fullTimeJobTime = new IntVar(store, 5, 5);
		IntVar partTimeJobTime = new IntVar(store, 2, 2);
		
		IntVar mondayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{mondayWorkersFullTime, sundayWorkersFullTime, saturdayWorkersFullTime, fridayWorkersFullTime, thursdayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar tuesdayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{tuesdayWorkersFullTime, mondayWorkersFullTime, sundayWorkersFullTime, saturdayWorkersFullTime, fridayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar wednesdayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{wednesdayWorkersFullTime, tuesdayWorkersFullTime, mondayWorkersFullTime, sundayWorkersFullTime, saturdayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar thursdayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{thursdayWorkers, wednesdayWorkersFullTime, tuesdayWorkersFullTime, mondayWorkersFullTime, sundayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar fridayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{fridayWorkers, thursdayWorkers, wednesdayWorkersFullTime, tuesdayWorkersFullTime, mondayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar saturdayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{saturdayWorkersFullTime, fridayWorkers, thursdayWorkers, wednesdayWorkersFullTime, tuesdayWorkersFullTime}, mondayWorkersFullTimeSum));
		IntVar sundayWorkersFullTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{sundayWorkersFullTime, saturdayWorkersFullTime, fridayWorkers, thursdayWorkers, wednesdayWorkersFullTime}, mondayWorkersFullTimeSum));


		IntVar mondayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{mondayWorkersPartTime, sundayWorkersPartTime}, mondayWorkersPartTimeSum));
		IntVar tuesdayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{tuesdayWorkersPartTime, mondayWorkersPartTime}, tuesdayWorkersPartTimeSum));
		IntVar wednesdayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{wednesdayWorkersPartTime, tuesdayWorkersPartTime}, wednesdayWorkersPartTimeSum));
		IntVar thursdayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{thursdayWorkersPartTime, wednesdayWorkersPartTime}, thursdayWorkersPartTimeSum));
		IntVar fridayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{fridayWorkersPartTime, thursdayWorkersPartTime}, fridayWorkersPartTimeSum));
		IntVar saturdayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{saturdayWorkersPartTime, fridayWorkersPartTime}, saturdayWorkersPartTimeSum));
		IntVar sundayWorkersPartTimeSum = new IntVar(store, 0, 100000);
		store.impose(new Sum(new IntVar[]{sundayWorkersPartTime, saturdayWorkersPartTime}, sundayWorkersPartTimeSum));
		
		
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
