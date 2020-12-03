package boundary;

import control.TourControl;
import entity.Tour;
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

public class TourBoundary extends Application implements EventHandler<ActionEvent> {
	
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtData = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private TourControl control = new TourControl();
	private TableView<Tour> table = new TableView<>();
	
//	@SuppressWarnings("unchecked")
//	public void vincularCampos() {
//
//		StringConverter<? extends Number> idConverter = new LongStringConverter();
//		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter htf = DateTimeFormatter.ofPattern("HH:mm:ss");
//		
//		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
//		Bindings.bindBidirectional(txtNome.textProperty(), control.get);
//		Bindings.bindBidirectional(txtData.textProperty(), control.getNascimentoProperty(), dateConverter);
//		
//		TableColumn<Visitante, String> colCpf = new TableColumn<>("CPF");
//		colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
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
//		table.setItems(control.getVisitantes());
//	}
	
	public void start(Stage stage) throws Exception {
//		vincularCampos();
//		dateField(txtData);
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
//		paneCampos.add(txtData, 1, 2);
//		
//		btnAdicionar.setOnAction(this);
//		btnPesquisar.setOnAction(this);
//		
//		bp.setTop(paneCampos);
//		bp.setCenter(table);
//		
//		stage.setScene(scn);
//		stage.setTitle("Cadastro de Tour");
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
	
	public static void horaField(final TextField textField) {
	    maxField(textField, 10);
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() < 11) {
	                String value = textField.getText();
	                value = value.replaceAll("[^0-9]", "");
	                value = value.replaceFirst("(\\d{2})(\\d{2})", "$1:$2");
	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d{2})", "$1:$2:$3");
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
		Application.launch(TourBoundary.class, args);
	}
}
