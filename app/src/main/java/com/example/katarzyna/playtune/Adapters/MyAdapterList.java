package com.example.katarzyna.playtune.Adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.example.katarzyna.playtune.View.DetailsFragment;
import com.example.katarzyna.playtune.View.ListOfDiscsFragmentByGenre;
import com.example.katarzyna.playtune.View.NavigationDrawerActivity;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class MyAdapterList extends RecyclerView.Adapter<MyAdapterList.ViewHolder2> {

    private final LayoutInflater layoutInflater;
    private List<DiscModel> list;
    public  static List<DiscModel> discsToCart = new ArrayList<>();
    public static List<DiscModel> discsToFavorites = new ArrayList<>();
    Context context;
    DiscViewModel discViewModel;

    public MyAdapterList(Context context){
        layoutInflater = LayoutInflater.from(context);
        this.context=context;
        discViewModel = ViewModelProviders.of((FragmentActivity) context).get(DiscViewModel.class);
    }


    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View itemView;
        itemView = layoutInflater.inflate(ListOfDiscsFragmentByGenre.isGrid ? R.layout.card_layout_grid : R.layout.card_layout_of_disc, viewGroup,false);
        return new ViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder2 viewHolder2,  final int i) {
        final DiscModel current = list.get(i);


        if(list != null ){
            viewHolder2.textView.setText(current.getName());
            viewHolder2.textView2.setText(current.getAuthor());
            viewHolder2.textView3.setText(String.valueOf(current.getPrice()) + " zł");
            viewHolder2.imageView.setImageResource(context.getResources().getIdentifier(current.getMainImage(), "drawable", context.getPackageName()));

            if(current.isInCart()){
                viewHolder2.imageViewCart.setImageResource(R.drawable.dodaj_koszyka);
            }
            if(current.isInFavorites()){
                viewHolder2.imageViewFav.setImageResource(R.drawable.ulubione);
            }
        }else
        {
            viewHolder2.textView.setText("brak danych");
            viewHolder2.textView2.setText("brak danych");
            viewHolder2.textView3.setText("brak danych");
        }
        viewHolder2.imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setInCart(true);
                discViewModel.updateDB(current);
                discsToCart.add(list.get(i));
                Toast.makeText(context,"Dodano do koszyka",Toast.LENGTH_SHORT).show();
                viewHolder2.imageViewCart.setImageResource(R.drawable.dodaj_koszyka);
            }
        });
        viewHolder2.imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setInFavorites(true);
                discViewModel.updateDB(current);
                discsToFavorites.add(list.get(i));
                Toast.makeText(context,"Dodano do ulubionych",Toast.LENGTH_SHORT).show();
                viewHolder2.imageViewFav.setImageResource(R.drawable.ulubione);
            }
        });
        viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= DetailsFragment.newInstance(current.getId());
                ((NavigationDrawerActivity)v.getContext()).stackFragment(fragment);
            }
        });
    }

    public  void setDiscs(List<DiscModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
         else
        return 0;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder  {

        TextView textView, textView2, textView3;
        public View itemView;
        ImageView imageView, imageViewCart, imageViewFav, removeImg;

        public ViewHolder2( View itemView) {
            super(itemView);
            this.itemView=itemView;
            textView =  itemView.findViewById(R.id.textView);
            textView2 =  itemView.findViewById(R.id.textView2);
            textView3 =  itemView.findViewById(R.id.textView3);
            imageView =  itemView.findViewById(R.id.imageView);
            imageViewCart= itemView.findViewById(R.id.imageView2shop);
            imageViewFav=itemView.findViewById(R.id.imageUlubione);
            removeImg =itemView.findViewById(R.id.removeDisc);
        }


    }
}
