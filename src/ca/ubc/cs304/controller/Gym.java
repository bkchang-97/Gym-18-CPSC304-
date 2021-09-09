package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.GymFunctionDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.GymFunctionWindow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Gym implements GymFunctionDelegate {
    private DatabaseConnectionHandler dbHandler;
    private GymFunctionWindow functionWindow = null;
    private final String userName = "ora_yczycz";
    private final String password = "a15689078";


    public Gym(){
        dbHandler = new DatabaseConnectionHandler();
    }

    public void start() {
        boolean didConnect = dbHandler.login(userName, password);


        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            System.out.println("Connected to Oracle");
            databaseSetup();
            functionWindow = new GymFunctionWindow(this);
            functionWindow.setupDatabase(this);
            functionWindow.showFrame(this);

        } else {
            //functionWindow.dispose();
            System.out.println("Login Failed");
            System.exit(-1);
            }
        }

    @Override
    public void databaseSetup() {
        try{
            dbHandler.createDatabase();
            dbHandler.populateDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void showUserWithDPlan(int userid) {
        UserWithDietPlan user = dbHandler.getUserWithDietPlan(userid);
        functionWindow.display(user);
    }



    @Override
    public void showUserWithEPlan(int ePlanId,int userId) {
        ExercisePlanContainsExercise ePlan = dbHandler.getExercisePlan(ePlanId);
        String uName = dbHandler.getUserNameWithExercisePlanId(userId);
        functionWindow.display(ePlan,uName);
    }

    @Override
    public void updateUser(int id, String name, String phoneNum, int dietPlanId) {
        UserWithDietPlan user = new UserWithDietPlan(name,id,phoneNum,dietPlanId);
        boolean updated = dbHandler.updateUser(user);
        if (updated){
            showUserWithDPlan(id);
        }else{
            functionWindow.operationFailed();
        }
    }

    @Override
    public void addUser(int id, String name, String phoneNum, int dietPlanId) {
        UserWithDietPlan user = new UserWithDietPlan(name,id,phoneNum,dietPlanId);
        boolean added = dbHandler.addUserWithDietPlan(user);
        if (added){
            showUserWithDPlan(id);
        }else{
            functionWindow.operationFailed();
        }
    }

    @Override
    public void deleteUserWithDPlan(int userid) {
        boolean deleted = dbHandler.deleteUserWithDPlan(userid);
        if (deleted){
            functionWindow.operationSucceed();
        }else{
            functionWindow.operationFailed();
        }
    }


    @Override
    public void showTrainer(int id) {
        Trainer trainer = dbHandler.getTrainer(id);
        functionWindow.display(trainer);
    }

    @Override
    public void getBusiestTrainer() {
        Trainer trainer = dbHandler.getBusiestTrainer();
        functionWindow.display(trainer);
    }


    @Override
    public void updateTrainer(String name,int id,String phoneNum) {
        Trainer trainer = new Trainer(name,id,phoneNum);
        boolean updated = dbHandler.updateTrainer(trainer);
        if (updated){
            showTrainer(id);
        }else{
            functionWindow.operationFailed();
        }
    }

    @Override
    public void showDietPlanByUser(int id) {
        UserWithDietPlan user = dbHandler.getUserWithDietPlan(id);
        DietPlan dietPlan = dbHandler.getUserDietPlan(user);
        functionWindow.displayDietPlan(dietPlan, user.getName());
    }

    @Override
    public ArrayList<String> getAllDietPlans() {
        ArrayList<String> dietPlans = dbHandler.getAllDietPlans();
        return dietPlans;
    }


    @Override
    public void updateUserDietPlan(int id, int dietPlanId) {
        UserWithDietPlan curUser = dbHandler.getUserWithDietPlan(id);
        UserWithDietPlan newUser = new UserWithDietPlan(curUser.getName(), id, curUser.getPhoneNumber(), dietPlanId);
        boolean updated = dbHandler.updateUser(newUser);
        if (updated){
            showUserWithDPlan(id);
        }else{
            functionWindow.operationFailed();
        }
    }

    @Override
    public void showExercisePlanByUser(int id) {
        UserWithDietPlan user = dbHandler.getUserWithDietPlan(id);
        ExercisePlanContainsExercise exercisePlan = dbHandler.getUserExercisePlans(user);
        functionWindow.display(exercisePlan,user.getName());
    }

    @Override
    public ArrayList<String> getAllExercisePlans() {
        ArrayList<String> exercisePlans = dbHandler.getAllExercisePlans();
        return exercisePlans;
    }


    @Override
    public void updateUserExercisePlan(int uid, int ePlanId) {
        UserPracticesExercisePlan newUser = new UserPracticesExercisePlan(ePlanId,uid);
        boolean updated = dbHandler.updateUser(newUser);
        if (updated){
            showUserWithEPlan(ePlanId,uid);
        }else{
            functionWindow.operationFailed();
        }
    }

    @Override
    public void terminalTransactionsFinished() {

    }

    @Override
    public void insertTimeSlot(int tid, int time, Date d) {
    }

    @Override
    public void showTimeSlot(int uid, int tid, Date d) {

    }

    @Override
    public void deleteTimeSlot(int tid, int time, Date d) {

    }

    @Override
    public int countUser() {
        return dbHandler.countUser();
    }

    @Override
    public void showUserCountByTrainer(int tid) {
        int userCountByTrainer = dbHandler.getUserCountByTrainer(tid);
        String trainerName = dbHandler.getTrainerNameById(tid);
        functionWindow.displayUserCountByTrainer(userCountByTrainer, trainerName);
    }


    public static void main(String args[]) {
        Gym gym = new Gym();
        gym.start();
    }
}
