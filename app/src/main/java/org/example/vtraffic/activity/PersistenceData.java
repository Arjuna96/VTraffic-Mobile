package org.example.vtraffic.activity;

/**
 * Created by DELL on 5/18/2018.
 */

public class PersistenceData {

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        PersistenceData.useremail = useremail;
    }

    public static String useremail;

    public static double getLati() {
        return lati;
    }

    public static void setLati(double lati) {
        PersistenceData.lati = lati;
    }

    public static double getLonti() {
        return lonti;
    }

    public static void setLonti(double lonti) {
        PersistenceData.lonti = lonti;
    }

    public static double lati;
    public static double lonti;

}
