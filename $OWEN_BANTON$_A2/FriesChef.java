// Name: Owen Banton

package javaProjects.$OWEN_BANTON$_A2;

// You are free to add any attributes or methods you need.
public class FriesChef implements Runnable {
    private String name = "FriesChef";
    private int WAIT_TIME = 1000;
    private int MAKE_TIME = 2000;

    @Override
    public void run() {
        while (true) {      // runs indefinitely.
            if (KitchenTable.currentFries < KitchenTable.FryLimit) {    // Call function to make fries if below limit.
                try {
                    makeFries();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if ((KitchenTable.currentBurgers > 0) && (KitchenTable.currentFries > 0) && (ReadyTable.currentCombos < 3)) {
                try {                      // Call function to make a meal if below limit and both components are present.
                    makeCombo();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            if ((KitchenTable.currentFries == KitchenTable.FryLimit) && (ReadyTable.currentCombos == ReadyTable.limit)) {
                try {                       // Wait if both number of fries and combos are at limit.
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    // Function to have the fries chef make a fry and add it to the kitchen table, then print the info and wait.
    public void makeFries() throws InterruptedException {
        Thread.sleep(MAKE_TIME);
        KitchenTable.currentFries++;
        System.out.println("[Action] " + name + " added an order of fries to the kitchen table");
        System.out.println("[Status] Fries left: " + KitchenTable.currentFries);
        Thread.sleep(WAIT_TIME);    // Appropriate wait times added and information displayed.
    }

    // Function to have the fry chef make a combo and add it to the ready table, removing a burger and a fry from the kitchen table. Then prints info and wait.
    public void makeCombo() throws InterruptedException {
        KitchenTable.currentBurgers--;
        KitchenTable.currentFries--;
        ReadyTable.currentCombos++;

        Thread.sleep(MAKE_TIME);
        System.out.println("[Action] " + name + " makes a Burger and Fry combo and places it on the ready table.");
        System.out.print("[Status] Burgers left: " + KitchenTable.currentBurgers);
        System.out.print(", Fries left: " + KitchenTable.currentFries);
        System.out.println(", Meals left: " + ReadyTable.currentCombos);
        System.out.println("===================================================================================");
        Thread.sleep(WAIT_TIME);    // Appropriate wait times added and information displayed.

    }

}


