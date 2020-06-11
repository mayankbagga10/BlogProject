//************************DONE**************************
package com.upgrad.blog.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TODO: 8.1. Implement writeLog() method with the following method signature.
 * public void writeLog(String logMessage, String path)
 * This method should append the log message to the log file that is stored at the
 * Input path.
 */

public class LogWriter {

    public void writeLog(String logMessage , String path) throws IOException {
        File file = new File("logs.txt");

        FileWriter fw = new FileWriter(file,true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(logMessage);

        bw.close();

        fw.close();


    }

}