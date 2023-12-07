package org.modelio.platform.model.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

public class MetaModeltransforer {

    public static void main(String[] args) {

        try {
            InputStream initialStream = new FileInputStream(new File("H:\\modelio\\alouette\\work\\eclipse\\modelio\\platform\\platform.model.ui\\res\\metamodel_labels.properties"));
            Properties prop = new Properties();
            prop.load(initialStream);


            for(Entry<Object,Object> entry : prop.entrySet()) {
                System.out.println("\"" + entry.getKey().toString().replace("$","") + "\":\"" + entry.getValue() + "\",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
