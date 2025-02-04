package com.example.project;
/**
 * Define Topping Enum Class
 * @author Oladeji Fagbewesa
 */
public enum Topping {
    SAUSAGE, PEPPERONI, GREEN_PEPPERS, ONION, MUSHROOM, BLACK_OLIVE,
    BEEF, HAM, SHRIMP, SQUID, CRAB_MEATS, PINEAPPLE, PICKLES;

    /**
     * Get list of all toppings in enum class
     * @return array of Topping enum objects
     */
    public Topping[] getList(){
        return new Topping[]{SAUSAGE, PEPPERONI, GREEN_PEPPERS, ONION, MUSHROOM, BLACK_OLIVE,
            BEEF, HAM, SHRIMP, SQUID, CRAB_MEATS, PINEAPPLE, PICKLES};
    }

}
