package com.example.project;


import java.util.ArrayList;

/**
 * Extend Pizza class and Define BBQ Class.
 * @author Oladeji Fagbewesa
 */

public class BBQ extends Pizza {
    public static final double PRICE = 14.99;
    private final String type = "BBQ";

    /**
     * BBQ default contructor
     */
    public BBQ() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.GRILLED_CHICKEN);
        toppings.add(Topping.RED_ONIONS);
        toppings.add(Topping.BBQ_SAUCE);
        toppings.add(Topping.MOZZARELLA_CHEESE);
        toppings.add(Topping.CILANTRO);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL;
    }
    /**
     * Method to return Price of BBQ Pizza
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
     * BBQ pizza String format.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + type + "] " + super.toString();
    }
}

