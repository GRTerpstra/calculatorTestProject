package com.calculatorTestProject.view;

import java.util.ArrayList;

import com.calculatorTestProject.observers.Observable;
import com.calculatorTestProject.observers.Observer;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class View implements Observable {

	private StackPane stackPane1;
	private Scene scene1;
	private Button button1;
	private Text text1;
	private Text text2;
	private TextField textField1;
	private TextField textField2;
	private ArrayList<Observer> calculateObservers;
	private int result;
	private int number1;
	private int number2;

	public View() {
		this.calculateObservers = new ArrayList<Observer>();

		text1 = new Text();
		text1.setText("Make your calculation below.");
		text1.setTranslateY(-50);
		text2 = new Text();
		text2.setTranslateY(80);

		textField1 = new TextField();
		textField1.setTranslateY(0);
		textField2 = new TextField();
		textField2.setTranslateY(20);

		button1 = new Button();
		button1.setText("Calculate");
		button1.setTranslateY(50);
		button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonHandler());

		stackPane1 = new StackPane();
		stackPane1.getChildren().add(button1);
		stackPane1.getChildren().add(text1);
		stackPane1.getChildren().add(text2);
		stackPane1.getChildren().add(textField1);
		stackPane1.getChildren().add(textField2);

		scene1 = new Scene(stackPane1, 500, 300);
	}

	public Scene getScene() {
		return this.scene1;
	}
	
	public int getNumber1() {
		return number1;
	}
	
	public int getNumber2() {
		return number2;
	}
	
	public void setText2(String string) {
		this.text2.setText((string));
	}
	
	public void setNumber1() {
		number1 = Integer.parseInt(textField1.getText());
	}
	
	public void setNumber2() {
		number2 = Integer.parseInt(textField2.getText());
	}

	@Override
	public void registerObserver(Observer observer) {
		calculateObservers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		calculateObservers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : calculateObservers) {
			observer.update();
		}
	}

	public void setResult(int result) {
		this.result = result;
	}

	class ButtonHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			setNumber1();
			setNumber2();
			notifyObservers();
		}
	}
}
