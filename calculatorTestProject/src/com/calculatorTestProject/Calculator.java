package com.calculatorTestProject;

import com.calculatorTestProject.controller.Controller;
import com.calculatorTestProject.model.Model;
import com.calculatorTestProject.view.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class Calculator extends Application {

	private Model model1;
	private View view1;
	private Controller controller1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage1) throws Exception {
		view1 = new View();
		model1 = new Model();
		controller1 = new Controller(model1, view1);
		stage1.setTitle("Title of Stage 1");
		stage1.setScene(view1.getScene());
		stage1.show();
	}
}
