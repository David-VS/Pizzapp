package be.ehb.pizzapp.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class PizzaViewModel extends ViewModel {

    private MutableLiveData<Pizza> sharedPizza;
    private Pizza pizza;

    public MutableLiveData<Pizza> getSharedPizza() {
        if(sharedPizza == null){
            sharedPizza = new MutableLiveData<>();
            createPizza();
        }
        return sharedPizza;
    }

    private void createPizza() {
        pizza = new Pizza();
        pizza.setSize("Small");
        pizza.setToppings(new String[0]);
        sharedPizza.setValue(pizza);
    }

    public void addToppings(String[] toppings){
        pizza.setToppings(toppings);
        sharedPizza.setValue(pizza);
    }

    public void addSize(String size){
        pizza.setSize(size);
        sharedPizza.setValue(pizza);
    }
}
