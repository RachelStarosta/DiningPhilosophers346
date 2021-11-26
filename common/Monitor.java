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
	 * In order to prevent starvation it would be good to keep a count of the number of times a philosopher has
	 * already eaten
	 * ------------
	 */
	public enum state {THINKING, EATING, TALKING, HUNGRY};   //hungry
	public state [] PhS ;    //The size of the array will depend on the number of philosophers

				//PhS = Philosopher State array


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{

		// TODO: set appropriate number of chopsticks based on the # of philosophers

		/*
		The self array will contain the current state of the philosopher
		The constructor will give the philosophers the default state of thinking
		 */
		PhS = new state [piNumberOfPhilosophers];

		int x = 0;
		for (state s: PhS){    //checked
			PhS[x] = state.THINKING;
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
		//check left and right philosopher to see if the chopsticks are available
		PhS[piTID] = state.HUNGRY;
		//Check the states of the surrounding philosophers. Checking:






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
