package com.calculatorTestProject.observers;

import com.calculatorTestProject.controller.Controller;
import com.calculatorTestProject.view.View;

public class ViewObserver implements Observer {

	private View view;
	private Controller controller;
	
	public ViewObserver(Controller controller, View view) {
		this.view = view;
		this.controller = controller;
	}
	
	@Override
	public void update() {
		controller.getModel().setNumber1(view.getNumber1());
		controller.getModel().setNumber2(view.getNumber2());
		controller.getModel().calculate();
	}
}
