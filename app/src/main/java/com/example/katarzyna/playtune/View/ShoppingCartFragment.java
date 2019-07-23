package com.example.katarzyna.playtune.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Adapters.MyAdapterCart;
import com.example.katarzyna.playtune.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends Fragment {
    public List<DiscModel> discs = new ArrayList<>();
    Button button;
    private Handler mhandler;
    private Runnable runnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_cart_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);
        button = view.findViewById(R.id.button2);

        final MyAdapterCart myAdapterCart = new MyAdapterCart(getActivity());
        discs = MyAdapterCart.getCart();
        recyclerView.setAdapter(myAdapterCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mhandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                double price = 0;
                for (int i = 0; i < discs.size(); i++) {
                    double tmp = discs.get(i).getPrice();
                    price = price + tmp;
                }
                button.setText("  Zapłać  " + (new DecimalFormat("#.##").format(price)) + " zł  ");
                mhandler.postDelayed(this, 500);
            }
        };
        mhandler.postDelayed(runnable, 500);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
