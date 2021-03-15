package com.calculatorTestProject.view;

import java.util.ArrayList;

import com.calculatorTestProject.observers.Observable;
import com.calculatorTestProject.observers.Observer;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class View implements Observable {

	private StackPane stackPane1;
	private Scene scene1;
	private ArrayList<Button> buttons;
	private Button calculateButton;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button14;
	private Button button15;
	private Button button16;
	private Button button17;
	private Text number1Text;
	private Text number2Text;
	private Text resultText;
	private Text outputText;
	private ArrayList<Observer> calculateObservers;
	private int result;
	private int number1;
	private int number2;
	private char method;
	private Rectangle screen1;

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
		outputText = new Text();
		outputText.setTranslateX(45);
		outputText.setTranslateY(-130);
		outputText.setFont(Font.font(15));
		outputText.setTextAlignment(TextAlignment.RIGHT);

		buttons = new ArrayList<Button>();
		fillButtonArray();
		calculateButton = buttons.get(13);
		calculateButton.setText("=");
		calculateButton.setMinSize(105, 50);
		calculateButton.setTranslateX(82.5);
		calculateButton.setTranslateY(90);
		calculateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new CalculateHandler());
		button10 = buttons.get(10);
		button10.setText("+");
		button10.setMinSize(50, 50);
		button10.setTranslateX(55);
		button10.setTranslateY(35);
		button10.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddHandler());
		button11 = buttons.get(11);
		button11.setText("-");
		button11.setMinSize(50, 50);
		button11.setTranslateX(55);
		button11.setTranslateY(-20);
		button11.addEventHandler(MouseEvent.MOUSE_CLICKED, new SubtractHandler());
		button12 = buttons.get(12);
		button12.setText("x");
		button12.setMinSize(50, 50);
		button12.setTranslateX(55);
		button12.setTranslateY(-75);
		button12.addEventHandler(MouseEvent.MOUSE_CLICKED, new MultiplyHandler());
		button14 = buttons.get(14);
		button14.setText(",");
		button14.setMinSize(50, 50);
		button14.setTranslateX(0);
		button14.setTranslateY(90);
		// button14.addEventHandler(MouseEvent.MOUSE_CLICKED, new CalculateHandler());
		button15 = buttons.get(15);
		button15.setText("/");
		button15.setMinSize(50, 50);
		button15.setTranslateX(110);
		button15.setTranslateY(35);
		button15.addEventHandler(MouseEvent.MOUSE_CLICKED, new DivideHandler());
		button16 = buttons.get(16);
		button16.setText("^2");
		button16.setMinSize(50, 50);
		button16.setTranslateX(110);
		button16.setTranslateY(-20);
		button16.addEventHandler(MouseEvent.MOUSE_CLICKED, new ExponentHandler());
		button17 = buttons.get(17);
		button17.setText("<");
		button17.setMinSize(50, 50);
		button17.setTranslateX(110);
		button17.setTranslateY(-75);
		button17.addEventHandler(MouseEvent.MOUSE_CLICKED, new BackspaceHandler());
		initializeNumberButtons();

		screen1 = new Rectangle(200, 50);
		screen1.setTranslateY(-140);
		screen1.setFill(Color.WHITE);
		screen1.setStroke(Color.DARKGRAY);

		stackPane1 = new StackPane();
		stackPane1.getChildren().add(calculateButton);
		stackPane1.getChildren().add(button10);
		stackPane1.getChildren().add(button11);
		stackPane1.getChildren().add(button12);
		stackPane1.getChildren().add(button14);
		stackPane1.getChildren().add(button15);
		stackPane1.getChildren().add(button16);
		stackPane1.getChildren().add(button17);
		addNumberButtons();
		stackPane1.getChildren().add(screen1);
