package common;

import common.BaseThread;
import common.Monitor;
/**
 * Class common.Philosopher.
 * Outlines main subroutines of our virtual philosopher.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;

	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has started eating.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done eating.
	 */
	public void eat()
	{
		try
		{

			System.out.println("Philosopher: " + getTID() + " has started eating");

			sleep((long)(Math.random() * TIME_TO_WASTE));

			System.out.println("Philosopher: " + getTID() + " has finished eating");

			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("common.Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has started thinking.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done thinking.
	 */
	public void think()
	{
		System.out.println("Philosopher: " + getTID() + " has started thinking");
		this.randomYield();
		try{
			this.sleep((long)Math.round(Math.random()*500));
			//*The thread can sleep from 0 to 5 seconds
		}
		catch(InterruptedException e) {
			System.out.println("Philosopher: " + getTID() + " has been interrupted while thinking");
		}
		this.randomYield();
		 System.out.println("Philosopher: " + getTID() + " is done thinking");

	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has started talking.
	 * - yield
	 * - Say something brilliant at random
	 * - yield
	 * - The print that they are done talking.
	 */
	public void talk()
	{
		System.out.println("Philosopher: " + getTID() + " has started talking");
		this.randomYield();
		try{
			this.saySomething();
			this.sleep(1000);
		}
		catch(InterruptedException e) {
			System.out.println("Philosopher: " + getTID() + " has been interrupted while talking");
		}
		this.randomYield();
		System.out.println("Philosopher: " + getTID() + " is done talking");

	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
		{
			common.DiningPhilosophers.soMonitor.pickUp(getTID());

			eat();

			common.DiningPhilosophers.soMonitor.putDown(getTID());

			think();

			/*
			 * TODO:
			 * A decision is made at random whether this particular
			 * philosopher is about to say something terribly useful.
			 * So generate a random var
			 */
			int talkDecision = (int) Math.round((Math.random()*4));
			if(talkDecision > 2)
			{
				common.DiningPhilosophers.soMonitor.requestTalk();

				talk();

				common.DiningPhilosophers.soMonitor.endTalk();

			}

			this.randomYield();
		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
			"Eh, it's not easy to be a philosopher: eat, think, talk, eat...",
			"You know, true is false and false is true if you think of it",
			"2 + 2 = 5 for extremely large values of 2...",
			"If thee cannot speak, thee must be silent",
			"The present is pregnant with the future, the future can be read in the past; \n the distant is expressed in the approximate. \n " +
					"One could know the beauty of the universe in each soul, if one could unfold all its folds, \n" +
					"which only open perceptibly with time." ,



			"My number is " + getTID() + ""

		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
}

// EOF
