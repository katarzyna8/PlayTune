package com.example.katarzyna.playtune.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.katarzyna.playtune.R;

public class FilteringFragment extends Fragment {
    FragmentManager fragmentManager;
    Fragment fragment = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.card_layout_for_filtering, container, false);

        final RadioButton checkBox = view.findViewById(R.id.checkbox);
        final RadioButton checkBox1 = view.findViewById(R.id.checkbox1);
        final RadioButton checkBox2 = view.findViewById(R.id.checkbox2);
        final RadioButton checkBox3 = view.findViewById(R.id.checkbox3);
        final RadioButton checkBox4 = view.findViewById(R.id.checkbox4);
        final RadioButton ameryka = view.findViewById(R.id.radioButton);
        final RadioButton anglia = view.findViewById(R.id.radioButton2);
        final RadioButton polska = view.findViewById(R.id.radioButton3);
        final RadioButton australia = view.findViewById(R.id.radioButton4);


        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lata
                if (checkBox.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1960, 1969, "lata 60", null);
                }
                if (checkBox1.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1970, 1979, "lata 70", null);
                }
                if (checkBox2.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1980, 1989, "lata 80", null);
                }
                if (checkBox3.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1990, 1999, "lata 90", null);
                }
                if (checkBox4.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(2000, 2019, "lata współczesne", null);
                }
                //ameryka
                if (ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(0, 0, "wszystkie lata", "Ameryka");
                }
                //wspólne filtry dla Ameryki
                if (checkBox.isChecked() && ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1960, 1969, "lata 60", "Ameryka");
                }
                if (checkBox1.isChecked() && ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1970, 1979, "lata 70", "Ameryka");
                }
                if (checkBox2.isChecked() && ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1980, 1989, "lata 80", "Ameryka");
                }
                if (checkBox3.isChecked() && ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1990, 1999, "lata 90", "Ameryka");
                }
                if (checkBox4.isChecked() && ameryka.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(2000, 2019, "lata współczesne", "Ameryka");
                }
                //anglia
                if (anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(0, 0, "wszystkie lata", "Anglia");
                }
                //wspólne filtry dla Anglii
                if (checkBox.isChecked() && anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1960, 1969, "lata 60", "Anglia");
                }
                if (checkBox1.isChecked() && anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1970, 1979, "lata 70", "Anglia");
                }
                if (checkBox2.isChecked() && anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1980, 1989, "lata 80", "Anglia");
                }
                if (checkBox3.isChecked() && anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1990, 1999, "lata 90", "Anglia");
                }
                if (checkBox4.isChecked() && anglia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(2000, 2019, "lata współczesne", "Anglia");
                }
                //polska
                if (polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(0, 0, "wszystkie lata", "Polska");
                }
                //wspólne filtry dla polski
                if (checkBox.isChecked() && polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1960, 1969, "lata 60", "Polska");
                }
                if (checkBox1.isChecked() && polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1970, 1979, "lata 70", "Polska");
                }
                if (checkBox2.isChecked() && polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1980, 1989, "lata 80", "Polska");
                }
                if (checkBox3.isChecked() && polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1990, 1999, "lata 90", "Polska");
                }
                if (checkBox4.isChecked() && polska.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(2000, 2019, "lata współczesne", "Polska");
                }
                //Australia
                if (australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(0, 0, "wszystkie lata", "Australia");
                }
                //wspólne filtry dla australia
                if (checkBox.isChecked() && australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1960, 1969, "lata 60", "Australia");
                }
                if (checkBox1.isChecked() && australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1970, 1979, "lata 70", "Australia");
                }
                if (checkBox2.isChecked() && australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1980, 1989, "lata 80", "Australia");
                }
                if (checkBox3.isChecked() && australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(1990, 1999, "lata 90", "Australia");
                }
                if (checkBox4.isChecked() && australia.isChecked()) {
                    fragment = ListOfDiscsFragmentByYears.newInstance(2000, 2019, "lata współczesne", "Australia");
                }

                if (fragment != null) {
                    ((NavigationDrawerActivity) view.getContext()).stackFragment(fragment);
                    fragmentManager = getChildFragmentManager();
                    fragmentManager.beginTransaction().detach(FilteringFragment.this).commit();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
