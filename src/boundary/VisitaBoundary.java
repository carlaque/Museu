package boundary;

import control.VisitaControl;
import entity.Visitante;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VisitaBoundary extends Application implements EventHandler<ActionEvent> {
	
	private TextField txtId = new TextField();
	private TextField txtVisitante = new TextField();
	private TextField txtData = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private VisitaControl control = new VisitaControl();
	private TableView<Visitante> table = new TableView<>();
	
//	@SuppressWarnings("unchecked")
//	public void vincularCampos() {
//
//		StringConverter<? extends Number> idConverter = new LongStringConverter();
//		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		
//		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
//		Bindings.bindBidirectional(txtVisitante.textProperty(), control.getVisitanteProperty());
//		Bindings.bindBidirectional(txtData.textProperty(), control.getDataProperty(), dateConverter);
//		
//		TableColumn<Visitante, Long> colId = new TableColumn<>("ID");
//		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		
//		TableColumn<Visitante, String> colNome = new TableColumn<>("NOME");
//		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		
//		TableColumn<Visitante, String> colNascimento = new TableColumn<>("Nascimento");
//		colNascimento.setCellValueFactory(
//				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getNascimento().format(dtf));
//		});
//		
//		table.getColumns().addAll(colCpf, colNome, colNascimento);
//		
//		table.setItems(control.getVisitas());
//	}
//	
	@Override
	public void start(Stage stage) throws Exception {
//		vincularCampos();
//		dateField(txtNascimento);
//		BorderPane bp = new BorderPane();
//		Scene scn = new Scene(bp, 600, 200);
//		
//		GridPane paneCampos = new GridPane();
//		
//		paneCampos.add(new Label("CPF"), 0, 0);
//		paneCampos.add(txtCpf, 1, 0);
//		
//		paneCampos.add(new Label("Nome"), 0, 1);
//		paneCampos.add(txtNome, 1, 1);
//		
//		paneCampos.add(new Label("Nascimento"), 0, 2);
//		paneCampos.add(txtNascimento, 1, 2);
//		
//		btnAdicionar.setOnAction(this);
//		btnPesquisar.setOnAction(this);
//		
//		bp.setTop(paneCampos);
//		bp.setCenter(table);
//		
//		stage.setScene(scn);
//		stage.setTitle("Cadastro de Visitante");
//		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) { 
			control.adicionar();
		} else if (e.getTarget() == btnPesquisar) { 
			control.pesquisarPorId();
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
		Application.launch(VisitaBoundary.class, args);
	}
}
