package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	
	private Label label;
	private Button button;
	private TextField text;
	private Alert alert;
	private int number;
	private int random;
	private int count;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		random = randomNumber();
		
		label = new Label();
		label.setText("Introduce un número del 1 al 100");
		
		text = new TextField();
		text.setPrefColumnCount(5);
		text.setMaxWidth(100);
		text.setAlignment(Pos.CENTER);
		
		button = new Button();
		button.setText("Comprobar");
		button.setDefaultButton(true);
		button.setOnAction(e -> checkNumber(e));
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label, text, button);
		
		Scene scene = new Scene(root, 320, 200);
		
		stage.setScene(scene);
		stage.setTitle("AdivinApp");
		stage.show();
	}
	
	private int randomNumber() {
		return (int)(Math.random() * 100 + 1);
	}
	
	private void checkNumber(ActionEvent e) {
		
		try
		{		
			number = Integer.parseInt(text.getText());
			
			count++;
			
			if(number == random)
			{
				alertType("info");
				random = randomNumber();
			}
			else
			{
				alertType("warning");
			}
		}
		catch(Exception ex)
		{
			alertType("error");
		}
	}
	
	private void alertType(String type)
	{	
		if(type.equals("info"))
		{
			alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("¡Has ganado!");
			alert.setContentText("Sólo has necesitado " + count + " intentos. \n\n Vuelve a jugar y hazlo mejor.");
		}
		else if(type.equals("error"))
		{
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido");
		}
		else if(type.equals("warning"))
		{
			alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("¡Has fallado!");
			
			if(random > number)
				alert.setContentText("El número a adivinar es mayor que " + number + ". \n\n Vuelve a intentarlo.");
			else
				alert.setContentText("El número a adivinar es menor que " + number + ". \n\n Vuelve a intentarlo.");
		}
		
		alert.setTitle("AdivinApp");
		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
