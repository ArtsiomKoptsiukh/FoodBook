package com.ramsey.artifox.foodbook.utils;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.ramsey.artifox.foodbook.MainActivity;
import com.ramsey.artifox.foodbook.model.Food;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class XMLParser extends AsyncTask<Void, Void, List<Food>> {
    List<Food> mFoots;
    Food mFoot;

    boolean running;
    ProgressDialog progressDialog;

    MainActivity mMainActivity;
    private String text;
    private boolean flag = false;

    public XMLParser(MainActivity activity) {
        mMainActivity = activity;
        mFoots = new ArrayList<Food>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<Food> doInBackground(Void... params) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            XmlPullParser parser = factory.newPullParser();
            URL url = new URL("http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN");
            InputStream stream = url.openStream();
            parser.setInput(stream, null);

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("offer")) {
                            mFoot = new Food();
                        } else if (tagname.equalsIgnoreCase("param")) {
                            if (parser.getAttributeValue(0).equalsIgnoreCase("вес")) {
                                flag = true;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("offer")) {
                            mFoots.add(mFoot);
                        } else if (tagname.equalsIgnoreCase("name")) {
                            mFoot.setName(text);
                        } else if (tagname.equalsIgnoreCase("categoryId")) {
                            mFoot.setCategoryId(text);
                        } else if (tagname.equalsIgnoreCase("price")) {
                            mFoot.setPrice(text);
                        } else if (tagname.equalsIgnoreCase("description")) {
                            mFoot.setDescription(text);
                        } else if (tagname.equalsIgnoreCase("picture")) {
                            mFoot.setImageId(text);
                        } else if (tagname.equalsIgnoreCase("param") && flag) {
                            mFoot.setWeight(text);
                            flag = false;
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mFoots;
    }

    @Override
    protected void onPostExecute(List<Food> foots) {
        super.onPostExecute(foots);

    }
}
