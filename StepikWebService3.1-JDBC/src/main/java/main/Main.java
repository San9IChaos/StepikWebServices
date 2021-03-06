package main;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService(DBService.getH2connection());
        dbService.printConnectInfo();

        try{
            long userId = dbService.addUser("root");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);
            dbService.cleanUp();
        }catch (DBException e){
            e.printStackTrace();
        }
    }
}
