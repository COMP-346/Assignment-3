/**
 * Class DiningPhilosophers
 * The main starter.
 *
 * @author Ricardo RAJI CHAHINE 40234410
 * @author Mathys Loiselle 40242303
 */

import java.util.Scanner;

public class DiningPhilosophers
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * This default may be overridden from the command line
	 */
	public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;

	/**
	 * Dining "iterations" per philosopher thread
	 * while they are socializing there
	 */
	public static final int DINING_STEPS = 10;

	/**
	 * Our shared monitor for the philosphers to consult
	 */
	public static Monitor soMonitor = null;

	/*
	 * -------
	 * Methods
	 * -------
	 */

	/**
	 * Main system starts up right here
	 */
	public static void main(String[] argv) {
		try {
			int iPhilosophers;

			// Use Scanner to get user input
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the number of philosophers: ");

			// Read and validate the input
			while (true) {
				String input = scanner.nextLine();

				try {
					if (input.isEmpty()) {
						iPhilosophers= DEFAULT_NUMBER_OF_PHILOSOPHERS;
						System.out.println("The default number of Philosophers was used: "+ DEFAULT_NUMBER_OF_PHILOSOPHERS+"\n");
						break;
					}

					int number = Integer.parseInt(input);

					if (number <= 0) {
						System.out.println(input + " is not a positive integer number.");
						System.out.print("Please enter a valid number of philosophers: ");
					} else {
						iPhilosophers = number;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(input + " is not a valid integer.");
					System.out.print("Please enter a valid number of philosophers: ");
				}
			}

			// Close the scanner as it's no longer needed
			scanner.close();

			// Make the monitor aware of how many philosophers there are
			soMonitor = new Monitor(iPhilosophers);

			// Space for all the philosophers
			Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

			// Let 'em sit down
			for (int j = 0; j < iPhilosophers; j++) {
				aoPhilosophers[j] = new Philosopher();
				aoPhilosophers[j].start();
			}

			System.out.println(iPhilosophers + " philosopher(s) came in for dinner.");

			// Main waits for all its children to die...
			for (int j = 0; j < iPhilosophers; j++)
				aoPhilosophers[j].join();

			System.out.println("All philosophers have left. System terminates normally.");
		} catch (InterruptedException e) {
			System.err.println("main():");
			reportException(e);
			System.exit(1);
		}
	}
	// main()

	/**
	 * Outputs exception information to STDERR
	 * @param poException Exception object to dump to STDERR
	 */
	public static void reportException(Exception poException)
	{
		System.err.println("Caught exception : " + poException.getClass().getName());
		System.err.println("Message          : " + poException.getMessage());
		System.err.println("Stack Trace      : ");
		poException.printStackTrace(System.err);
	}
}

// EOF
