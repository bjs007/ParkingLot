package com.gojek.parkinglot.storage;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.model.SlotDistanceMapperClass;

import java.util.Map;
import java.util.TreeSet;

public class InMemoryStorageService {
    protected static InMemoryStorageService inMemoryStorageService = null;
    private InMemoryStorage inMemoryStorage;

    private InMemoryStorageService(){

    }

    public static InMemoryStorageService getInstance(){
        if(inMemoryStorageService == null){
            synchronized (InMemoryStorageService.class){
                if(inMemoryStorageService == null)
                inMemoryStorageService = new InMemoryStorageService();
            }
        }

        return inMemoryStorageService;
    }


}
