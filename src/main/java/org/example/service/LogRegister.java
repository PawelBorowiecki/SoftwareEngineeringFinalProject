package org.example.service;

import org.example.model.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogRegister {
    private final List<Log> logs;

    public LogRegister() {
        this.logs = new ArrayList<>();
    }

    public List<Log> getlogs(){
        return this.logs;
    }

    public void addLog(Log log){
        this.logs.add(log);
    }

    public void saveToFile(){
        File file = new File("logs.csv");
        String line;
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
            if(!file.exists()){
                file.createNewFile();
            }
            for(Log log : this.logs){
                line = String.format("Date: %s, employeeId: %s, orderId: %s, state: %s\n", log.getDate().toString(), log.getEmployeeId(), log.getOrderId(), log.getState());
                bufferedWriter.append(line);
            }
        }catch(IOException e){
            throw new RuntimeException("Logs saving to file failed.");
        }
        System.out.println("Zapisano logi do pliku.");
    }

    public void clear(){
        this.logs.clear();
    }
}
