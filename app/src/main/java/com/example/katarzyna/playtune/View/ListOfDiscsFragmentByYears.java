package com.example.katarzyna.playtune.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Adapters.MyAdapter;
import com.example.katarzyna.playtune.R;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;

import java.util.List;

public class ListOfDiscsFragmentByYears extends Fragment {
    DiscViewModel discViewModel;
    TextView textView1;
    TextView textView;
    private static final String ARG_PARAM = "from";
    private static final String ARG_PARAM2 = "to";
    private static final String ARG_PARAM3 = "lata";
    private static final String ARG_PARAM4 = "kraj";
    private long from;
    private long to;
    private String lata;
    private String kraj;

    public ListOfDiscsFragmentByYears() {
    }

    public static ListOfDiscsFragmentByYears newInstance(long from, long to, String lata, String kraj) {
        ListOfDiscsFragmentByYears listOfDiscsFragmentByYears = new ListOfDiscsFragmentByYears();
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_PARAM, from);
        bundle.putLong(ARG_PARAM2, to);
        bundle.putString(ARG_PARAM3, lata);
        bundle.putString(ARG_PARAM4, kraj);
        listOfDiscsFragmentByYears.setArguments(bundle);
        return listOfDiscsFragmentByYears;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_layout_for_tags, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);
        textView = view.findViewById(R.id.textViewforTag);
        textView1 = view.findViewById(R.id.textViewforTag2);

        final MyAdapter myAdapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        discViewModel = ViewModelProviders.of(this).get(DiscViewModel.class);

        if (kraj == null) {
            discViewModel.getDiscsByYear(from, to).observe(this, new Observer<List<DiscModel>>() {
                @Override
                public void onChanged(@Nullable List<DiscModel> discModels) {
                    myAdapter.setDiscs(discModels);
                    textView.setText(lata);
                    textView1.setText("cały świat");
                }


            });
        } else if (from == 0 && to == 0) {
            discViewModel.getDiscsByCountry(kraj).observe(this, new Observer<List<DiscModel>>() {
                @Override
                public void onChanged(@Nullable List<DiscModel> list) {
                    myAdapter.setDiscs(list);
                    textView.setText("wszystkie lata");
                    textView1.setText(kraj);
                }
            });
        } else
            discViewModel.getDiscsByYearCountry(from, to, kraj).observe(this, new Observer<List<DiscModel>>() {
                @Override
                public void onChanged(@Nullable List<DiscModel> list) {
                    myAdapter.setDiscs(list);
                    textView.setText(lata);
                    textView1.setText(kraj);

                }
            });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            from = getArguments().getLong(ARG_PARAM);
            to = getArguments().getLong(ARG_PARAM2);
            lata = getArguments().getString(ARG_PARAM3);
            kraj = getArguments().getString(ARG_PARAM4);
        }
    }
}
