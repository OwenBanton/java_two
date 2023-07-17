package javaProjects.$OWEN_BANTON$_A3.src.test.java;

import javaProjects.$OWEN_BANTON$_A3.src.main.java.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Test class. Functions of the main method not tested.

public class CoffeeTest {

    private Inventory inventory = new Inventory();
    private Recipe recipe = new Recipe();
    private CoffeeMaker coffeeMaker = new CoffeeMaker();
    private RecipeBook recipeBook = new RecipeBook();

    //Inventory class methods tests

    @Test
    @Before
    public void testInventoryDefaultValues() {

        assertEquals(15, inventory.getChocolate());
        assertNotEquals(14, inventory.getChocolate());
        assertEquals(15, inventory.getMilk());
        assertNotEquals(13, inventory.getMilk());
        assertEquals(15, inventory.getCoffee());
        assertNotEquals(12, inventory.getCoffee());
        assertEquals(15, inventory.getSugar());
        assertNotEquals(11, inventory.getSugar());  // default inventory values as indicated in the constructor.
    }

    @Test
    public void testInventorySetValues() {

        inventory.setChocolate(1);
        assertEquals(1, inventory.getChocolate());
        assertNotEquals(15, inventory.getChocolate());
        inventory.setMilk(2);
        assertEquals(2, inventory.getMilk());
        assertNotEquals(15, inventory.getMilk());
        inventory.setCoffee(3);
        assertEquals(3, inventory.getCoffee());
        assertNotEquals(15, inventory.getCoffee());
        inventory.setSugar(4);
        assertEquals(4, inventory.getSugar());
        assertNotEquals(15, inventory.getSugar());  // inventory values correctly updated.
    }

    @Test
    public void testInventoryAdders() throws InventoryException {

        inventory.addChocolate("1");
        assertEquals(16, inventory.getChocolate());
        assertNotEquals(15, inventory.getChocolate());
        inventory.addMilk("2");
        assertEquals(17, inventory.getMilk());
        assertNotEquals(15, inventory.getMilk());
        inventory.addCoffee("3");
        assertEquals(18, inventory.getCoffee());
        assertNotEquals(15, inventory.getCoffee());
        inventory.addSugar("4");
        assertEquals(19, inventory.getSugar());
        assertNotEquals(15, inventory.getSugar());      // each ingredient displays correct updated value.
    }

    @Test
    public void testEnoughIngredients() throws RecipeException {

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        assertTrue(inventory.enoughIngredients(recipe));    // inventory can accommodate recipe with default values (15 each)

        inventory.setChocolate(1);
        inventory.setMilk(1);
        inventory.setCoffee(1);
        inventory.setSugar(1);

        assertFalse(inventory.enoughIngredients(recipe));   // returns false when inventory is too low.
    }

    @Test
    public void testUseIngredients() throws RecipeException {

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        inventory.useIngredients(recipe);

        assertEquals(10, inventory.getChocolate());
        assertNotEquals(15, inventory.getChocolate());
        assertEquals(10, inventory.getMilk());
        assertNotEquals(15, inventory.getMilk());
        assertEquals(10, inventory.getCoffee());
        assertNotEquals(15, inventory.getCoffee());
        assertEquals(10, inventory.getSugar());
        assertNotEquals(15, inventory.getSugar());          // all ingredients decremented by 5 as indicated by recipe.
    }

    @Test
    public void testInventoryString() {
        assertEquals(("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n"), inventory.toString()); // String of default values

        inventory.setChocolate(1);
        inventory.setMilk(2);
        inventory.setCoffee(3);
        inventory.setSugar(4);

        assertEquals(("Coffee: 3\nMilk: 2\nSugar: 4\nChocolate: 1\n"), inventory.toString()); // Updated value string
    }

    // Recipe class methods tests

    @Test
    public void testRecipeDefaultValues() {     // testing default recipe values of 0.

        assertEquals(0, recipe.getPrice());
        assertNotEquals(14, recipe.getPrice());
        assertEquals("", recipe.getName());
        assertNotEquals("Cookies", recipe.getName());
        assertEquals(0, recipe.getAmtChocolate());
        assertNotEquals(14, recipe.getAmtChocolate());
        assertEquals(0, recipe.getAmtMilk());
        assertNotEquals(13, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtCoffee());
        assertNotEquals(12, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtSugar());
        assertNotEquals(11, recipe.getAmtSugar());

    }

    @Test
    public void testRecipeSetValues() throws RecipeException {  // testing setting each parameter of the recipe.

        recipe.setAmtChocolate("1");
        assertEquals(1, recipe.getAmtChocolate());
        assertNotEquals(0, recipe.getAmtChocolate());
        recipe.setAmtMilk("2");
        assertEquals(2, recipe.getAmtMilk());
        assertNotEquals(0, recipe.getAmtMilk());
        recipe.setAmtCoffee("3");
        assertEquals(3, recipe.getAmtCoffee());
        assertNotEquals(0, recipe.getAmtCoffee());
        recipe.setAmtSugar("4");
        assertEquals(4, recipe.getAmtSugar());
        assertNotEquals(0, recipe.getAmtSugar());
        recipe.setName("Pie");
        assertEquals("Pie", recipe.getName());
        assertNotEquals("", recipe.getName());
        recipe.setPrice("10");
        assertEquals(10, recipe.getPrice());
        assertNotEquals(0, recipe.getPrice());
    }

