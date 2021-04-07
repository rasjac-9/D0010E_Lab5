
package Lab5.specific;

import Lab5.generic.Event;
import Lab5.generic.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Prints the events to the console
 *
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
@SuppressWarnings("deprecation")
public class SuperMarketConsole extends View {

	/**
	 * prints the configurations
	 * 
	 * @param rc - RunConfig file whit all the config files
	 */
	public SuperMarketConsole(SuperMarket rc) {
		System.out.println("Parametrar\n==========\nAntal kassor, N..........: " + rc.regLim
				+ "\nMax som ryms, M..........: " + rc.customerLimit + "\nAnkomshastighet,  lamda..: " + rc.Lamda
				+ "\nPlocktider, [P_min..Pmax]: [" + rc.pMin + ".." + rc.pMax + "]\nBetaltider, [K_min..Kmax]: ["
				+ rc.kMin + ".." + rc.kMax + "]\nFrö, f...................: " + rc.SEED);

		System.out.println(
				"\nFörlopp\n=======\nTid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
	}

	/**
	 * Prints the event every time a event happens
	 */
	@Override
	public void update(Observable o, Object arg) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		df2.setRoundingMode(RoundingMode.UP);

		Event event = (Event) arg;
		SuperMarket sm = (SuperMarket) event.getEventState();

		BigDecimal currentTime = new BigDecimal(event.getTime()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal regTime = new BigDecimal(sm.emptyRegTime).setScale(2, RoundingMode.HALF_UP);
		BigDecimal queueTime = new BigDecimal(sm.inQueueTime).setScale(2, RoundingMode.HALF_UP);

		// Special print for Start event
		if (event.getName().equals("Start")) {
			System.out.println(currentTime + "\t" + event.getName());
		}
		// Special print for Stop event
		else if (event.getName() == "Stop") {

			// Average time cash register was empty in time
			BigDecimal avgRegTime = new BigDecimal(sm.emptyRegTime / sm.regLim).setScale(2, RoundingMode.HALF_UP);

			// Average time cash register was empty in %
			BigDecimal avgInPerc = new BigDecimal(((sm.emptyRegTime / sm.regLim) / sm.lastEventTime) * 100).setScale(2,
					RoundingMode.HALF_UP);

			// Average time some one queue
			BigDecimal avgQueueTime = new BigDecimal(sm.inQueueTime / sm.cashQueue.queueCounter).setScale(2,
					RoundingMode.HALF_UP);

			System.out.println(currentTime + "\t" + event.getName());

			System.out.println("\nRESULTAT\n========");

			// Total amount of customers that the store have dealt whit
			int allCustomers = sm.getTotalCustomers() + sm.getLostCustomer();

			// Point 1 prints customer line
			System.out.println("\n1)\tAv " + allCustomers + " kunder handlade " + sm.getTotalCustomers() + " medan "
					+ sm.getLostCustomer() + " missades.");

			// Point 2 prints related to cash register time
			System.out.println("\n2)\tTotal tid " + sm.getRegisterLimit() + " kassor varit lediga: " + regTime
					+ " te.\n\tGenomsnittlig ledig kassatid: " + avgRegTime + " te (dvs " + avgInPerc
					+ "% av tiden från öppning tills sita kunden betalat)");

			// Point 3 prints related to how many people that have queued
			System.out.println("\n3)\tTotal tid " + sm.cashQueue.queueCounter + " kunder tvingats köa: " + queueTime
					+ " te.\n\tGenomsnittlig kötid: " + avgQueueTime + " te.");

			// Special variation when the store closes
		} else if (event.getName() == "Stänger") {
			System.out.println(currentTime + "\t" + event.getName() + "       ---\t" + "Ö\t" + sm.getAvailableCashReg()
					+ "\t" + regTime + "\t" + sm.getCustomers() + "\t" + sm.getShopped() + "\t" + sm.getLostCustomer()
					+ "\t" + sm.cashQueue.queueCounter + "\t" + queueTime + "\t" + sm.cashQueue.getSize() + "\t"
					+ sm.cashQueue.getQue());
		}
		// Every print when a event happens
		else {
			// different print "Ö" or "S" depending on open or not
			if (sm.getOpenForBis()) {
				// Special case fore payment ass it have different length
				if (event.getName() == "Betalning") {
					System.out.println(currentTime + "\t" + event.getName() + "\t" + event.getID() + "\tÖ\t"
							+ sm.getAvailableCashReg() + "\t" + regTime + "\t" + sm.getCustomers() + "\t"
							+ sm.getShopped() + "\t" + sm.getLostCustomer() + "\t" + sm.cashQueue.queueCounter + "\t"
							+ queueTime + "\t" + sm.cashQueue.getSize() + "\t" + sm.cashQueue.getQue());
				}
				// Every day events
				else {
					System.out.println(currentTime + "\t" + event.getName() + "\t\t" + event.getID() + "\tÖ\t"
							+ sm.getAvailableCashReg() + "\t" + regTime + "\t" + sm.getCustomers() + "\t"
							+ sm.getShopped() + "\t" + sm.getLostCustomer() + "\t" + sm.cashQueue.queueCounter + "\t"
							+ queueTime + "\t" + sm.cashQueue.getSize() + "\t" + sm.cashQueue.getQue());
				}
			} else {
				// Special case fore payment ass it have different length
				if (event.getName() == "Betalning") {
					System.out.println(currentTime + "\t" + event.getName() + "\t" + event.getID() + "\tS\t"
							+ sm.getAvailableCashReg() + "\t" + regTime + "\t" + sm.getCustomers() + "\t"
							+ sm.getShopped() + "\t" + sm.getLostCustomer() + "\t" + sm.cashQueue.queueCounter + "\t"
							+ queueTime + "\t" + sm.cashQueue.getSize() + "\t" + sm.cashQueue.getQue());
				}
				// Every day events
				else {
					System.out.println(currentTime + "\t" + event.getName() + "\t\t" + event.getID() + "\tS\t"
							+ sm.getAvailableCashReg() + "\t" + regTime + "\t" + sm.getCustomers() + "\t"
							+ sm.getShopped() + "\t" + sm.getLostCustomer() + "\t" + sm.cashQueue.queueCounter + "\t"
							+ queueTime + "\t" + sm.cashQueue.getSize() + "\t" + sm.cashQueue.getQue());
				}
			}
		}
	}
}
