package com.example.katarzyna.playtune.Model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DiscRepository {

    private DiscDAO discDAO;
    private LiveData<List<DiscModel>> listLiveData;

    public DiscRepository(Application application) {
        DiscRoomDatebase discRoomDatebase = DiscRoomDatebase.getDatabase(application);
        discDAO = discRoomDatebase.discDAO();
        listLiveData = discDAO.getAllDisc();
    }

    public LiveData<List<DiscModel>> getAllDisc() {
        return listLiveData;
    }

    public LiveData<List<DiscModel>> getDiscsFromDb(String searchText) {
        return discDAO.getDiscsFromDb(searchText);
    }

    public LiveData<List<DiscModel>> getDiscsByGenres(String genre) {
        return discDAO.getDiscsByGenres(genre);
    }

    public LiveData<List<DiscModel>> getDiscsByYear(long from, long to) {
        return discDAO.getDiscsByYear(from, to);
    }

    public LiveData<List<DiscModel>> getDiscsByYearCountry(long from, long to, String country) {
        return discDAO.getDiscsByYearCountry(from, to, country);
    }

    public LiveData<List<DiscModel>> getDiscsByCountry(String country) {
        return discDAO.getDiscsByCountry(country);
    }

    public void updateDB(DiscModel discModel) {
        discDAO.updateDB(discModel);
    }

    public DiscModel getDisc(int id) {
        return discDAO.getDisc(id);
    }

    public void insertDisc(DiscModel discModel) {
        new insertAsyncTask(discDAO).execute(discModel);
    }


    private static class insertAsyncTask extends AsyncTask<DiscModel, Void, Void> {
        private DiscDAO myAsyncTaskDao;

        public insertAsyncTask(DiscDAO discDAO) {
            myAsyncTaskDao = discDAO;
        }

        @Override
        protected Void doInBackground(final DiscModel... params) {
            myAsyncTaskDao.insertDisc(params[0]);

            return null;
        }
    }

}
