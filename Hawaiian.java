package com.example.cs213project4;

import java.util.ArrayList;

/**
 * Extend Pizza class and Define BBQ Class.
 * @author Clinton Ezenwosu, Oladeji Fagbewosa
 */

public class Hawaiian extends Pizza{
    public static final double PRICE = 17.99;
    private final String type = "Hawaiian";

    /**
     * Hawaiian default contructor
     */
    public BBQ() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.HAM);
        toppings.add(Topping.PINEAPPLE_CHUNKS);
        toppings.add(Topping.MOZZARELLA_CHEESE);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL;
    }
    /**
     * Method to return Price of Hawaiian Pizza
     * @return double, representing price of pizza
     */
    @Override
    public double price(){
        double finalPrice = PRICE;
        if(this.size == Size.MEDIUM){
            finalPrice+= 2;
        }
        if(this.size == Size.LARGE){
            finalPrice+=4;
        }
        if(this.extraCheese){
            finalPrice+=1;
        }
        if(this.extraSauce){
            finalPrice+=1;
        }
        return finalPrice;
    }
    /**
     * Hawaiian pizza String format.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + type + "] " + super.toString();
    }
}