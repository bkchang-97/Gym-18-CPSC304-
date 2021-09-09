package ca.ubc.cs304.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.util.ArrayList;

import ca.ubc.cs304.delegates.GymFunctionDelegate;
import ca.ubc.cs304.model.*;

import javax.swing.*;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class GymFunctionWindow extends JFrame implements ActionListener {
	private GymFunctionDelegate gym = null;
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	private static final int TEXT_FIELD_WIDTH = 10;

	private JPanel panel = null;
	private JButton mainMenuButton = null;

	
	private BufferedReader bufferedReader = null;
	private GymFunctionDelegate delegate = null;


	public GymFunctionWindow(GymFunctionDelegate gym) {
		this.gym = gym;
		mainMenuButton = new JButton("Back");
		mainMenuButton.addActionListener(new mainMenuClickerHandler());
	}
	
	/**
	 * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
	 * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
	 */
	public void setupDatabase(GymFunctionDelegate delegate) {
		this.delegate = delegate;
	}

	public void initialize(){
		JLabel welcomeLabel = new JLabel("Welcome to Gym 304");

		JButton searchUserButton = new JButton("Show User");
		JButton insertUserButton = new JButton("Update User");
		searchUserButton.addActionListener(new searchUserButtonClickerHandler());
		insertUserButton.addActionListener(new updateUserButtonClickerHandler());

		JButton trainerButton = new JButton("Show Trainer");
		trainerButton.addActionListener(new searchTrainerButtonClickerHandler());
		JButton updateTrainerButton = new JButton("Update Trainer");
		updateTrainerButton.addActionListener(new updateTrainerButtonClickerHandler());

		JButton dietPlanButton = new JButton("Show Diet Plan");
		dietPlanButton.addActionListener(new showDietPlanButtonClickerHandler());
		JButton updateDietPlanButton = new JButton("Choose Diet Plan");
		updateDietPlanButton.addActionListener(new updateDietPlanButtonClickerHandler());

		JButton exercisePlanButton = new JButton("Show Exercise Plan");
		exercisePlanButton.addActionListener(new showExercisePlanButtonClickerHandler());
		JButton updateExercisePlanButton = new JButton("Choose Exercise Plan");
		updateExercisePlanButton.addActionListener(new updateExercisePlanButtonClickerHandler());
		JButton scheduleButton = new JButton("");
		JButton updateScheduleButton = new JButton("");

		// Third row
		JButton addUser = new JButton("Add User");
		addUser.addActionListener(new addUserButtonClickerHandler());
		JButton deleteUser = new JButton("Delete User");
		deleteUser.addActionListener(new deleteUserButtonClickerHandler());
		JButton countUser = new JButton("Count user");
		countUser.addActionListener(new countUserHandler());
		JButton countUserByTrainer = new JButton("Count User by Trainer");
		countUserByTrainer.addActionListener(new countUserByTrainerHandler());
		JButton showTrainerAllUser = new JButton("<html>Show Trainer <br> that trains all user </html>");
		showTrainerAllUser.addActionListener(new showTrainerAllUserButtonClickerHandler());

		Dimension buttonSize = new Dimension(180, 100);
		searchUserButton.setPreferredSize(buttonSize);
		insertUserButton.setPreferredSize(buttonSize);
		trainerButton.setPreferredSize(buttonSize);
		updateTrainerButton.setPreferredSize(buttonSize);
		dietPlanButton.setPreferredSize(buttonSize);
		updateDietPlanButton.setPreferredSize(buttonSize);
		exercisePlanButton.setPreferredSize(buttonSize);
		updateExercisePlanButton.setPreferredSize(buttonSize);
		scheduleButton.setPreferredSize(buttonSize);
		updateScheduleButton.setPreferredSize(buttonSize);
		addUser.setPreferredSize(buttonSize);
		deleteUser.setPreferredSize(buttonSize);
		countUser.setPreferredSize(buttonSize);
		countUserByTrainer.setPreferredSize(buttonSize);
		showTrainerAllUser.setPreferredSize(buttonSize);


		panel.setLayout(new GridBagLayout());
		GridBagConstraints labelC = new GridBagConstraints();
		GridBagConstraints buttonC = new GridBagConstraints();

		labelC.fill = GridBagConstraints.HORIZONTAL;
		labelC.ipady = 40;
		labelC.weightx = 0.0;
		labelC.gridwidth = 3;
		labelC.gridx = 0;
		labelC.gridy = 3;

		buttonC.fill = GridBagConstraints.HORIZONTAL;
		this.setContentPane(panel);
 		// adding buttons to panel
		panel.add(welcomeLabel,labelC);

		buttonC.gridx = 0;
		buttonC.gridy = 0;
		panel.add(searchUserButton,buttonC);
		buttonC.gridx = 1;
		panel.add(trainerButton,buttonC);
		buttonC.gridx = 2;
		panel.add(dietPlanButton,buttonC);
		buttonC.gridx = 3;
		panel.add(exercisePlanButton,buttonC);
		buttonC.gridx = 4;
		panel.add(scheduleButton,buttonC);

		buttonC.gridx = 0;
		buttonC.gridy = 1;
		panel.add(insertUserButton, buttonC);
		buttonC.gridx = 1;
		panel.add(updateTrainerButton, buttonC);
		buttonC.gridx = 2;
		panel.add(updateDietPlanButton, buttonC);
		buttonC.gridx = 3;
		panel.add(updateExercisePlanButton, buttonC);
		buttonC.gridx = 4;
		panel.add(updateScheduleButton, buttonC);

		buttonC.gridx = 0;
		buttonC.gridy = 2;
		panel.add(addUser, buttonC);
		buttonC.gridx = 1;
		panel.add(deleteUser, buttonC);
		buttonC.gridx = 2;
		panel.add(countUser, buttonC);
		buttonC.gridx = 3;
		panel.add(countUserByTrainer, buttonC);
		buttonC.gridx = 4;
		panel.add(showTrainerAllUser, buttonC);

		// register login button with action event handler
		searchUserButton.addActionListener(this);
		insertUserButton.addActionListener(this);
		trainerButton.addActionListener(this);
		dietPlanButton.addActionListener(this);
		exercisePlanButton.addActionListener(this);
		scheduleButton.addActionListener(this);
		// anonymous inner class for closing the window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// size the window to obtain a best fit for the components
		this.pack();
		// center the frame
		Dimension d = this.getToolkit().getScreenSize();
		Rectangle r = this.getBounds();
		this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );
	}

	public void showFrame(GymFunctionDelegate delegate) {
		this.delegate = delegate;
		panel = new JPanel();
		initialize();
		// make the window visible
		this.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}

 	public void addMainMenuButton(){
		panel.add(mainMenuButton);
		panel.repaint();
	}

	public void display(UserWithDietPlan user){
		clearPanel();
		if (user == null){
			JLabel notFoundL = new JLabel("User Not Found ");
			panel.add(notFoundL);
		} else {
			JLabel nameL = new JLabel(" Name: "+ user.getName());
			JLabel phoneL = new JLabel("Phone Number " + user.getPhoneNumber());
			JLabel idL = new JLabel("User id " + user.getUserId());
			JLabel planL = new JLabel("Plan id " + user.getDietPlanId());
			panel.add(idL);
			panel.add(nameL);
			panel.add(phoneL);
			panel.add(planL);
		}
		addMainMenuButton();
	}

	public void display(Trainer trainer){
		clearPanel();
		if (trainer == null){
			JLabel notFoundL = new JLabel("Trainer Not Found ");
			panel.add(notFoundL);
		} else {
			JLabel nameL = new JLabel(" Name: "+ trainer.getName());
			JLabel phoneL = new JLabel("Phone Number " + trainer.getPhoneNumber());
			JLabel idL = new JLabel("Trainer id " + trainer.getTrainerId());
			panel.add(idL);
			panel.add(nameL);
			panel.add(phoneL);
		}
		addMainMenuButton();
	}

	public void displayDietPlan(DietPlan dietPlan, String userName) {
		clearPanel();
		if (dietPlan == null){
			JLabel noPlan = new JLabel("Wrong user id or user doesn't have any diet plan.");
			panel.add(noPlan);
		} else {
			JLabel dietPlanName = new JLabel("Diet plan for user " + userName + ": " + dietPlan.getName());
			panel.add(dietPlanName);
		}
		addMainMenuButton();
	}
	public void display(ExercisePlanContainsExercise exercisePlan, String userName) {
		clearPanel();
		if (exercisePlan == null){
			JLabel noPlan = new JLabel("Wrong user id or user doesn't have any exercise plan.");
			panel.add(noPlan);
		} else {
			JLabel exercisePlanName = new JLabel("Exercise plan for user " + userName + ": " + exercisePlan.getName());
			panel.add(exercisePlanName);
		}
		addMainMenuButton();

	}

	public void displayUserCountByTrainer(int userCountByTrainer, String trainerName) {
		clearPanel();
		JLabel label = new JLabel("Numbers of user by trainer " + trainerName + ":" + userCountByTrainer);
		panel.add(label);
		addMainMenuButton();
	}

	public void clearPanel(){
		panel.removeAll();
		panel.repaint();
		panel.revalidate();
		panel.setLayout(new GridLayout());
	}
	public void operationSucceed() {
		clearPanel();
		JLabel failMsg = new JLabel("Operation Succeeded");
		panel.add(failMsg);
		panel.add(mainMenuButton);
	}

	public void operationFailed() {
		clearPanel();
		JLabel failMsg = new JLabel("Operation Failed");
		panel.add(failMsg);
		panel.add(mainMenuButton);
	}

	public void preparePanelForSearch(){
		clearPanel();
		panel.setLayout(new GridLayout());
		JLabel label = new JLabel("Please enter ID");
		panel.add(label);
	}

	private class addUserButtonClickerHandler implements ActionListener{
		int uId;
		String uName;
		String uPhone;
		int uDietPlan;
		JTextField idField;
		JTextField nameField;
		JTextField phoneNumField;
		JTextField dietPlanField;

		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			idField = new JTextField("Enter User ID");
			nameField = new JTextField("Enter name");
			phoneNumField = new JTextField("Enter phoneNumber");
			dietPlanField = new JTextField("Enter a diet plan ID");

			panel.add(idField);
			panel.add(nameField);
			panel.add(phoneNumField);
			panel.add(dietPlanField);
			clearTextFieldOnClick(idField);
			clearTextFieldOnClick(nameField);
			clearTextFieldOnClick(phoneNumField);
			clearTextFieldOnClick(dietPlanField);

			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}

		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					System.out.println("submit clicked");
					uId = Integer.parseInt(idField.getText());
					uName = nameField.getText();
					uPhone = phoneNumField.getText();
					uDietPlan = Integer.parseInt(dietPlanField.getText());
					delegate.addUser(uId,uName,uPhone,uDietPlan);
				}catch (NumberFormatException exception) {
					idField.setText("enter number only");
					dietPlanField.setText("enter number only");
				}
			}
		}
	}

	private class showTrainerAllUserButtonClickerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			delegate.getBusiestTrainer();
		}
	}

	private class searchUserButtonClickerHandler implements ActionListener{
		JTextField textField = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int userID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.showUserWithDPlan(userID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class deleteUserButtonClickerHandler implements ActionListener{
		JTextField textField = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int userID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.deleteUserWithDPlan(userID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class searchTrainerButtonClickerHandler implements ActionListener{
		JTextField textField = null;
		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int trainerID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.showTrainer(trainerID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class showDietPlanButtonClickerHandler implements ActionListener {
		JTextField textField = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new showDietPlanButtonClickerHandler.submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int userID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.showDietPlanByUser(userID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class updateDietPlanButtonClickerHandler implements ActionListener {
		JTextField userId;
		JTextField dietPlanId;
		int uid;
		int pid;
		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			ArrayList<String> dietPlans = delegate.getAllDietPlans();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("<html>");
			strBuilder.append("List of diet plans with ID:<br>");
			for (String dietPlan : dietPlans) {
				strBuilder.append(dietPlan);
				strBuilder.append("<br>");
			}
			strBuilder.append("</html>");
			panel.setLayout(new GridLayout());
			JLabel label = new JLabel(strBuilder.toString());
			userId = new JTextField("Enter user ID");

			dietPlanId = new JTextField("Enter diet plan ID");

			panel.add(label);
			panel.add(userId);
			panel.add(dietPlanId);
			clearTextFieldOnClick(userId);
			clearTextFieldOnClick(dietPlanId);

			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new updateDietPlanButtonClickerHandler.submitButtonClickerHandler());
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("submit clicked");
					uid = Integer.parseInt(userId.getText());
					pid = Integer.parseInt(dietPlanId.getText());
					delegate.updateUserDietPlan(uid, pid);
				} catch (NumberFormatException exception) {
					userId.setText("enter number only");
					dietPlanId.setText("enter number only");
				}
			}
		}
	}

	private class showExercisePlanButtonClickerHandler implements ActionListener {
		JTextField textField = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int userID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.showExercisePlanByUser(userID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class updateExercisePlanButtonClickerHandler implements ActionListener {
		JTextField userId;
		JTextField exercisePlanId;
		int uid;
		int pid;
		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			ArrayList<String> exercisePlans = delegate.getAllExercisePlans();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("<html>");
			strBuilder.append("List of diet plans with ID:<br>");
			for (String exercisePLan : exercisePlans) {
				strBuilder.append(exercisePLan);
				strBuilder.append("<br>");
			}
			strBuilder.append("</html>");
			panel.setLayout(new GridLayout());
			JLabel label = new JLabel(strBuilder.toString());
			userId = new JTextField("Enter user ID");

			exercisePlanId = new JTextField("Enter Exercise plan ID");

			panel.add(label);
			panel.add(userId);
			panel.add(exercisePlanId);
			clearTextFieldOnClick(userId);
			clearTextFieldOnClick(exercisePlanId);

			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("submit clicked");
					uid = Integer.parseInt(userId.getText());
					pid = Integer.parseInt(exercisePlanId.getText());
					delegate.updateUserExercisePlan(uid, pid);
				} catch (NumberFormatException exception) {
					userId.setText("enter number only");
					exercisePlanId.setText("enter number only");
				}
			}
		}
	}

	private void clearTextFieldOnClick(JTextField textField) {
		textField.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				textField.setText("");
			}
		});
	}

	private class updateUserButtonClickerHandler implements ActionListener{
		int uId;
		String uName;
		String uPhone;
		int uDietPlan;
		JTextField idField;
		JTextField nameField;
		JTextField phoneNumField;
		JTextField dietPlanField;

		@Override
		public void actionPerformed(ActionEvent e) {

			clearPanel();
			idField = new JTextField("Enter User ID");
			nameField = new JTextField("Enter name");
			phoneNumField = new JTextField("Enter phoneNumber");
			dietPlanField = new JTextField("Enter a diet plan ID");

			panel.add(idField);
			panel.add(nameField);
			panel.add(phoneNumField);
			panel.add(dietPlanField);
			clearTextFieldOnClick(idField);
			clearTextFieldOnClick(nameField);
			clearTextFieldOnClick(phoneNumField);
			clearTextFieldOnClick(dietPlanField);

			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					System.out.println("submit clicked");
					uId = Integer.parseInt(idField.getText());
					uName = nameField.getText();
					uPhone = phoneNumField.getText();
					uDietPlan = Integer.parseInt(dietPlanField.getText());
					delegate.updateUser(uId,uName,uPhone,uDietPlan);
				}catch (NumberFormatException exception) {
					idField.setText("enter number only");
					dietPlanField.setText("enter number only");
				}
			}
		}
	}
	private class updateTrainerButtonClickerHandler implements ActionListener{
		int tId;
		String tName;
		String tPhone;
		JTextField idField;
		JTextField nameField;
		JTextField phoneNumField;

		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			idField = new JTextField("Enter Trainer ID");
			nameField = new JTextField("Enter name");
			phoneNumField = new JTextField("Enter phoneNumber");

			panel.add(idField);
			panel.add(nameField);
			panel.add(phoneNumField);
			clearTextFieldOnClick(idField);
			clearTextFieldOnClick(nameField);
			clearTextFieldOnClick(phoneNumField);

			addMainMenuButton();
			JButton submitButton = new JButton("submit");
			submitButton.addActionListener(new submitButtonClickerHandler());
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}
		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					System.out.println("submit clicked");
					tId = Integer.parseInt(idField.getText());
					tName = nameField.getText();
					tPhone = phoneNumField.getText();
					delegate.updateTrainer(tName,tId,tPhone);
				}catch (NumberFormatException exception) {
					idField.setText("enter number only");
				}
			}
		}
	}

	private class countUserHandler implements ActionListener {
		int totalNumOfUser;
		JLabel label;
		@Override
		public void actionPerformed(ActionEvent e) {
			clearPanel();
			totalNumOfUser = delegate.countUser();
			label = new JLabel("Total number of users in our system: " + totalNumOfUser);
			panel.add(label);
			addMainMenuButton();
		}
	}

	private class countUserByTrainerHandler implements ActionListener {
		JTextField textField;
		JButton submitButton;
		@Override
		public void actionPerformed(ActionEvent e) {
			preparePanelForSearch();
			textField = new JTextField("");
			panel.add(textField);
			submitButton = new JButton("submit");
			submitButton.addActionListener(new countUserByTrainerHandler.submitButtonClickerHandler());
			addMainMenuButton();
			panel.add(submitButton);
			panel.repaint();
			addMainMenuButton();
		}

		private class submitButtonClickerHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int trainerID = Integer.parseInt(textField.getText());
					System.out.println("submit clicked");
					delegate.showUserCountByTrainer(trainerID);
				}catch (NumberFormatException exception) {
					textField.setText("enter number only");
				}
			}
		}
	}

	private class mainMenuClickerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("back clicked");
			clearPanel();
			initialize();
		}
	}
}
