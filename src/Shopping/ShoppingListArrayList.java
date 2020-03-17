package Shopping;

import DataStructures.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @version Spring 2019
 * @author Paul Franklin, Kyle Kiefer, David Stokes
 */
public class ShoppingListArrayList implements ShoppingListADT {

    private ArrayList<Grocery> shoppingList;

    /**
     * Default constructor of ShoppingArray object.
     */
    public ShoppingListArrayList() {
        this.shoppingList = new ArrayList<>();
    }

    /**
     * Constructor of ShoppingArray object that parses from a file.
     *
     * @param filename the name of the file to parse
     * @throws FileNotFoundException if an error occurs when parsing the file
     */
    public ShoppingListArrayList(String filename) throws FileNotFoundException {
        this.shoppingList = new ArrayList<>();
        scanFile(filename);
    }

    /**
     * Method to add a new entry. Only new entries can be added. Combines
     * quantities if entry already exists.
     *
     * @param entry the entry to be added
     */
    @Override
    public void add(Grocery entry) {

        //if input parameter is null exit
        if (entry == null) {
            return;
        }
        // Check if this item already exists
        if (this.contains(entry)) {
            //Merge the quantity of new entry into existing entry
            combineQuantity(entry);
            return;
        }

        shoppingList.add(entry);
    }

    /**
     * Method to remove an entry.
     *
     * @param ent Grocery to be removed
     * @return true when entry was removed
     */
    @Override
    public boolean remove(Grocery ent) {

        // search in the shoppingList, if find ent in the
        // list remove it, set the value of `found'
        for (Grocery x : shoppingList) {
            if (x.equals(ent)) {
                shoppingList.remove(x);
                return true;
            }

        }

        // Return false if not found        
        return false;
    }

    /**
     * Method to find an entry.
     *
     * @param index to find
     * @return the entry if found
     * @throws Exceptions.EmptyCollectionException
     * @throws DataStructures.EmptyCollectionException
     */
    @Override
    public Grocery find(int index) throws IndexOutOfBoundsException,
            EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("ECE - find");
        }

        // check whether or not the input index number is legal
        // for example, < 0 or falls outside of the size
        if (!(index > -1 && index < shoppingList.size())) {

            throw new IndexOutOfBoundsException("meme");
        }

        return shoppingList.get(index);

    }

    /**
     * Method to locate the index of an entry.
     *
     * @param ent to find the index
     * @return the index of the entry
     * @throws ElementNotFoundException if no entry was found
     */
    @Override
    public int indexOf(Grocery ent) throws ElementNotFoundException {
        //only continue if element is not null
        if (ent != null) {
            //iterate through and check equality then return the index
            for (int i = 0; i < shoppingList.size(); i++) {
                if (shoppingList.get(i).compareTo(ent) == 0) {
                    return i;
                }
            }
        }

        //element does not exist
        throw new ElementNotFoundException("indexOf");
    }

    /**
     * Method to determine whether the object contains an entry.
     *
     * @param ent to find
     * @return true if and only if the entry is found
     */
    @Override
    public boolean contains(Grocery ent) {

        // first check whether or not ent is null
        boolean hasItem = false;

        if (ent == null) {
            return false;
        }
        // go through the shoppingList and try to find the 
        for (Grocery x : shoppingList) {
            if (x.compareTo(ent) == 0) {

                hasItem = true;

            }
        }

        return hasItem;
    }

    /**
     * Gets the size of the collection.
     *
     * @return the size of the collection
     */
    @Override
    public int size() {
        return shoppingList.size();
    }

    /**
     * Gets whether the collection is empty.
     *
     * @return true if and only if the collection is empty
     */
    @Override
    public boolean isEmpty() {
        return shoppingList.isEmpty();
    }

    /**
     * Returns a string representing this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%-25s", "NAME"));
        s.append(String.format("%-18s", "CATEGORY"));
        s.append(String.format("%-10s", "AISLE"));
        s.append(String.format("%-10s", "QUANTITY"));
        s.append(String.format("%-10s", "PRICE"));
        s.append('\n');
        s.append("------------------------------------------------------------"
                + "-------------");
        s.append('\n');
        for (int i = 0; i < shoppingList.size(); i++) {
            s.append(String.format("%-25s", shoppingList.get(i).getName()));
            s.append(String.format("%-18s", shoppingList.get(i).getCategory()));
            s.append(String.format("%-10s", shoppingList.get(i).getAisle()));
            s.append(String.format("%-10s", shoppingList.get(i).getQuantity()));
            s.append(String.format("%-10s", shoppingList.get(i).getPrice()));
            s.append('\n');
            s.append("--------------------------------------------------------"
                    + "-----------------");
            s.append('\n');
        }

        return s.toString();
    }

    /**
     * Add the quantity of a duplicate entry into the existing
     *
     * @param entry duplicate
     */
    void combineQuantity(Grocery entry) {
        try {
            int index = this.indexOf(entry);
            shoppingList.get(index).setQuantity(
                    shoppingList.get(index).getQuantity()
                    + entry.getQuantity());
        } catch (ElementNotFoundException e) {
            System.out.println("combineQuantity - ECE");
        }

    }

    /**
     * Scans the specified file to add items to the collection.
     *
     * @param filename the name of the file to scan
     * @throws FileNotFoundException if the file is not found
     */
    private void scanFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(getClass().getResourceAsStream(filename))
                .useDelimiter("(,|\r\n)");

        while (scanner.hasNext()) {
            Grocery temp = new Grocery(scanner.next(), scanner.next(),
                    Integer.parseInt(scanner.next()),
                    Float.parseFloat(scanner.next()),
                    Integer.parseInt(scanner.next()));

            add(temp);
        }
    }

}
