// Name: Owen Banton

package javaProjects.$OWEN_BANTON$_A2;

// You are free to add any attributes or methods you need.
public class Waitress implements Runnable{
	private String name = "Waitress";
	private int WAIT_TIME = 1000;
	private int SERVE_TIME = 10000; // 10 second

	@Override
	public void run() {
		while (true){		// Runs indefinitely.
			if (ReadyTable.currentCombos > 0){	// Calls function to serve meal if there are any on the ready table.
				try {
					serve();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			else {
				try {
					Thread.sleep(WAIT_TIME); //	Wait if no meals are on the ready table.
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

		}
		
	}

	// Function to removes meals from the ready table and serve them to customers.
	public void serve() throws InterruptedException {
		System.out.println("[Action] " + name + " took a meal from the ready table.");
		ReadyTable.currentCombos --;
		System.out.println("[Status] Meals left: " + ReadyTable.currentCombos);
		System.out.println("-----------------------------------------------------------------------------------");
		// Waitress takes a meal from the table, action is displayed and number of meals updated.
		Thread.sleep(SERVE_TIME);
		System.out.println("[Action] " + name + " served a meal.");
		System.out.println("[Status] Meals left: " + ReadyTable.currentCombos);
		System.out.println("-----------------------------------------------------------------------------------");
		// Waitress serves meal after appropriate time, action displayed to user.
		Thread.sleep(WAIT_TIME); // Wait time in implemented after the service is complete.
	}

}
