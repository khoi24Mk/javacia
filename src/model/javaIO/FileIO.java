package model.javaIO;

import java.io.*;
import java.net.*;
import java.util.*;



public class FileIO extends Thread{

    static BufferedReader br = null;
    static BufferedWriter bw = null;

    Socket _client;

    public FileIO(Socket client){
        _client = client;
    }


    /*FILE ++++++++++++++++++++++++++++++++++++++++++++++++*/

    public static ArrayList<String> readFile(String path) throws IOException {

        br = new BufferedReader(new FileReader(path));
        String str_temp;
        ArrayList<String> list = new ArrayList<String>();

        while ((str_temp = br.readLine()) != null) {
            list.add(str_temp);
        }
        br.close();

        return list;
    }

    public static void writeFile(String path, ArrayList<String> list) throws IOException {

        bw = new BufferedWriter(new FileWriter(path));
        String endLine = System.getProperty("line.separator");

        for (String line :list){
            System.out.println(line);
            bw.write(line + endLine);
        }
        bw.close();
    }

    public static void copyFile(String pathIn, String pathOut) throws IOException {
        ArrayList<String> list = readFile(pathIn);
        writeFile(pathOut, list);
    }



}

