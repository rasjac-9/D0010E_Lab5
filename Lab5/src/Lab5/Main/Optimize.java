
package Lab5.Main;

import java.util.Random;

import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.generic.EventQueue;
import Lab5.event.StartEvent;

/**
 * The method runs a simulation and stores the output values of the simulation
 * in an array
 *
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class Optimize implements K {

	protected EventQueue eq;
	protected Event e;

	public Optimize() {
	}

	// Metod I
	private State aSimRun(int fro, int reg) {
		State s;
		eq = new EventQueue();

		// TODO: Change boolean from true to false to stop prints
		e = new StartEvent(fro, reg, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
				HIGH_PAYMENT_TIME, false, END_TIME, STOP_TIME, eq);

		s = e.getEventState();
		eq.addEvent(e);

		while (s.getRunSim()) {
			if (!eq.isEmpty()) {

				eq.getFirstEvent().effect();
				eq.removeFirstEvent();
			}
		}

		return s;
	}

	// Metod II
	private int[] findReg(int fro) {
		int MAX_reg = M;
		int MIN_reg = 1;

		int TEST_reg = getHalf(MAX_reg, MIN_reg);
		int oldLostC = aSimRun(fro, M).getLostCustomer();

		State s;

		while (true) {
			s = aSimRun(fro, TEST_reg);
//			testTvå(s.getLostCustomer());

			if (MIN_reg + 1 == MAX_reg) {
				TEST_reg = MAX_reg;
				break;

			} else if (s.getLostCustomer() == oldLostC) {
				MAX_reg = TEST_reg;
				TEST_reg = MIN_reg + getHalf(MAX_reg, MIN_reg);

			} else if (s.getLostCustomer() > oldLostC) {
				MIN_reg = TEST_reg;
				TEST_reg = MIN_reg + getHalf(MAX_reg, MIN_reg);
			}
		}
		return new int[] { TEST_reg, oldLostC };
	}

	private int getHalf(int Max, int Min) {
		int diff = Max - Min;
		double test = diff / 2;
		int anser = (int) Math.floor(test);
		return anser;
	}

	// Andres är obetald praktikan på bolaget och det är hans uppgift att optimera
	// butikerna
	private int findWorstReg(int seed) {
		Random random = new Random(seed);
		int counter = 0;
		int worstReg = 0;

		while (true) {

			int[] twoReg = findReg(random.nextInt());

			if (twoReg[0] > worstReg) {

//				testTre(counter, worstReg);
				counter = 0;
				worstReg = twoReg[0];
			} else {

				counter++;
			}

			if (counter == 100) {
				break;
			}
		}
//		testTre(counter, worstReg);
		return worstReg;
	}

//	private void testTvå(int a) {
//		System.out.println("Missed customers: " + a);
//	}

//	private void testTre(int a, int b) {
//		System.out.println("Counter: " + a + "      " + "Best amount of cash registers:  " + b);
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optimize op = new Optimize();

//		Run only Metod I
//		int[] arg = { SEED, 13 };
//		System.out.print(op.Mood(SEED, 2).getLostCustomer());

//		Run only Metod II
		int[] x = op.findReg(SEED);

		System.out.println("Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME + ".");

		System.out.println("Minsta antal kassor som ger minimalt antal missade (" + x[1] + "): " + x[0]);

//		Run onlt Metod III
		// op.gustavFrigolin(SEED);
	}
}
