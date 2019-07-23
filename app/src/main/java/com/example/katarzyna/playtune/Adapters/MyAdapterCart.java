package com.example.katarzyna.playtune.Adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.R;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterCart extends RecyclerView.Adapter<MyAdapterCart.ViewHolder2> {

    private final LayoutInflater layoutInflater;
    private List<DiscModel> list;
    public static List<DiscModel> discsToCart = new ArrayList<>();
    public static List<DiscModel> discsToFavorites = new ArrayList<>();
    Context context;
    DiscViewModel discViewModel;

    public MyAdapterCart(Context context) {
        layoutInflater = LayoutInflater.from(context);
        discsToCart = MyAdapter.getCart();
        this.context = context;
        discViewModel = ViewModelProviders.of((FragmentActivity) context).get(DiscViewModel.class);
    }


    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.card_layout_of_disc, viewGroup, false);
        return new MyAdapterCart.ViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder2 viewHolder2, final int i) {
        final DiscModel current = discsToCart.get(i);

        if (current != null) {
            viewHolder2.textView.setText(current.getName());
            viewHolder2.textView2.setText(current.getAuthor());
            viewHolder2.textView3.setText(String.valueOf(current.getPrice()) + " zł");
            viewHolder2.imageView.setImageResource(context.getResources().getIdentifier(current.getMainImage(), "drawable", context.getPackageName()));
            viewHolder2.removeImg.setVisibility(View.VISIBLE);

            if (current.isInFavorites()) {
                viewHolder2.imageView1.setImageResource(R.drawable.ulubione);
            }
        } else {
            if (current.isInFavorites()) {
                viewHolder2.imageView1.setImageResource(R.drawable.ulubione);
                //viewHolder2.removeImg.setVisibility(View.VISIBLE);

            }
            viewHolder2.textView.setText("brak danych");
            viewHolder2.textView2.setText("brak danych");
            viewHolder2.textView3.setText("brak danych");
        }
        viewHolder2.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setInFavorites(true);
                discViewModel.updateDB(current);
                discsToFavorites.add(current);
                Toast.makeText(context, "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
                viewHolder2.imageView1.setImageResource(R.drawable.ulubione);
            }
        });
        viewHolder2.removeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setInCart(false);
                discViewModel.updateDB(current);
                discsToCart.remove(current);
                Toast.makeText(context, "Usunięto z koszyka", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        viewHolder2.imageView2.setImageResource(R.drawable.dodaj_koszyka);
        viewHolder2.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discsToCart.add(current);
                Toast.makeText(context, "Zwiększono ilość", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    public static List<DiscModel> getCart() {
        return discsToCart;
    }

    public static List<DiscModel> getDiscsToFavorites() {
        return discsToFavorites;
    }

    @Override
    public int getItemCount() {
        if (discsToCart != null)
            return discsToCart.size();
        else
            return 0;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        TextView textView, textView2, textView3;
        ImageView imageView, imageView1, imageView2, removeImg;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
            imageView1 = itemView.findViewById(R.id.imageUlubione);
            imageView2 = itemView.findViewById(R.id.imageView2shop);
            removeImg = itemView.findViewById(R.id.removeDisc);
        }
    }
}
