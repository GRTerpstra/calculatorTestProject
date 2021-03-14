package com.calculatorTestProject.model;

import java.util.ArrayList;

import com.calculatorTestProject.observers.Observable;
import com.calculatorTestProject.observers.Observer;

public class Model implements Observable {
	
	private int number1;
	private int number2;
	private int result;
	private ArrayList<Observer> calculateObservers;
		
	
	public Model() {
		this.calculateObservers = new ArrayList<Observer>();
	}
	
	public void setNumber1(int number) {
		this.number1 = number;
	}
	public void setNumber2(int number) {
		this.number2 = number;
	}
	
	public String getResultString() {
		return Integer.toString(result);
	}
	
	public void calculate() {
		this.result = this.number1 * this.number2;
		notifyObservers();
	}
	
	public void multiply() {
		this.result = this.number1 * this.number2;
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
