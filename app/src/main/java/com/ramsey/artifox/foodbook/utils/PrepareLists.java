package com.ramsey.artifox.foodbook.utils;

import com.ramsey.artifox.foodbook.model.Food;

import java.util.ArrayList;
import java.util.List;


public class PrepareLists {

    private static final int SOUPS = 6;
    private static final int ROLLS = 18;
    private static final int WARMS = 8;
    private static final int SALADS = 7;
    private static final int SNAKS = 20;

    List<Food> mFoots;
    static List<Food> mSoups;
    static List<Food> mRolls;
    static List<Food> mSalads;
    static List<Food> mWasms;
    static List<Food> mSnaks;

    public PrepareLists(List<Food> foots) {
        mFoots = foots;
        mSoups = new ArrayList<Food>();
        mRolls = new ArrayList<Food>();
        mSalads = new ArrayList<Food>();
        mWasms = new ArrayList<Food>();
        mSnaks = new ArrayList<Food>();

        createLists();
    }

    public void createLists() {
        for (Food elem : mFoots) {
            switch (Integer.parseInt(elem.getCategoryId())) {
                case SOUPS:
                    mSoups.add(elem);
                    break;
                case ROLLS:
                    mRolls.add(elem);
                    break;
                case WARMS:
                    mWasms.add(elem);
                    break;
                case SALADS:
                    mSalads.add(elem);
                    break;
                case SNAKS:
                    mSnaks.add(elem);
                    break;
                default:
                    break;

            }
        }
    }

    public List<Food> getFoots() {
        return mFoots;
    }

    public void setFoots(List<Food> foots) {
        mFoots = foots;
    }

    public static List<Food> getRolls() {
        return mRolls;
    }

    public void setRolls(List<Food> rolls) {
        mRolls = rolls;
    }

    public static List<Food> getSalads() {
        return mSalads;
    }

    public void setSalads(List<Food> salads) {
        mSalads = salads;
    }

    public static List<Food> getSnaks() {
        return mSnaks;
    }

    public void setSnaks(List<Food> snaks) {
        mSnaks = snaks;
    }

    public static List<Food> getSoups() {
        return mSoups;
    }

    public void setSoups(List<Food> soups) {
        mSoups = soups;
    }

    public static List<Food> getWasms() {
        return mWasms;
    }

    public void setWasms(List<Food> wasms) {
        mWasms = wasms;
    }
}