//		stackPane1.getChildren().add(number1Text);
//		stackPane1.getChildren().add(number2Text);
//		stackPane1.getChildren().add(resultText);
		stackPane1.getChildren().add(outputText);

		scene1 = new Scene(stackPane1, 350, 400);
	}

	private void addNumberButtons() {
		for (int i = 0; i < 10; i++) {
			stackPane1.getChildren().add(buttons.get(i));
		}
	}

	private void initializeNumberButtons() {
		int x = 0;
		int y = -75;
		for (int i = 9; i >= 0; i--) {
			buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new NumberHandler(i));
			buttons.get(i).setText(Integer.toString(i));
			buttons.get(i).setMinSize(50, 50);
			buttons.get(i).setTranslateX(x);
			buttons.get(i).setTranslateY(y);
			if (i == 1) {
				x += 55;
				y += 55;
			} else if (i % 3 == 1) {
				x = 0;
				y += 55;

			} else {
				x -= 55;
			}
		}
	}

	private void fillButtonArray() {
		for (int i = 0; i < 20; i++) {
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
		if (method == '\u0000') {
			//resetText();
			number1 *= 10;
			number1 += number;
			updateText(false);
		} else {
			number2 *= 10;
			number2 += number;
			updateText(false);
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
		if(number1 != 0 && number2 != 0) {
		resultText.setText(" = " + Integer.toString(result));
		}
		else { 
			resultText.setText("");
		}
	}

	public void resetText() {
		number2Text.setText("");
		resultText.setText("");
	}

	public void setNumberResult(int number) {
		if (number2 == 0) {
			number1 = number;
			number1Text.setText(Integer.toString(number));
		} else {
			number2 = number;
			number2Text.setText(Integer.toString(number));
		}
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
	
	public void updateText(boolean updateResultFlag) {
		if(number1 != 0 && method == '\u0000') {
		number1Text.setText(Integer.toString(number1));
		}
		if(number1 != 0 && method != '\u0000') {
			if(method != '*') {
				number1Text.setText(Integer.toString(number1) + " " + method);
				}
			else {
				number1Text.setText(Integer.toString(number1) + " x ");
			}
		}
		if(number2 != 0) {
		number2Text.setText(Integer.toString(number2));
		}
		if(number1 == 0) {
			number1Text.setText("");
		}
		if(number2 == 0) {
			number2Text.setText("");
		}
		if(updateResultFlag == true) {
			updateResultText();
		}
		outputText.setText(number1Text.getText() + " " + number2Text.getText() + resultText.getText());
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public int getResult() {
		return this.result;
	}

	class CalculateHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			if(getNumber1() == 0 & getNumber2() == 0) {
				setNumber1(getResult());
			}
			notifyObservers();
			updateText(true);
			setMethod('\u0000');
			setNumber1(0);
			setNumber2(0);
		}
	}

	class AddHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			if(getMethod() == '\u0000') {
				setMethod('+');
				if(getNumber1() == 0 & getNumber2() == 0) {
					setNumber1(getResult());
					result = 0;
				}
				number1Text.setText(Integer.toString(number1) + " " + method);
				updateText(true);
			}
		}
	}

	class SubtractHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			if(getMethod() == '\u0000') {
				setMethod('-');
				if(getNumber1() == 0 & getNumber2() == 0) {
					setNumber1(getResult());
					result = 0;
				}
				number1Text.setText(Integer.toString(number1) + " " + method);
				updateText(true);
			}
		}
	}

	class MultiplyHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			if(getMethod() == '\u0000') {
				setMethod('*');
				if(getNumber1() == 0 & getNumber2() == 0) {
					setNumber1(getResult());
					result = 0;
				}
				number1Text.setText(Integer.toString(number1) + " x");
				updateText(true);
			}			
		}
	}

	class DivideHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			if(getMethod() == '\u0000') {
				System.out.println(method);
				setMethod('/');
				if(getNumber1() == 0 & getNumber2() == 0) {
					setNumber1(getResult());
					result = 0;
				}
				number1Text.setText(Integer.toString(number1) + " /");
				updateText(true);
			}			
		}
	}
	
	class BackspaceHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			String numberString;
			if(getNumber2() == 0 & getMethod() != '\u0000') {
				setMethod('\u0000');
			}
			else if(getNumber2() == 0 & getNumber1() != 0) {
				numberString = String.valueOf(number1);
				numberString = numberString.substring(0, numberString.length() - 1);
				if(numberString.contentEquals("")) {
					numberString = "0";
				}
				setNumber1(Integer.parseInt(numberString));
			}
			else if(getNumber2() != 0) {
				numberString = String.valueOf(number2);
				numberString = numberString.substring(0, numberString.length() - 1);
				if(numberString.contentEquals("")) {
					numberString = "0";
				}
				setNumber2(Integer.parseInt(numberString));
			}
			else {
				updateText(true);
			}
			updateText(false);			
		}
	}

	class ExponentHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent arg0) {
			char previousMethod = getMethod();
			setMethod('^');
			if(getNumber1() == 0 & getNumber2() == 0) {
				setNumber1(getResult());
				result = 0;
				updateText(true);
			}
			notifyObservers();
			setMethod(previousMethod);
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
