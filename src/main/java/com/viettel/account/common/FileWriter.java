package com.viettel.account.common;

import com.viettel.account.service.dto.AccountDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {
    public static void writeToFile(AccountDTO accountDTO) throws IOException {
        File folder = new File("./output");
        String messageToWrite = accountDTO.toString();
        String fileName = "account_" + System.currentTimeMillis() + ".txt";

        if(folder.list().length > 10){
            System.out.println("There are more than 10 files, to much to store, deleting...");

            String []fileNames = folder.list();
            for(String fName : fileNames){
                File fileToDelete = new File(folder, fName);
                System.out.println("deleting filename: "+ fName + ": " +fileToDelete.delete());
            }
        }

        File dataFile = new File(folder, fileName);
        if(!dataFile.exists()){
            dataFile.createNewFile();
        }
        try(java.io.FileWriter writer = new java.io.FileWriter(dataFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer)){
            bufferedWriter.write(messageToWrite);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
