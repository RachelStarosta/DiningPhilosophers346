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
	 * In order to prevent starvation it would be good to keep a count of the
	 *  number of times a philosopher has already eaten
	 * ------------
	 */
	public enum state {THINKING, EATING, TALKING, HUNGRY};  //confused on how to implement the talking state
	public state [] PhS ;    //The size of the array will depend on the number of philosophers
			//PhS = Philosopher State array
	public int [] times_eaten;  //To make sure that each philosopher gets a fair turn to eat
	public static boolean SILENCE = true;
	public int N;  //The number of philosophers

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		N = piNumberOfPhilosophers;
		// TODO: set appropriate number of chopsticks based on the # of philosophers

		/*
		The self array will contain the current state of the philosopher
		The constructor will give the philosophers the default state of thinking
		 */
		PhS = new state [piNumberOfPhilosophers];
		times_eaten = new int[piNumberOfPhilosophers];
		int i;
		for(i = 0; i<N; i++){
			times_eaten[i] = 0;

		}

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
		int x = piTID;
		PhS[x] = state.HUNGRY;
		//Check the states of the surrounding philosophers. Checking:
		try {
			while ((PhS[((x + N) - 1) % N] == state.EATING) || (PhS[(x + 1) % N] == state.EATING)) {
				wait();  //if either surrounding philosopher is eating wait
			}
			// in order to prevent starvation compare the # of times each surrounding
			// philosopher has eaten, if they have eaten less wait again and let them eat first
			while (((times_eaten[(x+N-1)%N] < times_eaten[x]) && ((PhS[((x+N) - 1) % N] == state.HUNGRY))) || ((times_eaten[(x+1)%N] < times_eaten[x])&&((PhS[(x+1) % N] == state.HUNGRY)))){
				wait(); // Enter block state until you get notify call from another thread - Thread blocked
				// In putdown, notify
			}
			// both chopsticks are free and this current philosopher can begin eating
			PhS[x] = state.EATING;
			times_eaten[x] += 1;
		}
		catch(InterruptedException e){

		}

	}

	/**
	 * When a given philosopher's done eating, they put the chopsticks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		// Time to let go of the chopsticks and go back to thinking, notifyAll() to signal other
		// philosophers for them to be able to eat
			PhS[piTID] = state.THINKING;
			notifyAll();
	}

	/**
	 * Only one philosopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		//since there is no pTID in the parameters, check all the philosophers
		boolean isSpeaking = false;

		while(!isSpeaking) {
			try {
				if (!SILENCE) {
					wait();
				}
				else{
					SILENCE = false;
					isSpeaking = true;
				}

			} catch (Exception e) {

			}
		}

	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// check to make sure that philosopher is still speaking
		if(!SILENCE){
			SILENCE = true;
		}
		notifyAll();
	}
}

// EOF
