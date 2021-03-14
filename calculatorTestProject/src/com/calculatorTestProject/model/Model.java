package com.calculatorTestProject.model;

import java.util.ArrayList;

import com.calculatorTestProject.observers.Observable;
import com.calculatorTestProject.observers.Observer;

public class Model implements Observable {
	
	private int result;
	private ArrayList<Observer> calculateObservers;
		
	
	public Model() {
		this.calculateObservers = new ArrayList<Observer>();
	}
	
	public int getResult() {
		return result;
	}
	
	public void calculate(int number1, int number2, char method) {
		switch(method) {
		case '+':
			add(number1, number2);
			break;
		case '-':
			subtract(number1, number2);
			break;
		case '*':
			multiply(number1, number2);
			break;
		}
		notifyObservers();
	}
	
	public void multiply(int number1, int number2) {
		this.result = number1 * number2;
	}
	
	public void add(int number1, int number2) {
		this.result = number1 + number2;
	}
	
	public void subtract(int number1, int number2) {
		this.result = number1 - number2;
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
}
