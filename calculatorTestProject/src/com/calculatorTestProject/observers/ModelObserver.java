package com.calculatorTestProject.observers;

import com.calculatorTestProject.controller.Controller;
import com.calculatorTestProject.model.Model;

public class ModelObserver implements Observer {
	
	private Controller controller;
	private Model model;
	
	public ModelObserver(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
	}
	
	@Override
	public void update() {	
		this.controller.getView().setResult(model.getResult());
		this.controller.getView().updateResultText();
	}
}
