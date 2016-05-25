package test;


public class Question {
	String question;
	String answers[];
	int rightAnswer;
	int id;
	public Question(String q, String ans[], int num){
		question = q;
		answers = ans;
		for(int i = 0; i < 4; i++){
			if(answers[i].charAt(0)=='-'){
				rightAnswer = i + 1;
				answers[i] = answers[i].substring(1, answers[i].length());
			}
		}
		id = num;
	}
	
	public int ans(){
		return rightAnswer;
	}
	
	public String quest(){
		return question;
	}
	public String[] answer(){
		return answers;
	}
}
