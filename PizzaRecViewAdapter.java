package com.example.project;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PizzaRecViewAdapter extends RecyclerView.Adapter<PizzaRecViewAdapter.ItemsHolder> {

    private PizzaMenuActivity context;
    private ArrayList<Pizza> pizzas;
    private ArrayList<ItemsHolder> holders = new ArrayList<>();
    public static final int MAX_QUANTITY = 6;

    public PizzaRecViewAdapter(PizzaMenuActivity context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view_pizza, parent, false);
        return new ItemsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, @SuppressLint("RecyclerView") int position) {
        List<Integer> quantityOptions = new ArrayList<>();
        for(int i = 0; i <= MAX_QUANTITY; i++) {
            quantityOptions.add(i);
        }

        holder.tvName.setText(pizzas.get(position).getName());
        holder.tvPrice.setText("$" + String.format("%.2f", pizzas.get(position).price()));
        holder.imItem.setImageResource(pizzas.get(position).getImageResId());
        holder.quantity.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, quantityOptions));

        holder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                int qty = (int) holder.quantity.getSelectedItem();
                pizzas.get(position).setQuantity(qty);
                context.updateOrderTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        holder.quantity.setSelection(pizzas.get(position).getQuantity());
        holders.add(holder);
    }

    public void reset() {
        for (Pizza pizza : pizzas) {
            pizza.setQuantity(0);
        }
        for (ItemsHolder holder : holders) {
            holder.quantity.setSelection(0);
        }
        context.updateOrderTotal();
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPrice;
        private ImageView imItem;
        private Spinner quantity;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPizzaName);
            tvPrice = itemView.findViewById(R.id.tvPizzaPrice);
            quantity = itemView.findViewById(R.id.spinnerPizzaQuantity);
            imItem = itemView.findViewById(R.id.imPizza);
        }
    }
}
