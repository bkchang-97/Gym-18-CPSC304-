package ca.ubc.cs304.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.*;
import ca.ubc.cs304.utility.SQLUtil;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static Connection connection = null;

    //private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void createDatabase() throws SQLException {
        try {
            SQLUtil.executeFile(connection, new File("src/ca/ubc/cs304/sql/scripts/setup.sql"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void populateDatabase() throws SQLException {
        try {
            SQLUtil.executeFile(connection, new File("src/ca/ubc/cs304/sql/scripts/populate.sql"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean updateUser(UserWithDietPlan user) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update UserWithDietPlan SET userName = ?, userPhone = ?, dplanID =? WHERE userId = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            if (user.getDietPlanId() == 0) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, user.getDietPlanId());
            }
            ps.setInt(4, user.getUserId());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }
    public boolean updateUser(UserPracticesExercisePlan user) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update UserFollowsExercisePlan SET eplanID = ? WHERE userId = ?");
            if (user.getPlanId() == 0) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, user.getPlanId());
            }
            ps.setInt(2, user.getUserId());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public boolean addUserWithDietPlan(UserWithDietPlan user) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert INTO UserWithDietPlan VALUES (?,?,?,?)");
            ps.setString(1,user.getName());
            ps.setInt(2,user.getUserId());
            ps.setString(3,user.getPhoneNumber());
            if (user.getDietPlanId() == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, user.getDietPlanId());
            }
            ps.execute();
            connection.commit();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }



    public boolean updateTrainer(Trainer trainer) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update Trainer SET trainerName = ?, trainerPhone = ? WHERE trainerID = ?");
            ps.setString(1, trainer.getName());
            ps.setString(2, trainer.getPhoneNumber());
            ps.setInt(3, trainer.getTrainerId());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return false;
        }
    }

    public Trainer getBusiestTrainer() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * FROM Trainer t WHERE NOT EXISTS  (select * From UserWithDietPlan u WHERE NOT EXISTS (select * FROM TrainerTrainsUser ttu WHERE t.trainerID = ttu.trainerID  AND ttu.userID = u.userID))");
            ResultSet rs = ps.executeQuery();
            Trainer trainer = null;
            while (rs.next()){
                 trainer = new Trainer(rs.getString(2),rs.getInt(1),rs.getString(3));
            }

            return trainer;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            return null;
        }
    }

    public UserWithDietPlan getUserWithDietPlan(int id) {
        try {
            UserWithDietPlan user = null;
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserWithDietPlan WHERE userId = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                user = new UserWithDietPlan(rs.getString(1), rs.getInt(2), rs.getNString(3), rs.getInt(4));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public boolean deleteUserWithDPlan(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM UserWithDietPlan WHERE userId = ?");
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public String getUserNameWithExercisePlanId(int id) {
        try {
            String userName = "";
            PreparedStatement stmt = connection.prepareStatement
                    ("SELECT userName " +
                    "FROM  UserWithDietPlan,UserFollowsExercisePlan " +
                    "WHERE UserWithDietPlan.userId = (select UserFollowsExercisePlan.userID FROM UserFollowsExercisePlan Where UserFollowsExercisePlan.userID = ?)");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                userName = rs.getString(1);
            }
            return userName;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public ExercisePlanContainsExercise getExercisePlan(int id){
        try {
            ExercisePlanContainsExercise plan = null;
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ExercisePlan WHERE eplanID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                plan = new ExercisePlanContainsExercise(rs.getString(1), id);
            }
            return plan;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public Trainer getTrainer(int id) {
        try {
            Trainer trainer = null;
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Trainer WHERE trainerID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                trainer = new Trainer(rs.getString(2), rs.getInt(1), rs.getString(3));
            }
            return trainer;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public DietPlan getUserDietPlan(UserWithDietPlan user) {
        if (user == null) {
            return null;
        }
        try {
            int dietPlanId = user.getDietPlanId();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DietPlan WHERE dplanID = ?");
            stmt.setInt(1, dietPlanId);
            DietPlan dietPlan = null;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dietPlan = new DietPlan(rs.getInt(1), rs.getString(2));
            }
            return dietPlan;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }

    }

    public ArrayList<String> getAllDietPlans() {
        ArrayList<String> dietPlans = new ArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DietPlan");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String curDietPlan = rs.getString(2) + " : " + rs.getInt(1);
                dietPlans.add(curDietPlan);
            }
            return dietPlans;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

//    public ArrayList<TimeslotSchedule> getAllSessions(int ID) {
//        ArrayList<TimeslotSchedule> sessions = new ArrayList();
//        try {
//            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Timeslot WHERE userID = ?");
//            stmt.setInt(1, ID);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                TimeslotSchedule session = rs.
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }



    public ExercisePlanContainsExercise getUserExercisePlans(UserWithDietPlan user) {
        if (user == null) {
            return null;
        }
        try {
            int uid = user.getUserId();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserFollowsExercisePlan WHERE userID = ?");
            stmt.setInt(1, uid);
            int planId = 0;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                planId = rs.getInt(1);
            }
            stmt = connection.prepareStatement("SELECT * FROM ExercisePlanContainsExercise WHERE eplanID = ?");
            stmt.setInt(1, planId);
            rs = stmt.executeQuery();
            String exerciseName = "";
            while(rs.next()){
                exerciseName = rs.getString(1);
            }
            ExercisePlanContainsExercise exercisePlan = new ExercisePlanContainsExercise(exerciseName,planId);
            return exercisePlan;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }
    public ArrayList<String> getAllExercisePlans() {
        ArrayList<String> exercisePlans = new ArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ExercisePlanContainsExercise");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String curExercisePlan = rs.getInt(2) + " : " + rs.getString(1);
                exercisePlans.add(curExercisePlan);
            }
            return exercisePlans;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public int countUser() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT count(userId) FROM UserWithDietPlan");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return 0;
        }
    }

    public int getUserCountByTrainer(int tid) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT count(userId) FROM TrainerTrainsUser Group By trainerId having trainerId = ?");
            stmt.setInt(1, tid);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return 0;
        }
    }

    public String getTrainerNameById(int tid) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT trainerName FROM Trainer Where trainerId = ?");
            stmt.setInt(1, tid);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return "";
        }
    }
}
