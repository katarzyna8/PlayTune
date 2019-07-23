package com.example.katarzyna.playtune.View;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katarzyna.playtune.Adapters.ImageAdapter;
import com.example.katarzyna.playtune.Adapters.MyAdapter;
import com.example.katarzyna.playtune.Adapters.MyAdapterCart;
import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.R;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {

    public DiscViewModel discViewModel;
    private static final String ARG_PARAM = "id";
    private int id;
    public DiscModel current;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(int id) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, id);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM);
        }
        discViewModel = ViewModelProviders.of(this).get(DiscViewModel.class);
        current = discViewModel.getDisc(id);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment_layout, container, false);
        Context context = view.getContext();
        FloatingActionButton floatingActionButton = ((NavigationDrawerActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }

        TextView textView = view.findViewById(R.id.detailsName);
        TextView textView1 = view.findViewById(R.id.detailsAuthor);
        TextView textView2 = view.findViewById(R.id.detailsYear);
        TextView textView3 = view.findViewById(R.id.opis);
        Button button = view.findViewById(R.id.cartBut);
        Button button1 = view.findViewById(R.id.favoritesBut);
        ViewPager viewPager = view.findViewById(R.id.viewpagerForDetails);

        textView.setText(current.getName());
        textView1.setText(current.getAuthor());
        textView2.setText(Long.toString(current.getYear()));
        textView3.setText(current.getDescription());
        button.setText(Double.toString(current.getPrice()) + " ZŁ");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapterCart.getCart().add(current);
                current.setInCart(true);
                discViewModel.updateDB(current);
                Toast.makeText(getActivity(), "Dodano do koszyka", Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter.getDiscsToFavorites().add(current);
                current.setInFavorites(true);
                discViewModel.updateDB(current);
                Toast.makeText(getActivity(), "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<Integer> imageId = new ArrayList<>();
        if (current.getMainImage() != null) {
            imageId.add(context.getResources().getIdentifier(current.getMainImage(), "drawable", context.getPackageName()));
        }
        if (current.getSecondImage() != null) {
            imageId.add(context.getResources().getIdentifier(current.getSecondImage(), "drawable", context.getPackageName()));
        }
        if (current.getThirdImage() != null) {
            imageId.add(context.getResources().getIdentifier(current.getThirdImage(), "drawable", context.getPackageName()));
        }
        ImageAdapter imageAdapter = new ImageAdapter(context, imageId);
        viewPager.setAdapter(imageAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
