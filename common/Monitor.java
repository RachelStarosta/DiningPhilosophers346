package common;

/**
 * Class common.Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	public enum state {THINKING, EATING, TALKING};
	public state [] philAction ;    //The size of the array will depend on the number of philosophers


	public int chopstickTotal;

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{

		// TODO: set appropriate number of chopsticks based on the # of philosophers
		chopstickTotal = piNumberOfPhilosophers;  //FYI the default number is 4 philosophers
		/*
		The self array will contain the current state of the philosopher
		The constructor will give the philosophers the default state of thinking
		 */
		philAction = new state [piNumberOfPhilosophers];

		int x = 0;
		for (state s: philAction){    //checked ->debugged separately
			philAction[x] = state.THINKING;
			x++;
		}
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{


	}

	/**
	 * When a given philosopher's done eating, they put the chopsticks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		// ...
	}

	/**
	 * Only one philosopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		// ...
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
	}
}

// EOF
