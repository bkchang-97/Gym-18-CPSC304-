package ca.ubc.cs304.delegates;


import java.util.ArrayList;
import java.util.Date;

public interface GymFunctionDelegate {

    public void databaseSetup();

    public void showUserWithDPlan(int userid);
    public void showUserWithEPlan(int ePlanId,int userid);
    public void updateUser(int id, String name, String phoneNum, int dietPlanId);
    public void addUser(int id, String name, String phoneNum, int dietPlanId);
    public void deleteUserWithDPlan(int userid);


    public void showTrainer(int id);
    public void getBusiestTrainer();
    public void updateTrainer(String name,int id,String phoneNum);

    public void showDietPlanByUser(int id);
    public ArrayList<String> getAllDietPlans();
    public void updateUserDietPlan(int id, int dietPlanId);

    public void showExercisePlanByUser(int id);
    public ArrayList<String> getAllExercisePlans();
    public void updateUserExercisePlan(int uid, int ePlanId);

    public void showTimeSlot(int uid, int tid, Date d);
    public void insertTimeSlot(int tid,int time, Date d);
    public void deleteTimeSlot(int tid,int time, Date d);

    public int countUser();
    public void showUserCountByTrainer(int tid);



    public void terminalTransactionsFinished();

}
