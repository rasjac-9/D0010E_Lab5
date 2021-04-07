
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

	/**
	 * Constructor
	 */
	public Optimize() {
	}

	/**
	 * Metod I Runs a simulation
	 * 
	 * @param fro - the seed
	 * @param reg - amount of cash registers
	 * @return - the end state
	 */
	public State aSimRun(int fro, int reg) {
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

	/**
	 * Metod II Finds the least amount of cash registers that gives the least amount
	 * of lost customers
	 * 
	 * @param fro - the seed
	 * @return test_reg, oldLostC
	 */
	public int[] findReg(int fro) {
		int MAX_reg = M;
		int MIN_reg = 1;

		int TEST_reg = getHalf(MAX_reg, MIN_reg);
		int oldLostC = aSimRun(fro, M).getLostCustomer();

		State s;

		while (true) {
			s = aSimRun(fro, TEST_reg);

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

	/**
	 * Metod III Runs findReg() until the highest amount of register haven't change
	 * for 100 turns
	 * 
	 * @param seed - the beginning seed to start the random generator
	 * @return worstReg - highest amount of registers found
	 */
	private int[] findWorstReg(int seed) {
		Random random = new Random(seed);
		int counter = 0;
		int worstReg = 0;
		int eke = 0;

		while (true) {

			int[] twoReg = findReg(random.nextInt());

			if (twoReg[0] > worstReg) {
				counter = 0;
				worstReg = twoReg[0];

			} else {
				counter++;
			}

			if (counter == 100) {
				eke = twoReg[1];
				break;
			}
		}
		return new int[]{worstReg,eke};
	}

	/**
	 * Main method starts program and runs the different methods
	 * 
	 * @param args - 1 = Metod I, 2 = Metod II, 3 = Metod III
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optimize op = new Optimize();

		if (args.length >= 1) {
			switch (args[0]) {
			case "1":
				State s = op.aSimRun(SEED, 2);
				System.out.println("Metod I finished amount of lost customers: " + s.getLostCustomer());
				break;
			case "2":
				int[] x = op.findReg(SEED);
				System.out.println("Metod 2: ");
				System.out.println(
						"Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME + ".");
				System.out.println("Minsta antal kassor som ger minimalt antal missade (" + x[1] + "): " + x[0]);
				break;
			case "3":
				int[] kek = op.findWorstReg(SEED);
				System.out.println(
						"Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME + ".");
				System.out.println("Minsta antal kassor som ger minimalt antal missade (" + kek[1] + "): " + kek[0]);
				break;
			default:
				System.out.println("Sorry your input did not respond to a method please try igen.");
				break;
			}

		} else {
			int[] x = op.findReg(SEED);

			System.out.println("Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME + ".");
			System.out.println("Minsta antal kassor som ger minimalt antal missade (" + x[1] + "): " + x[0]);
		}
	}
}
