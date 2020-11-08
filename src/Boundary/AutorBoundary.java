package Boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Control.AutorControl;
import Entity.Autor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LongStringConverter;

public class AutorBoundary extends Application implements EventHandler<ActionEvent>{
		
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtNacionalidade = new TextField();
	private TextField txtNascimento = new TextField();
	private TextField txtFalecimento = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private AutorControl control = new AutorControl();
	private TableView<Autor> table = new TableView<>();
	
	@SuppressWarnings("unchecked")
	public void vincularCampos(){ 
		StringConverter<? extends Number> idConverter = new LongStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);	
		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
		Bindings.bindBidirectional(txtNacionalidade.textProperty(), control.getNacionalidadeProperty());
		Bindings.bindBidirectional(txtNascimento.textProperty(), control.getNascimentoProperty(), dateConverter);
		Bindings.bindBidirectional(txtFalecimento.textProperty(), control.getFalecimentoProperty(), dateConverter);
		
		TableColumn<Autor, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Autor, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Autor, String> colNacionalidade = new TableColumn<>("Nacionalidade");
		colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
		
		TableColumn<Autor, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getNascimento().format(dtf));}
				);
		
		TableColumn<Autor, String> colFalecimento = new TableColumn<>("Nascimento");
		colFalecimento.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getFalecimento().format(dtf));}
				);
		
		table.getColumns().addAll(colId, colNome, colNacionalidade, colNascimento, colFalecimento);
		
		table.setItems( control.getAutores() );
		
	}

	
	
	@Override
	public void start(Stage stage) throws Exception {
		vincularCampos();
		dateField(txtNascimento);
		BorderPane bp = new BorderPane();
		Scene scn = new Scene(bp, 600, 200);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("Id"), 0, 0);
		paneCampos.add(txtId, 1, 0);
		
		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);
		
		paneCampos.add(new Label("Nacionalidade"), 0, 2);
		paneCampos.add(txtNacionalidade, 1, 2);
		
		paneCampos.add(new Label("Nascimento"), 0, 3);
		paneCampos.add(txtNascimento, 1, 3);
		
		paneCampos.add(new Label("Falecimento"), 0, 4);
		paneCampos.add(txtFalecimento, 1, 4);
		
		paneCampos.add(btnAdicionar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		
		bp.setTop(paneCampos);
		bp.setCenter(table);
		
		stage.setScene(scn);
		stage.setTitle("Cadastro de Autor");
		stage.show();
	}
	
	
	
	@Override
	public void handle(ActionEvent arg0) {
		
	}
	
	private static void maxField(final TextField textField, final Integer length) {
	    textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	            if (newValue.length() > length)
	                textField.setText(oldValue);
	        }
	    });
	}
	
	public static void dateField(final TextField textField) {
	    maxField(textField, 10);

	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() < 11) {
	                String value = textField.getText();
	                value = value.replaceAll("[^0-9]", "");
	                value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
	                textField.setText(value);
	                positionCaret(textField);
	            }
	        }
	    });
	}
	
	private static void positionCaret(final TextField textField) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	            // Posiciona o cursor sempre a direita.
	            textField.positionCaret(textField.getText().length());
	        }
	    });
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(AutorBoundary.class, args);
	}

}
