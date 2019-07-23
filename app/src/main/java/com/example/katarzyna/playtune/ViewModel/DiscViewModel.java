package com.example.katarzyna.playtune.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Model.DiscRepository;

import java.util.List;

public class DiscViewModel extends AndroidViewModel {
    private LiveData<List<DiscModel>> listLiveData;
    private DiscRepository discRepository;

    public DiscViewModel(@NonNull Application application) {
        super(application);
        discRepository = new DiscRepository(application);
        listLiveData = discRepository.getAllDisc();
    }

    public LiveData<List<DiscModel>> getAllDisc() {
        return listLiveData;
    }

    public DiscModel getDisc(int id) {
        return discRepository.getDisc(id);
    }

    public LiveData<List<DiscModel>> getDiscsFromDb(String searchText) {
        return discRepository.getDiscsFromDb(searchText);
    }

    public LiveData<List<DiscModel>> getDiscsByGenres(String genre) {
        return discRepository.getDiscsByGenres(genre);
    }

    public LiveData<List<DiscModel>> getDiscsByYearCountry(long from, long to, String country) {
        return discRepository.getDiscsByYearCountry(from, to, country);
    }

    public LiveData<List<DiscModel>> getDiscsByYear(long from, long to) {
        return discRepository.getDiscsByYear(from, to);
    }

    public LiveData<List<DiscModel>> getDiscsByCountry(String country) {
        return discRepository.getDiscsByCountry(country);
    }

    public void updateDB(DiscModel discModel) {
        discRepository.updateDB(discModel);
    }
}
