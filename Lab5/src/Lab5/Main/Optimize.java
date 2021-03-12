
package Lab5.Main;

import java.util.Random;

import Lab5.event.StartEvent;
import Lab5.generic.Event;
import Lab5.generic.EventQueue;
import Lab5.generic.State;

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
		// TODO Auto-generated constructor stub
	}

	// Metod I
	public State Mood(int fro, int reg) {
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
	public int[] GardinStänger(int fro) {
		int MAX_reg = M;
		int MIN_reg = 1;
		
		int TEST_reg = getHalf(MAX_reg, MIN_reg);

		boolean beeanZero = false;
		int oldCust = Integer.MAX_VALUE;
		State PLEACE;


		int i = 0;

		while (true) {
			PLEACE = Mood(fro, TEST_reg);
//			testTvå(PLEACE.getLostCustomer());

			// checks if to many regs used
			if (oldCust == PLEACE.getLostCustomer() || MIN_reg == MAX_reg) {
				if (beeanZero) {
					TEST_reg = MAX_reg;
					oldCust = 0;
				} else {
					TEST_reg = MIN_reg;
				}
				break;
				
			}else if (PLEACE.getLostCustomer() == 0) {
				beeanZero = true;
				MAX_reg = TEST_reg;
				TEST_reg = MIN_reg + getHalf(MAX_reg, MIN_reg);

			} else if (oldCust > PLEACE.getLostCustomer()) {
				oldCust = PLEACE.getLostCustomer();
				MIN_reg = TEST_reg;
				TEST_reg = MIN_reg + getHalf(MAX_reg, MIN_reg);

			} 
			
			
			i++;
			if (i == 100) {
				break;
			}
		}
		return new int[] { TEST_reg, oldCust };
	}

	public int getHalf(int Max, int Min) {
		int diff = Max - Min;
		double test = diff / 2;
		int anser = (int) Math.floor(test);
		return anser;
	}

	// Andres är obetald praktikan på bolaget och det är hans uppgift att optimera
	// butikerna
	public int gustavFrigolin(int seed) {
		Random random = new Random(seed);
		int counter = 0;
		int worstReg = 0;

		while (true) {

			int[] twoReg = GardinStänger(random.nextInt());

			if (twoReg[0] > worstReg) {

				testTre(counter, worstReg);
				counter = 0;
				worstReg = twoReg[0];
			} else {

				counter++;
			}

			if (counter == 100) {
				break;
			}
		}
		testTre(counter, worstReg);
		return worstReg;
	}

//	private void testTvå(int a) {
//		System.out.println("Missed customers: " + a);
//	}

	private void testTre(int a, int b) {
		System.out.println("Counter: " + a + "      " + "Best amount of cash registers:  " + b);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optimize op = new Optimize();

//		Run only Metod I
//		int[] arg = { SEED, 13 };
//		System.out.print(op.Mood(SEED, 2).getLostCustomer());

//		Run only Metod II
		int[] x = op.GardinStänger(SEED);

		System.out.println("Stängning sker tiden " + END_TIME + " och stophändelsen" +
				" sker tiden " + STOP_TIME + ".");

		System.out.println("Minsta antal kassor som ger minimalt antal missade (" +
				x[1] + "): " + x[0]);
		
//		Run onlt Metod III
		//op.gustavFrigolin(SEED);
	}
}