    @Test(expected = RecipeException.class)
    public void testChocolateNegativeValue() throws RecipeException {
        recipe.setAmtChocolate("-1");
    }

    @Test(expected = RecipeException.class)
    public void testMilkNegativeValue() throws RecipeException {
        recipe.setAmtMilk("-2");
    }

    @Test(expected = RecipeException.class)
    public void testCoffeeNegativeValue() throws RecipeException {
        recipe.setAmtCoffee("-3");
    }

    @Test(expected = RecipeException.class)
    public void testSugarNegativeValue() throws RecipeException {
        recipe.setAmtSugar("-4");
    }

    @Test(expected = RecipeException.class)
    public void testPriceNegativeValue() throws RecipeException {
        recipe.setPrice("-5");
    }

    @Test(expected = RecipeException.class)
    public void testChocolateNotInteger() throws RecipeException {
        recipe.setAmtChocolate("a");
    }

    @Test(expected = RecipeException.class)
    public void testMilkNotInteger() throws RecipeException {
        recipe.setAmtMilk("b");
    }

    @Test(expected = RecipeException.class)
    public void testCoffeeNotInteger() throws RecipeException {
        recipe.setAmtCoffee("c");
    }

    @Test(expected = RecipeException.class)
    public void testSugarNotInteger() throws RecipeException {
        recipe.setAmtSugar("d");

    }

    @Test(expected = RecipeException.class)
    public void testPriceNotInteger() throws RecipeException {
        recipe.setPrice("e");
    }

    @Test
    public void testRecipeString() {

        assertEquals("", recipe.toString());    // Empty string if no name set.
        assertNotEquals("Cake", recipe.toString());

        recipe.setName("Cake");

        assertEquals("Cake", recipe.toString());    // Returns new name.
        assertNotEquals("", recipe.toString());

    }

    @Test
    public void testRecipeHashCode() {
        // TO DO
    }

    @Test
    public void testRecipeEquals() {
        Recipe r1 = new Recipe();
        r1.setName("Cake");

        Recipe r2 = new Recipe();
        r2.setName("Cake");

        assertTrue(r1.equals(r2));  // Two equal objects return true.

        r2.setName("Bagel");

        assertFalse(r1.equals(r2)); // Two different objects return false.
    }

    // CoffeeMaker class methods tests

    @Test
    public void testAddRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe));  // returns true if recipe added.
    }

    @Test
    public void DeleteRecipe() {

        assertEquals(null, coffeeMaker.deleteRecipe(0));    // returns null if no existing recipe.

        coffeeMaker.addRecipe(recipe);
        recipe.setName("Cookies");
        assertEquals("Cookies", coffeeMaker.deleteRecipe(0));   // returns name of deleted recipe.
    }

    @Test
    public void EditRecipe() {

        coffeeMaker.addRecipe(recipe);
        recipe.setName("Muffins");

        assertEquals("Muffins", coffeeMaker.editRecipe(0, recipe)); // Recipe updated to Muffins
    }

    @Test
    public void testAddInventory() throws InventoryException {

        assertEquals(15, inventory.getChocolate());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getSugar());   // Test to check if default values correct.

        coffeeMaker.addInventory("1", "2", "3", "4");

        assertEquals(19, inventory.getChocolate());
        assertEquals(17, inventory.getMilk());
        assertEquals(16, inventory.getCoffee());
        assertEquals(18, inventory.getSugar());   // Test to check if updated values stored.

    }

    @Test
    public void testCheckInventory() {

        assertEquals(inventory.toString(), coffeeMaker.checkInventory());       // Test to check if properly returns inventory.
        assertEquals(("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n"), coffeeMaker.checkInventory());// Test to check if default values display.

        inventory.setChocolate(1);
        inventory.setMilk(2);
        inventory.setCoffee(3);
        inventory.setSugar(4);

        assertEquals(("Coffee: 3\nMilk: 2\nSugar: 4\nChocolate: 1\n"), coffeeMaker.checkInventory());   // Test to check if updated values display.
    }

    @Test
    public void testMakeCoffee() throws RecipeException {

        coffeeMaker.editRecipe(0, recipe);
        recipe.setPrice("5");
        assertEquals(5, coffeeMaker.makeCoffee(0, 10));       // Function doesn't return correct change amount.

        coffeeMaker.editRecipe(0, null);                                         // Test if null recipe returns full amount paid.
        assertEquals(10, coffeeMaker.makeCoffee(0, 10));
    }

    @Test
    public void testGetRecipes() {
        assertEquals(coffeeMaker.getRecipes(), recipeBook.getRecipes());
    }


    // RecipeBook class methods tested during course of testing other methods so weren't re-tested on their own.
}
