package application;
	

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	public static ListView<register> registers;
	static TextArea TextArea;
	static TextArea TextArea2;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
		
			/////////////selection box for registers///////////////////////
			ListView<register> registers = new ListView<>();
			ListView<register> registers2 = new ListView<>();
			registers.setMaxWidth(70);
			registers2.setMaxWidth(70);
		
			
			HBox selection0 = new HBox();
			selection0.setStyle("-fx-padding: 10;" + 
	                  "-fx-border-style: solid inside;" + 
	                  "-fx-border-width: 2;" +
	                  "-fx-border-insets: 5;" + 
	                  "-fx-border-radius: 5;" + 
	                  "-fx-border-color: BlueViolet;");
			
			
			
		
			
			selection0.getChildren().addAll( registers,registers2);
			
			ArrayList<register> registersar = new ArrayList<register>();
         
        	
			ArrayList<register> registersar2 = new ArrayList<register>();
            registersar.add(new register("$s0"));
            registersar.add(new register("$s1"));
            registersar.add(new register("$s2"));
            registersar.add(new register("$s3"));
            registersar.add(new register("$s4"));
            registersar.add(new register("$s5"));
            registersar.add(new register("$t0"));
            registersar.add(new register("$t1"));
            registersar.add(new register("$t2"));
            registersar.add(new register("$ra"));
            registersar.add(new register("$sp"));
            registersar.add(new register("$v0"));
            registersar.add(new register("$v1"));
            registersar.add(new register("$a0"));
            registersar.add(new register("$a1"));
            registersar.add(new register("$a2"));
            registersar.add(new register("$pc"));
            
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
            registersar2.add(new register("0"));
          
            
			
			registers.getItems().addAll(registersar);
			registers2.getItems().addAll(registersar2);
			
			////////////////////////////////////////////////////

			
			/////////////////////////////////////////////////////////////////////////////////////
			
			HBox selection2 = new HBox();

			TextArea = new TextArea();
			
			TextArea.setStyle("-fx-padding: 10;" + 
	                  "-fx-border-style: solid inside;" + 
	                  "-fx-border-width: 2;" +
	                  "-fx-border-insets: 5;" + 
	                  "-fx-border-radius: 5;" + 
	                  "-fx-border-color: BlueViolet;"
	                  + "-fx-control-inner-background: LightSalmon; -fx-font-size:16px;-fx-font-weight:bold;");
			
			TextArea.setPrefHeight(700);
			TextArea.setPrefWidth(1000);
			selection2.getChildren().addAll( TextArea,selection0);
			
			//////////////////////////////////////////////////////////////////////////////////////
			Button runButton = new Button();
			runButton.setStyle("-fx-background-size: 22px; -fx-background-repeat: no-repeat;-fx-background-image: url('run.jpg');");
			HBox header = new HBox(runButton);
			header.setStyle("-fx-border-style: solid inside;   -fx-background-color: #ffffff;\n" + "    -fx-spacing: 10;");
			
			VBox selection3 = new VBox();
			TextArea2 = new TextArea();
			TextArea2.setStyle("-fx-padding: 10;" + 
	                  "-fx-border-style: solid inside;" + 
	                  "-fx-border-width: 2;" +
	                  "-fx-border-insets: 5;" + 
	                  "-fx-border-radius: 5;" + 
	                  "-fx-border-color: BlueViolet;"
	                  + "-fx-control-inner-background: LightSlateGray	; -fx-font-size:16px;-fx-font-weight:bold;");
			TextArea2.setPrefHeight(200);
			TextArea2.setPrefWidth(1000);
			selection3.getChildren().addAll(header ,selection2, TextArea2);
			
			
			Scene scene2 = new Scene(selection3);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene2);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public class registerCellFactory implements Callback<ListView<register>, ListCell<register>> {
		@Override
		public ListCell<register> call(ListView<register> listview) {
			return new registerCell();
		}
	}
	
	public class registerCell extends ListCell<register> {
		@Override
		public void updateItem(register item, boolean empty) {
			super.updateItem(item, empty);

			int index = this.getIndex();
			String name = null;
			
		}
	}
	
	public static class register {
		// Declaring the attributes
		private String Name;

		public register(String Name) {
			this.Name = Name;

		}

		public String getFirstName() {
			return Name;
		}

		public void setFirstName(String firstName) {
			this.Name = Name;
		}

		@Override
		public String toString() {
			return Name;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
