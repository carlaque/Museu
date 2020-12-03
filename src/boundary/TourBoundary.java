//package boundary;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import control.TourControl;
//import entity.Tour;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.beans.binding.Bindings;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.util.StringConverter;
//import javafx.util.converter.LocalDateStringConverter;
//import javafx.util.converter.LongStringConverter;
//
//public class TourBoundary implements EventHandler<ActionEvent>, TelaStrategy {
//	
//	private TextField txtId = new TextField();
//	private TextField txtNome = new TextField();
//	private TextField txtData = new TextField();
//	
//	private Button btnAdicionar = new Button("Adicionar");
//	private Button btnPesquisar = new Button("Pesquisar");
//	
//	private TourControl control = new TourControl();
//	private TableView<Tour> table = new TableView<>();
//	
//	@SuppressWarnings("unchecked")
//	public void vincularCampos() {
//
//		StringConverter<? extends Number> idConverter = new LongStringConverter();
//		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter htf = DateTimeFormatter.ofPattern("HH:mm:ss");
//		
//		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
//		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
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
//	
//	public TourBoundary(Principal principal){
//		vincularCampos();
//		dateField(txtData);
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
//	}
//
//	@Override
//	public void handle(ActionEvent e) {
//		if (e.getTarget() == btnAdicionar) { 
//			control.adicionar();
//		} else if (e.getTarget() == btnPesquisar) { 
//			control.pesquisarPorId();
//		}
//	}
//	
//	private static void maxField(final TextField textField, final Integer length) {
//	    textField.textProperty().addListener(new ChangeListener<String>() {
//	        @Override
//	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//	            if (newValue.length() > length)
//	                textField.setText(oldValue);
//	        }
//	    });
//	}
//	
//	public static void dateField(final TextField textField) {
//	    maxField(textField, 10);
//	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
//	        @Override
//	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//	            if (newValue.intValue() < 11) {
//	                String value = textField.getText();
//	                value = value.replaceAll("[^0-9]", "");
//	                value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
//	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
//	                textField.setText(value);
//	                positionCaret(textField);
//	            }
//	        }
//	    });
//	}
//	
//	public static void horaField(final TextField textField) {
//	    maxField(textField, 10);
//	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
//	        @Override
//	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//	            if (newValue.intValue() < 11) {
//	                String value = textField.getText();
//	                value = value.replaceAll("[^0-9]", "");
//	                value = value.replaceFirst("(\\d{2})(\\d{2})", "$1:$2");
//	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d{2})", "$1:$2:$3");
//	                textField.setText(value);
//	                positionCaret(textField);
//	            }
//	        }
//	    });
//	}
//	
//	private static void positionCaret(final TextField textField) {
//	    Platform.runLater(new Runnable() {
//	        @Override
//	        public void run() {
//	            textField.positionCaret(textField.getText().length());
//	        }
//	    });
//	}
//
//	@Override
//	public Pane getTela() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
