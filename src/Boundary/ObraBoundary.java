package Boundary;

import Control.ObraControl;
import Entity.Obra;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
import javafx.util.converter.LongStringConverter;

public class ObraBoundary extends Application implements EventHandler<ActionEvent>{
		
	private TextField txtId = new TextField();
	private TextField txtTitulo = new TextField();
	private TextField txtDescricao = new TextField();
	private TextField txtPeriodo = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private ObraControl control = new ObraControl();
	private TableView<Obra> table = new TableView<>();
	
	@SuppressWarnings("unchecked")
	public void vincularCampos(){ 
		StringConverter<? extends Number> idConverter = new LongStringConverter();
		
		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);	
		Bindings.bindBidirectional(txtTitulo.textProperty(), control.getTituloProperty());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.getDescricaoProperty());
		Bindings.bindBidirectional(txtPeriodo.textProperty(), control.getPeriodoProperty());
		
		TableColumn<Obra, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Obra, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		
		TableColumn<Obra, String> colDescricao = new TableColumn<>("Descrição");
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		TableColumn<Obra, String> colPeriodo = new TableColumn<>("Periodo");
		colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
		
		table.getColumns().addAll(colId, colTitulo, colDescricao, colPeriodo);
		
		table.setItems( control.getObras() );
		
	}

	
	@Override
	public void start(Stage stage) throws Exception {
		vincularCampos();
		
		BorderPane bp = new BorderPane();
		Scene scn = new Scene(bp, 600, 200);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("Id"), 0, 0);
		paneCampos.add(txtId, 1, 0);
		
		paneCampos.add(new Label("Titulo"), 0, 1);
		paneCampos.add(txtTitulo, 1, 1);
		
		paneCampos.add(new Label("Descricao"), 0, 2);
		paneCampos.add(txtDescricao, 1, 2);
		
		paneCampos.add(new Label("Periodo"), 0, 3);
		paneCampos.add(txtPeriodo, 1, 3);
		
		paneCampos.add(btnAdicionar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		
		bp.setTop(paneCampos);
		bp.setCenter(table);
		
		stage.setScene(scn);
		stage.setTitle("Cadastro de Obra");
		stage.show();
	}
	
	
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) { 
			control.adicionar();
		} else if (e.getTarget() == btnPesquisar) { 
			control.pesquisarPorNome();
		}
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
	            textField.positionCaret(textField.getText().length());
	        }
	    });
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(ObraBoundary.class, args);
	}

}
