package com.example.alumno.clase5;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 25/04/2019.
 */

public class ParseXml {

    public static List<ItemModel> parseXml(String listaItems) {
        List<ItemModel> ListaItem = new ArrayList<>();
        XmlPullParser xml = Xml.newPullParser();

        try {
            xml.setInput(new StringReader(listaItems));

            int event = xml.getEventType();
            ItemModel i = null;
            while (event != XmlPullParser.END_DOCUMENT) {

                if (event == XmlPullParser.START_TAG) {
                    if("item".equals(xml.getName()))//obtenemos el nombre del tag sobre el cual estamos parado
                    {
                        i = new ItemModel();
                    }
                    if("title".equals(xml.getName()))
                    {
                        if(i != null) {
                            String title = xml.nextText();
                            i.setTitle(title);
                        }
                    }
                    if("description".equals(xml.getName()))
                    {
                        if(i != null) {
                            String description = xml.nextText();
                            i.setDescription(description);
                        }
                    }
                    if("link".equals(xml.getName()))
                    {
                        if(i != null) {
                            String link = xml.nextText();
                            i.setLink(link);
                        }
                    }
                    if("image".equals(xml.getName()))
                    {
                        if(i != null) {
                            String image = xml.nextText();
                            i.setImage(image);
                        }
                    }
                    if ("enclosure".equals(xml.getName())) {
                        if(i != null) {
                            String image = xml.getAttributeValue(null,"url");
                            i.setImage(image);
                        }
                    }
                } else if(event == XmlPullParser.END_TAG && "item".equals(xml.getName())) {
                    ListaItem.add(i);
                }

                event = xml.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ListaItem;
    }

}
