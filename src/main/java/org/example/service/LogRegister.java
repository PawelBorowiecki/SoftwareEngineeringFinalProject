package org.example.service;

import org.example.model.Log;

import java.util.ArrayList;
import java.util.List;

public class LogRegister {
    private List<Log> logs;

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
        //TODO
    }

    public void clear(){
        this.logs.clear();
    }
}
