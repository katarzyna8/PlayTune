package com.example.katarzyna.playtune.Model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {DiscModel.class}, version = 1)
public abstract class DiscRoomDatebase extends RoomDatabase {
    private static volatile DiscRoomDatebase INSTANCE;
    public abstract DiscDAO discDAO();

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static DiscRoomDatebase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiscRoomDatebase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DiscRoomDatebase.class, "DiscDatabase").addCallback(roomDatabaseCallback).allowMainThreadQueries().build();
                }
            }

        }
        return INSTANCE;
    }


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DiscDAO discDAO;
        private PopulateDbAsync(DiscRoomDatebase discRoomDatebase) {
            discDAO = discRoomDatebase.discDAO();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            discDAO.deleteAll();
            DiscModel discModelOne = new DiscModel(0, "ACDC", "Highway to Hell ", 1979, "Hard rock", 39.99, "acdc", "Australia", false, false, "acdc2", "acdc3", "AC/DC – australijski zespół hardrockowy założony w Sydney w 1973 roku przez braci Angusa i Malcolma Youngów. Zespół jest uznawany m.in. za pioniera muzyki hardrockowej. Członkowie zespołu, mimo wszystko, zawsze klasyfikowali swoją muzykę jako rock & roll.");
            DiscModel d2 = new DiscModel(0, "ACDC", "Back in Black", 1980, "Hard rock", 39.99, "back", "Australia", false, false, "acdc2", "acdc3", "AC/DC – australijski zespół hardrockowy założony w Sydney w 1973 roku przez braci Angusa i Malcolma Youngów. Zespół jest uznawany m.in. za pioniera muzyki hardrockowej. Członkowie zespołu, mimo wszystko, zawsze klasyfikowali swoją muzykę jako rock & roll.");
            DiscModel d3 = new DiscModel(0, "ACDC", "High Voltage ", 1976, "Hard rock", 39.99, "highv", "Australia", false, false, "acdc2", "acdc3", "AC/DC – australijski zespół hardrockowy założony w Sydney w 1973 roku przez braci Angusa i Malcolma Youngów. Zespół jest uznawany m.in. za pioniera muzyki hardrockowej. Członkowie zespołu, mimo wszystko, zawsze klasyfikowali swoją muzykę jako rock & roll.");
            DiscModel d4 = new DiscModel(0, "Led Zeppelin", "Celebration Day", 2012, "Hard rock", 39.99, "zepp", "Anglia", false, false, "ledz", "ledzz", "Led Zeppelin – brytyjska grupa założona w Londynie, będąca jednym z pionierów hard rocka. Uważana za jeden z najbardziej znaczących zespołów muzycznych w historii. Jej muzyka stanowi połączenie rocka, folku i bluesa z mniejszymi, lecz zauważalnymi, wpływami rockabilly, reggae, soulu, funku, muzyki poważnej, celtyckiej, indyjskiej, arabskiej, muzyki latynoskiej i country. Zespół sprzedał 200–300 milionów płyt na całym świecie i ponad 111 milionów w samej Ameryce.");
            DiscModel d5 = new DiscModel(0, "Led Zeppelin", "Led Zeppelin", 1969, "Hard rock", 39.99, "ledzep", "Anglia", false, false, "ledz", "ledzz", "Led Zeppelin – brytyjska grupa założona w Londynie, będąca jednym z pionierów hard rocka. Uważana za jeden z najbardziej znaczących zespołów muzycznych w historii. Jej muzyka stanowi połączenie rocka, folku i bluesa z mniejszymi, lecz zauważalnymi, wpływami rockabilly, reggae, soulu, funku, muzyki poważnej, celtyckiej, indyjskiej, arabskiej, muzyki latynoskiej i country. Zespół sprzedał 200–300 milionów płyt na całym świecie i ponad 111 milionów w samej Ameryce.");
            DiscModel d6 = new DiscModel(0, "Led Zeppelin", "Led Zeppelin II", 1969, "Hard rock", 39.99, "led2", "Anglia", false, false, "ledz", "ledzz", "Led Zeppelin – brytyjska grupa założona w Londynie, będąca jednym z pionierów hard rocka. Uważana za jeden z najbardziej znaczących zespołów muzycznych w historii. Jej muzyka stanowi połączenie rocka, folku i bluesa z mniejszymi, lecz zauważalnymi, wpływami rockabilly, reggae, soulu, funku, muzyki poważnej, celtyckiej, indyjskiej, arabskiej, muzyki latynoskiej i country. Zespół sprzedał 200–300 milionów płyt na całym świecie i ponad 111 milionów w samej Ameryce.");
            DiscModel d7 = new DiscModel(0, "Led Zeppelin", "Led Zeppelin IV", 1971, "Hard rock", 39.99, "led4", "Anglia", false, false, "ledz", "ledzz", "Led Zeppelin – brytyjska grupa założona w Londynie, będąca jednym z pionierów hard rocka. Uważana za jeden z najbardziej znaczących zespołów muzycznych w historii. Jej muzyka stanowi połączenie rocka, folku i bluesa z mniejszymi, lecz zauważalnymi, wpływami rockabilly, reggae, soulu, funku, muzyki poważnej, celtyckiej, indyjskiej, arabskiej, muzyki latynoskiej i country. Zespół sprzedał 200–300 milionów płyt na całym świecie i ponad 111 milionów w samej Ameryce.");
            DiscModel d8 = new DiscModel(0, "Led Zeppelin", "Physical Graffiti", 1975, "Hard rock", 39.99, "phys", "Anglia", false, false, "ledz", "ledzz", "Led Zeppelin – brytyjska grupa założona w Londynie, będąca jednym z pionierów hard rocka. Uważana za jeden z najbardziej znaczących zespołów muzycznych w historii. Jej muzyka stanowi połączenie rocka, folku i bluesa z mniejszymi, lecz zauważalnymi, wpływami rockabilly, reggae, soulu, funku, muzyki poważnej, celtyckiej, indyjskiej, arabskiej, muzyki latynoskiej i country. Zespół sprzedał 200–300 milionów płyt na całym świecie i ponad 111 milionów w samej Ameryce.");
            DiscModel d9 = new DiscModel(0, "Deep purple", "Schades of Deep Purple", 1968, "Hard rock", 39.99, "purple", "Anglia", false, false, "deep", "purple", "Deep Purple – brytyjska grupa rockowa. Grupa powstała w Hertfordshire w 1968 początkowo pod nazwą Roundabout w następującym składzie: Ritchie Blackmore, Jon Lord, Nick Simper, Ian Paice oraz Rod Evans.");
            DiscModel d10 = new DiscModel(0, "King Crimson", "In the court of the Crimson King", 1969, "Rock progresywny", 39.99, "crimso", "Anglia", false, false, "king", "king2", "King Crimson – angielski zespół muzyczny z kręgu awangardowego i progresywnego rocka, założona w 1969, aktywna z przerwami do chwili obecnej. Jej korzeni należy szukać w krótko działającym Giles, Giles and Fripp. Wskutek licznych zmian personalnych nigdy nie wykształciła stałego brzmienia");
            DiscModel d11 = new DiscModel(0, "King Crimson", "Red", 1974, "Rock progresywny", 39.99, "red", "Anglia", false, false, "king", "king2", "King Crimson – angielski zespół muzyczny z kręgu awangardowego i progresywnego rocka, założona w 1969, aktywna z przerwami do chwili obecnej. Jej korzeni należy szukać w krótko działającym Giles, Giles and Fripp. Wskutek licznych zmian personalnych nigdy nie wykształciła stałego brzmienia");
            DiscModel d12 = new DiscModel(0, "King Crimson", "Islands", 1971, "Rock progresywny", 39.99, "islands", "Anglia", false, false, "king", "king2", "King Crimson – angielski zespół muzyczny z kręgu awangardowego i progresywnego rocka, założona w 1969, aktywna z przerwami do chwili obecnej. Jej korzeni należy szukać w krótko działającym Giles, Giles and Fripp. Wskutek licznych zmian personalnych nigdy nie wykształciła stałego brzmienia");
            DiscModel d13 = new DiscModel(0, "Supertramp", "Crime of the century", 1974, "Rock progresywny", 39.99, "supert", "Anglia", false, false, "king", "king2", "King Crimson – angielski zespół muzyczny z kręgu awangardowego i progresywnego rocka, założona w 1969, aktywna z przerwami do chwili obecnej. Jej korzeni należy szukać w krótko działającym Giles, Giles and Fripp. Wskutek licznych zmian personalnych nigdy nie wykształciła stałego brzmienia");
            DiscModel d14 = new DiscModel(0, "Dawid Podsiadło", "Comfort and Happiness", 2013, "Pop", 29.99, "comfort", "Polska", false, false, "dawid", "dawid2", "Dawid Podsiadło – polski piosenkarz oraz autor tekstów. Wokalista zespołu Curly Heads. Artysta zaczynał od występów w programie telewizyjnym X Factor, którego to wygrał drugą edycję. W 2013 roku wydał swój debiutancki album pt. Comfort and Happiness. ");
            DiscModel d15 = new DiscModel(0, "Dawid Podsiadło", "Annoyance and Disappointment", 2015, "Pop", 29.99, "anno", "Polska", false, false, "dawid", "dawid2", "Dawid Podsiadło – polski piosenkarz oraz autor tekstów. Wokalista zespołu Curly Heads. Artysta zaczynał od występów w programie telewizyjnym X Factor, którego to wygrał drugą edycję. W 2013 roku wydał swój debiutancki album pt. Comfort and Happiness. ");
            DiscModel d16 = new DiscModel(0, "Dawid Podsiadło", "Małomiasteczkowy", 2018, "Pop", 29.99, "malo", "Polska", false, false, "dawid", "dawid2", "Dawid Podsiadło – polski piosenkarz oraz autor tekstów. Wokalista zespołu Curly Heads. Artysta zaczynał od występów w programie telewizyjnym X Factor, którego to wygrał drugą edycję. W 2013 roku wydał swój debiutancki album pt. Comfort and Happiness. ");
            DiscModel d17 = new DiscModel(0, "Michael Jackson", "Thriller", 1982, "Pop", 29.99, "thri", "Ameryka", false, false, "jack", "jack2", "Michael Joseph Jackson – amerykański muzyk, autor tekstów, artysta estradowy, tancerz, aktor, kompozytor i filantrop, którego kariera i życie osobiste stały się ważną częścią kultury masowej ostatnich czterech dekad.");
            DiscModel d18 = new DiscModel(0, "Michael Jackson", "Dangerous", 1991, "Pop", 29.99, "dan", "Ameryka", false, false, "jack", "jack2", "Michael Joseph Jackson – amerykański muzyk, autor tekstów, artysta estradowy, tancerz, aktor, kompozytor i filantrop, którego kariera i życie osobiste stały się ważną częścią kultury masowej ostatnich czterech dekad.");
            DiscModel d19 = new DiscModel(0, "Johann Sebastian Bach", "Best of Bach", 2000, "Muzyka klasyczna", 49.99, "bach", "Niemcy", false, false, "bach", "bach", "Johann Sebastian Bach – kompozytor i organista niemiecki epoki baroku, jeden z najwybitniejszych artystów w dziejach muzyki, kompozytor dworski. Czołowa postać rodu Bachów, wybitny wykonawca i jeden z najbardziej wpływowych kompozytorów.");
            DiscModel d20 = new DiscModel(0, "Ludwig van Beethoven", "Die Streichquartette ", 2013, "Muzyka klasyczna", 49.99, "bet", "Niemcy", false, false, "beet", "beet", "Ludwig van Beethoven – kompozytor i pianista niemiecki, ostatni z tzw. klasyków wiedeńskich, a zarazem prekursor romantyzmu w muzyce, uznawany za jednego z największych twórców muzycznych wszech czasów.");
            discDAO.insertAllDisc(discModelOne, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20);
            return null;
        }
    }
}
