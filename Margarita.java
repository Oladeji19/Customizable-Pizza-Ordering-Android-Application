package com.example.cs213project4;

import java.util.ArrayList;

/**
 * Extend Pizza class and Define Meatzza Class.
 * @author Clinton Ezenwosu, Oladeji Fagbewosa
 */

public class Margarita extends Pizza{
    public static final double PRICE = 13.99;
    private final String type = "Margarita";

    /**
     * Margarita default constructor
     */
    public Margarita() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.BASIL_LEAVES);
        toppings.add(Topping.SLICED_TOMATOES);
        toppings.add(Topping.MOZZARELLA_CHEESE);
        toppings.add(Topping.OLIVE_OIL);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL;
    }
    /**
     * Method to return Price of Margarita Pizza
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
     * Margarita pizza String format.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + type + "] " + super.toString();
    }
}



