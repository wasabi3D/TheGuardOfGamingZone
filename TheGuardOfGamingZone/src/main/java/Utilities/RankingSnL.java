package Utilities;

import BotTasks.BotKernel;
import Components.RankingData;
import Components.SortedRanking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RankingSnL {

    public static RankingData loadFromFile(File file) throws IOException {
        Gson gson = new Gson();


        return gson.fromJson(BotKernel.getBotFileIOSystem().load(file),RankingData.class);


    }

    public static String getStr(Object sr){
        Gson gson = new Gson();
        return gson.toJson(sr);
    }

    public static void writeToFile(String arg, File file) throws IOException{
        BotKernel.getBotFileIOSystem().override(file,arg);
    }
}
