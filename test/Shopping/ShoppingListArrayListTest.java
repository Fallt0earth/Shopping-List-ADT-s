package Shopping;

import DataStructures.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @version Spring 2019
 * @author Paul Franklin, Kyle Kiefer
 */
public class ShoppingListArrayListTest {

    private ShoppingListArrayList instance;

    /**
     * Initialize instance and entries
     */
    @Before
    public void setupTestCases() {
        instance = new ShoppingListArrayList();
    }

    /**
     * Test of add method, of class ShoppingArray.
     */
    @Test
    public void testAdd() {
        try {
            //create grocery objects to the current instance which is the shopping list
            Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
            Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
            Grocery entry4 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);

            instance.add(entry1);
            //confirm it was added
            assert (instance.contains(entry1));
            instance.add(entry2);
            //confirm is adding at end of list
            assertEquals(1, instance.indexOf(entry2));
            instance.add(null);
            //test combine private
            instance.add(entry4);

        } catch (ElementNotFoundException ex) {
            System.out.println("Test add is not valid, index of threw an error");
            fail();
        }

    }

    /**
     * Test of remove method, of class ShoppingArrayList.
     */
    @Test
    public void testRemove() {

        //create and add grocery objects to the current instance which is the shopping list
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
        Grocery entry3 = new Grocery("Mango", "Fruit", 2, 4.99f, 1);
        instance.add(entry1);
        instance.add(entry2);

        //does contain
        assertEquals(true, instance.remove(entry2));
        assertEquals(false, instance.remove(entry3));
        assertEquals(true, instance.remove(entry1));

        assertEquals(false, instance.remove(null));

    }

    /**
     * Test of find method, of class ShoppingArrayList.
     */
    @Test
    public void testFind() throws IndexOutOfBoundsException {
        try {
            //create grocery objects to the current instance which is the shopping list
            Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
            Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
            Grocery entry3 = new Grocery("Mango", "Fruit", 2, 4.99f, 1);
            Grocery entry4 = new Grocery("steak", "Meat", 2, 40.99f, 1);

            //ensure that trying to access an empty collection properly throws exception
            boolean exceptionCaught = false;
            try {
                instance.find(0);

            } catch (EmptyCollectionException ex) {
                exceptionCaught = true;
            }
            assertTrue(exceptionCaught);

            //now add for normal testing
            instance.add(entry1);
            instance.add(entry2);
            instance.add(entry3);

            //ensure out of bounds throws exception
            exceptionCaught = false;
            try {
                instance.find(-1);
            } catch (IndexOutOfBoundsException ex) {
                exceptionCaught = true;
            }

            assertTrue(exceptionCaught);

            //try and find grocery entities 
            assertEquals(entry1, instance.find(0));
            assertEquals(entry2, instance.find(1));
            assertEquals(entry3, instance.find(2));

            //enure that it find gets the correct object
            assertNotSame(entry4, instance.find(2));

        } catch (IndexOutOfBoundsException | EmptyCollectionException ex) {
            //if these are exectued something has gone wrong
            assert (false);

        }

    }

    /**
     * Test of indexOf method, of class ShoppingArrayList.
     *
     * @throws DataStructures.ElementNotFoundException
     */
    @Test
    public void testIndexOf() throws ElementNotFoundException {

        //create and add grocery objects to the current instance which is the shopping list
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
        Grocery entry3 = new Grocery("Mango", "Fruit", 2, 4.99f, 1);
        instance.add(entry1);
        instance.add(entry2);

        try {
            //does contain tests if it returns correct index of where object should be
            assertEquals(1, instance.indexOf(entry2));
        } catch (ElementNotFoundException ex) {
            assert (false);

        }
        boolean exceptionCaught = false;
        try {
            //does not contain and should trhow element not found exception

            instance.indexOf(entry3);
        } catch (ElementNotFoundException ex) {
            exceptionCaught = true;

        }
        assertTrue(exceptionCaught);

        exceptionCaught = false;
        //confirmation that exception is still thrown if value passed is null
        try {
            instance.indexOf(null);
        } catch (ElementNotFoundException ex) {
            exceptionCaught = true;
        }
        
        assertTrue(exceptionCaught);

    }

    /**
     * Test of contains method, of class ShoppingArrayList.
     */
    @Test
    public void testContains() {
         //create and add grocery objects to the current instance which is the shopping list
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
        Grocery entry3 = new Grocery("Mango", "Fruit", 2, 4.99f, 1);
        instance.add(entry1);
        instance.add(entry2);
        
        
        //expect true when checking if it contains entry2
        assertEquals(true, instance.contains(entry2));
        //this should be false as entry 3 is not contained
        assertEquals(false, instance.contains(entry3));
        //this should be false and just passes null to preculed weird interactions
        assertEquals(false, instance.contains(null));

    }

    /**
     * Test of size method, of class ShoppingArrayList.
     */
    @Test
    public void testSize() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        assertEquals(0, instance.size());

        instance.add(entry1);

        // Test increment
        assertEquals(1, instance.size());

        assertTrue(instance.remove(entry1));

        // Test decrement
        assertEquals(0, instance.size());
    }

    /**
     * Test of isEmpty method, of class ShoppingArrayList.
     */
    @Test
    public void testIsEmpty() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        // Test empty
        assertTrue(instance.isEmpty());

        instance.add(entry1);

        // Test not empty
        assertFalse(instance.isEmpty());
    }

    /**
     * test of toString at the grocery class level, webcat yells at me if I take this out
     */
    @Test
    public void testToStringForGroceryClass() {
        //create grocery
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        //confirm output is the same
        assertEquals("Entry{" + "name=" + "Mayo" + ", category=" + "Dressing / Mayo" + ", aisle=" + 1 + ", price=" + "2.99" + ", quantity=" + 1 + "}", entry1.toString());

    }

    /**
     * Test for the to string method in array list
     */
    @Test
    public void testToStringForArrayList() {
        
        
         //create and add grocery objects to the current instance which is the shopping list
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Apple", "Fruit", 2, 4.99f, 1);
        Grocery entry3 = new Grocery("Mango", "Fruit", 2, 4.99f, 1);

        instance.add(entry1);
        instance.add(entry2);
        instance.add(entry3);
        
        
        //toString should produce this

        assertEquals("NAME                     CATEGORY          AISLE     QUANTITY  PRICE     \n"
                + "-------------------------------------------------------------------------\n"
                + "Mayo                     Dressing / Mayo   1         1         2.99      \n"
                + "-------------------------------------------------------------------------\n"
                + "Apple                    Fruit             2         1         4.99      \n"
                + "-------------------------------------------------------------------------\n"
                + "Mango                    Fruit             2         1         4.99      \n"
                + "-------------------------------------------------------------------------\n"
                + "", instance.toString());
    }

}
