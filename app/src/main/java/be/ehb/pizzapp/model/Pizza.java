package be.ehb.pizzapp.model;

import java.util.Arrays;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class Pizza {

    private String size;
    private String[] toppings;

    public Pizza() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String[] getToppings() {
        return toppings;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", toppings=" + Arrays.toString(toppings) +
                '}';
    }
}
