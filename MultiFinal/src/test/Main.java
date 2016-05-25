package test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Main extends Application {
	int num;
	ArrayList<String> file = new ArrayList<String>();
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<Question> test= new ArrayList<Question>();
	int questionsNo = 1;
	int correct;
	RadioButton ans1;
	RadioButton ans2;
	RadioButton ans3;
	RadioButton ans4;
	Button submit;
	Label result;
	Label quest;
	Question currentQuestion;
	ToggleGroup group = new ToggleGroup();
	Stage stage;
	Stage pop = new Stage();
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		setGame();
		run();
	}
	
	public void newQuestion(){
		questionsNo += 1;
		currentQuestion = test.get(getQ());
		quest.setText(currentQuestion.quest());
		String temp[] = currentQuestion.answer();
		ans1.setText(temp[0]);
		ans2.setText(temp[1]);
		ans3.setText(temp[2]);
		ans4.setText(temp[3]);
	}
	
	public void run() throws IOException{
		final String fileName = "questions.txt";
		try{
			Scanner output = new Scanner(new FileReader(fileName));
			while(output.hasNextLine()){
				num+=1;
				String question = output.nextLine();
				String ans[] = new String[4];
				ans[0]= output.nextLine();;
				ans[1]= output.nextLine();;
				ans[2]= output.nextLine();;
				ans[3]= output.nextLine();;
				test.add(new Question(question, ans, num));
			}
				
			output.close();
			currentQuestion = test.get(getQ());
			quest.setText(currentQuestion.quest());
			String temp[] = currentQuestion.answer();
			ans1.setText(temp[0]);
			ans2.setText(temp[1]);
			ans3.setText(temp[2]);
			ans4.setText(temp[3]);
		}
		catch(Exception e){
			e.printStackTrace();
			PrintWriter printer = new PrintWriter(new File(fileName));
			setPop("catch");
			printer.write("hi");
		}
	}
	
	public int getQ(){
		Random rand = new Random();
		int i = rand.nextInt(test.size());
		while(ids.contains(i)){
			i = rand.nextInt(test.size());
		}
		ids.add(i);
		return i;
	}
	
	public void thing(String hold){
		file.add(hold);
	}
	
	public void setGame(){
		try {
			BorderPane pane =  FXMLLoader.load(getClass().getClassLoader().getResource("Disp.fxml"));
			scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			ans1 = (RadioButton) scene.lookup("#ans1");
			ans1.setToggleGroup(group);
			ans1.setWrapText(true);
			ans2 = (RadioButton) scene.lookup("#ans2");
			ans2.setToggleGroup(group);
			ans2.setWrapText(true);
			ans3 = (RadioButton) scene.lookup("#ans3");
			ans3.setToggleGroup(group);
			ans3.setWrapText(true);
			ans4 = (RadioButton) scene.lookup("#ans4");
			ans4.setToggleGroup(group);
			ans4.setWrapText(true);
			submit = (Button) scene.lookup("#submit");
			result = (Label) scene.lookup("#result");
			result.setWrapText(true);
			quest = (Label) scene.lookup("#question");
			quest.setWrapText(true);
			submit.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	String temp;
	               if(ans1.isSelected()){
	            	   if(currentQuestion.ans() == 1){
	            		  correct += 1;
	            		  temp = "Correct! \n" + correct + "/" + questionsNo;
	            		  try {
							setPop(temp);
						} catch (IOException e) {
							e.printStackTrace();
						}
	            	   }
	            	   else{
	            		   try {
							setPop(currentQuestion.quest() + ", the answer was: " + (currentQuestion.answer()[currentQuestion.ans()-1])+ "\n" + correct + "/" + questionsNo);
						} catch (IOException e) {
							e.printStackTrace();
						}  
	            	   }
	               }
	               if(ans2.isSelected()){
	            	   if(currentQuestion.ans() == 2){
	            		   correct += 1;
		            		  temp = "Correct! \n" + correct + "/" + questionsNo;
		            		  try {
								setPop(temp);
							} catch (IOException e) {
								e.printStackTrace();
							}
	            	   }
	            	   else{
	            		   try {
							setPop(currentQuestion.quest() + ", the answer was: " + (currentQuestion.answer()[currentQuestion.ans()-1])+ "\n" + correct + "/" + questionsNo);
						} catch (IOException e) {
							e.printStackTrace();
						}  
	            	   }
	               }
	               if(ans3.isSelected()){
	            	   if(currentQuestion.ans() == 3){
	            		   correct += 1;
		            		  temp = "Correct! \n" + correct + "/" + questionsNo;
		            		  try {
								setPop(temp);
							} catch (IOException e) {
								e.printStackTrace();
							}
	            	   }
	            	   else{
	            		   try {
							setPop(currentQuestion.quest() + ", the answer was: " + (currentQuestion.answer()[currentQuestion.ans()-1])+ "\n" + correct + "/" + questionsNo);
						} catch (IOException e) {
							e.printStackTrace();
						}  
	            	   }
	               }
	               if(ans4.isSelected()){
	            	   if(currentQuestion.ans() == 4){
	            		   correct += 1;
		            		  temp = "Correct! \n" + correct + "/" + questionsNo;
		            		  try {
								setPop(temp);
							} catch (IOException e) {
								e.printStackTrace();
							}
	            	   }
	            	   else{
	            		   try {
							setPop(currentQuestion.quest() + ", the answer was: " + (currentQuestion.answer()[currentQuestion.ans()-1])+ "\n" + correct + "/" + questionsNo);
						} catch (IOException e) {
							e.printStackTrace();
						}  
	            	   }
	               }
	               result.setText(correct + "/" + questionsNo);
	               newQuestion();
	            }
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setPop(String message) throws IOException{
		VBox show = FXMLLoader.load(getClass().getClassLoader().getResource("cfds.fxml"));
		Scene popScene = new Scene(show);
		pop.setScene(popScene);
		pop.show();
		Button accept = (Button) popScene.lookup("#accept");
		Label res = (Label) popScene.lookup("#resultLabel");
		res.setText(message);
		accept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				pop.hide();
			}
		});
	}
}


