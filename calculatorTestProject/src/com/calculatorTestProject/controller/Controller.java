package com.calculatorTestProject.controller;

import com.calculatorTestProject.model.Model;
import com.calculatorTestProject.observers.ModelObserver;
import com.calculatorTestProject.observers.Observer;
import com.calculatorTestProject.observers.ViewObserver;
import com.calculatorTestProject.view.View;

public class Controller {

	private Model model1;
	private View view1;
	private Observer viewObserver;
	private Observer modelObserver;

	public Controller(Model model, View view) {
		this.model1 = model;
		this.view1 = view;
		viewObserver = new ViewObserver(this, this.view1);
		modelObserver = new ModelObserver(this, this.model1);
		view1.registerObserver(viewObserver);
		model1.registerObserver(modelObserver);
	}

	public Model getModel() {
		return this.model1;
	}

	public View getView() {
		return this.view1;
	}
}
