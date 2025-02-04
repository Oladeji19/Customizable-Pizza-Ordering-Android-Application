package com.example.cs213project4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class OrderingPizzasActivity extends AppCompatActivity {

    private Button addToOrder;
    private static ArrayList<Pizza> pizzas = new ArrayList<>();
    private int[] pizzaImages = {R.drawable.margherita, R.drawable.bbq, R.drawable.buffalo, /*... other pizza images ...*/ };
    private RecyclerView recyclerView;
    private PizzaRecViewAdapter pizzaAdapter;
    private TextView subTotal;
    public static final int MAX_SIZE_OF_PIZZA_TYPES = 10; // adjust as needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas_ordering);

        recyclerView = findViewById(R.id.pizzaRecView);
        subTotal = findViewById(R.id.subTotalPizza);
        addToOrder = findViewById(R.id.addToOrderPizza);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addToOrderButtonListener(addToOrder);
        initialize();
    }

    public void refreshOrderTotal() {
        double totalPrice = pizzas.stream().mapToDouble(MenuItem::getItemCost).sum();
        subTotal.setText(MainActivity.formatPrice(totalPrice));
    }

    private void initialize() {
        if (pizzas.isEmpty()) {
            String[] pizzaNames = getResources().getStringArray(R.array.pizzaNames);
            for (int i = 0; i < pizzaNames.length; i++) {
                pizzas.add(new Pizza(pizzaNames[i], pizzaImages[i]));
            }
        }
        pizzaAdapter = new PizzaRecViewAdapter(this, pizzas);
        recyclerView.setAdapter(pizzaAdapter);
    }

    private void addToOrderButtonListener(Button addToOrder) {
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collection<Pizza> zeroItems = pizzas.stream().filter(p -> p.getQuantity() == 0).map(Pizza::new).collect(Collectors.toList());
                if (zeroItems.size() == MAX_SIZE_OF_PIZZA_TYPES) {
                    Toast.makeText(view.getContext(), "No pizza was selected.", Toast.LENGTH_LONG).show();
                    return;
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Add to order");
                alert.setMessage("Confirm adding order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Collection<Pizza> nonZeroItems = pizzas.stream().filter(p -> p.getQuantity() > 0).map(Pizza::new).collect(Collectors.toList());
                        OrderingBasketActivity.addToOrder(nonZeroItems);
                        pizzaAdapter.reset();
                        Toast.makeText(view.getContext(), "Order added.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "Order not added.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }
}
