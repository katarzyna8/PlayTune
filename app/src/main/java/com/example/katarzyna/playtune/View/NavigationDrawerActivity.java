package com.example.katarzyna.playtune.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Adapters.MyAdapter;
import com.example.katarzyna.playtune.R;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    MaterialSearchView materialSearchView;
    String[] list;
    DiscViewModel discViewModel;
    RecyclerView recyclerView;
    Fragment fragment = null;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialSearchView = findViewById(R.id.mysearch);
        searchMethod();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fragment = FilteringFragment.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().add(R.id.content_frame, fragment).commit();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        displaySelectedFragment(0);
    }


    public void searchMethod() {
        list = new String[]{"ACDC", "Highway to Hell", "King Crimson", "Deep Purple", "Bach"};
        materialSearchView.clearFocus();
        materialSearchView.setSuggestions(list);
        materialSearchView.setEllipsize(true);
        materialSearchView.setSubmitOnClick(true);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDiscsFromDb(query);
                InputMethodManager in = (InputMethodManager) NavigationDrawerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(materialSearchView.getWindowToken(), 0);
                materialSearchView.dismissSuggestions();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                getDiscsFromDb(newText);
                return true;
            }
            private void getDiscsFromDb(String searchText) {
                searchText = "%" + searchText + "%";
                final MyAdapter myAdapter = new MyAdapter(NavigationDrawerActivity.this);
                recyclerView = findViewById(R.id.recycler_view_id);

                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(NavigationDrawerActivity.this, 2));

                discViewModel = ViewModelProviders.of(NavigationDrawerActivity.this).get(DiscViewModel.class);
                discViewModel.getDiscsFromDb(searchText).observe(NavigationDrawerActivity.this, new Observer<List<DiscModel>>() {
                    @Override
                    public void onChanged(@Nullable List<DiscModel> list) {
                        myAdapter.setDiscs(list);
                    }
                });
            }
        });
        materialSearchView.closeSearch();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
            materialSearchView.dismissSuggestions();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        MenuItem item = menu.findItem(R.id.search);
        materialSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public void stackFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void displaySelectedFragment(int id) {
        Class fragmentClass;
        switch (id) {
            case R.id.lista:
                fragmentClass = TabsFragment.class;
                break;
            case R.id.koszyk:
                fragmentClass = ShoppingCartFragment.class;
                fab.hide();
                break;
            case R.id.favorites:
                fragmentClass = FavoritesFragment.class;
                fab.hide();
                break;
            default:
                fragmentClass = TabsFragment.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedFragment(id);
        return true;
    }

}
