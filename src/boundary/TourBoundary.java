package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.TourControl;
import entity.Tour;
import exceptions.ObraException;
import exceptions.TourException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LongStringConverter;

public class TourBoundary implements EventHandler<ActionEvent>, TelaStrategy {
	
	private BorderPane tela = new BorderPane();
	
	private TextField txtId = new TextField("");
	private TextField txtIdFuncionario= new TextField("");
	private TextField txtData = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAdicionarFuncionario = new Button("Adicionar Funcionario");
	private Button btnGerenciarVisitas = new Button("Gerenciar Visitas");
	
	private TourControl control = new TourControl();
	private TableView<Tour> table = new TableView<>();
	
	private Principal principal;
	
	@SuppressWarnings("unchecked")
	public void vincularCampos() {

		StringConverter<? extends Number> idConverter = new LongStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter htf = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
		Bindings.bindBidirectional(txtIdFuncionario.textProperty(), control.getIdFuncionarioProperty(), (StringConverter<Number>)idConverter);
		
		Bindings.bindBidirectional(txtData.textProperty(), control.getDataProperty(), dateConverter);
		
		TableColumn<Tour, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Tour, Long> colIdFuncionario = new TableColumn<>("idFuncionario");
		colIdFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		
		TableColumn<Tour, String> colData = new TableColumn<>("Data");
		colData.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getData().format(dtf));
		});
		
		table.getColumns().addAll(colId, colIdFuncionario, colData);
		
		table.setItems(control.getTours());
		
		table.getSelectionModel().selectedItemProperty().addListener(
				(listener, antigo, novo) -> {
					control.setTour(novo);
				});
		
//		if(txtId.getText() == "0") txtId.setText("");
	}
	
	public TourBoundary(Principal principal){
		this.principal = principal;
		vincularCampos();
		dateField(txtData);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("Id"), 0, 0);
		paneCampos.add(txtId, 1, 0);
		
		paneCampos.add(new Label("idFuncionario"), 0, 1);
		paneCampos.add(txtIdFuncionario, 1, 1);
		
		paneCampos.add(new Label("Data"), 0, 2);
		paneCampos.add(txtData, 1, 2);
		
		paneCampos.add(btnAdicionar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		
		paneCampos.add(btnAdicionarFuncionario, 2, 5);
		paneCampos.add(btnGerenciarVisitas, 3, 5);
		
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnAdicionarFuncionario.setOnAction(this);
		btnGerenciarVisitas.setOnAction(this);
		
		tela.setTop(paneCampos);
		tela.setCenter(table);		
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) { 
			try {
				control.adicionar();
			} catch (TourException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar o tour").show();			
			}
			carregar();
		} else if (e.getTarget() == btnPesquisar) { 
			try {
				control.carregar();
			} catch (TourException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar o tour").show();

			}
		} else if (e.getTarget() == btnAdicionarFuncionario) { 
			this.principal.setCaracteristicaFuncionario("extencao");
			this.principal.navegarPara("funcionario");
		} else if (e.getTarget() == btnGerenciarVisitas) { 
			
			if(control.getTour().getId() > 0 ) {
				this.principal.setIdTourDasVisitas(Long.parseLong( txtId.getText() ) );
				this.principal.navegarPara("visita");
			}else {
				new Alert(AlertType.ERROR, "Selecione um Tour para gerenciar suas visitas").show();
			}
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

	@Override
	public Pane getTela() {
		return tela;
	}

	public void setIdFuncionario(Long id) {
		this.txtIdFuncionario.setText(id +"");
	}
	
	@Override
	public void carregar(){
		try {
			control.carregar();
		} catch (TourException e1) {
			e1.printStackTrace();
			new Alert(AlertType.ERROR, "Erro ao carregar dados").show();			
		}

	}
}
