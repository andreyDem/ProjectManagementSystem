package com.management.project.controllers;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Slava Makhinich
 */
public class Always1InputStream extends InputStream {

    protected int count = 0;

    @Override
    public int read() throws IOException {
        if (count%3 == 0) {
            count++;
            return 49;
        }
        count++;
        return -1;
    }
}
