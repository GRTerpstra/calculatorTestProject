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
	private ArrayList<Button> buttons;
	private Button calculateButton;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button14;
	private Text number1Text;
	private Text number2Text;
	private Text resultText;
	private ArrayList<Observer> calculateObservers;
	private int result;
	private int number1;
	private int number2;
	private char method;

	public View() {
		calculateObservers = new ArrayList<Observer>();

		number1Text = new Text();
		number1Text.setTranslateX(-10);
		number1Text.setTranslateY(-120);
		number2Text = new Text();
		number2Text.setTranslateX(25);
		number2Text.setTranslateY(-120);
		resultText = new Text();
		resultText.setTranslateX(60);
		resultText.setTranslateY(-120);

		buttons = new ArrayList<Button>();
		fillButtonArray();
		calculateButton = buttons.get(13);
		calculateButton.setText("=");
		calculateButton.setMinSize(50, 50);
		calculateButton.setTranslateX(110);
		calculateButton.setTranslateY(90);
		calculateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new CalculateHandler());
		button10 = buttons.get(10);
		button10.setText("+");
		button10.setMinSize(50, 50);
		button10.setTranslateX(110);
		button10.setTranslateY(35);
		button10.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddHandler());
		button11 = buttons.get(11);
		button11.setText("-");
		button11.setMinSize(50, 50);
		button11.setTranslateX(110);
		button11.setTranslateY(-20);
		button11.addEventHandler(MouseEvent.MOUSE_CLICKED, new SubtractHandler());
		button12 = buttons.get(12);
		button12.setText("x");
		button12.setMinSize(50, 50);
		button12.setTranslateX(110);
		button12.setTranslateY(-75);
		button12.addEventHandler(MouseEvent.MOUSE_CLICKED, new MultiplyHandler());
		button14 = buttons.get(14);
		button14.setText(",");
		button14.setMinSize(50, 50);
		button14.setTranslateX(55);
		button14.setTranslateY(90);
		button14.addEventHandler(MouseEvent.MOUSE_CLICKED, new CalculateHandler());
		initializeNumberButtons();

		stackPane1 = new StackPane();
		stackPane1.getChildren().add(calculateButton);
		stackPane1.getChildren().add(button10);
		stackPane1.getChildren().add(button11);
		stackPane1.getChildren().add(button12);
		stackPane1.getChildren().add(button14);
		addNumberButtons();
		stackPane1.getChildren().add(number1Text);
		stackPane1.getChildren().add(number2Text);
		stackPane1.getChildren().add(resultText);

		scene1 = new Scene(stackPane1, 500, 400);
	}
	
	private void addNumberButtons() {
		for(int i = 0; i < 10; i++) {
			stackPane1.getChildren().add(buttons.get(i));
		}
	}

	private void initializeNumberButtons() {
		int x = 55;
		int y = -75;
		for(int i = 9; i >= 0; i--) {
			buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new NumberHandler(i));
			buttons.get(i).setText(Integer.toString(i));
			buttons.get(i).setMinSize(50, 50);
			buttons.get(i).setTranslateX(x);
			buttons.get(i).setTranslateY(y);
			if(i == 1) {
				x += 55;
				y += 55;
				System.out.println(i);
			}
			else if(i % 3 == 1) {
				x = 55;
				y += 55;
				
			}
			else { 
				x -= 55;
			}
		}
	}

	private void fillButtonArray() {
		for(int i = 0; i < 20; i++) {
			buttons.add(i, new Button());
		}
		
	}

	public Scene getScene() {
		return this.scene1;
	}
	
	public void setMethod(char method) {
		this.method = method;
	}
	
	public char getMethod() {
		return method;
	}
	
	public void addNumber(int number) {
		if(method == '\u0000') {
			updateText();
			number1 *= 10;
			number1 += number;
			number1Text.setText(Integer.toString(number1));
		}
		else {
			number2 *= 10;
			number2 += number;
			number2Text.setText(Integer.toString(number2));
		}
	}
	
	public int getNumber1() {
		return number1;
	}
	
	public int getNumber2() {
		return number2;
	}
	
	public void setNumber1(int number) {
		this.number1 = number;
	}
	
	public void setNumber2(int number) {
		this.number2 = number;
	}
	
	public void updateResultText() {
		resultText.setText(" = " + Integer.toString(result));
	}
	
	public void updateText() {
		number2Text.setText("");
		resultText.setText("");
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

	class CalculateHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			notifyObservers();
			setMethod('\u0000');
			setNumber1(0);
			setNumber2(0);
		}
	}
	
	class AddHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			setMethod('+');
			number1Text.setText(Integer.toString(number1) + " " + method);
		}
	}
	
	class SubtractHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			setMethod('-');
			number1Text.setText(Integer.toString(number1) + " " + method);
		}
	}
	
	class MultiplyHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			setMethod('*');
			number1Text.setText(Integer.toString(number1) + " " + method);
		}
	}
	
	class NumberHandler implements EventHandler<MouseEvent> {
		
		int number;
		
		NumberHandler(int number) {
			this.number = number;
		}
		@Override
		public void handle(MouseEvent arg0) {
			addNumber(number);

		}
	}
}
