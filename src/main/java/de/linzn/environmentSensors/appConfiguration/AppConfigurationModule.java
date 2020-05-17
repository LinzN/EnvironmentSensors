/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.environmentSensors.appConfiguration;


import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class AppConfigurationModule {

    /* Variables */
    public String socketHost;
    public int socketPort;
    public String cryptAESKey;
    public byte[] vector16B;
    private String fileName = "settings.properties";

    /* Create class instance */
    public AppConfigurationModule() {
        this.init();
    }

    private static byte[] toByteArray(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        byte[] result = new byte[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Byte.parseByte(strings[i]);
        }
        return result;
    }

    /* Create folders and files*/
    public void init() {
        File file = new File(this.fileName);
        if (!file.exists()) {
            create();
        }
        this.load();

    }

    /* Setup properies file with values */
    public void create() {

        Properties prop = new Properties();
        OutputStream output;

        try {

            output = new FileOutputStream(this.fileName);
            // set the properties value
            prop.setProperty("socketHost", "10.40.0.40");
            prop.setProperty("socketPort", "11102");
            prop.setProperty("cryptAESKey", "3979244226452948404D635166546A576D5A7134743777217A25432A462D4A61");
            prop.setProperty("vector16B", Arrays.toString(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7}));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /* Load the file in the memory */
    public void load() {

        Properties prop = new Properties();
        InputStream input;

        try {
            input = new FileInputStream(this.fileName);

            prop.load(input);

            this.socketHost = prop.getProperty("socketHost");
            this.socketPort = Integer.parseInt(prop.getProperty("socketPort"));
            this.cryptAESKey = prop.getProperty("cryptAESKey");
            this.vector16B = toByteArray(prop.getProperty("vector16B"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
