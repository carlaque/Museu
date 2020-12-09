package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.VisitaControl;
import entity.Visita;
import exceptions.VisitaException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class VisitaBoundary implements EventHandler<ActionEvent>, TelaStrategy {
	
	private BorderPane tela = new BorderPane();
	
	private TextField txtId = new TextField();
	private TextField txtIdVisitante = new TextField();
	private TextField txtIdTour = new TextField();
	private TextField txtData = new TextField();
	
	private Button btnVoltarParaTour = new Button("Voltar para Tour");
	private Button btnAdicionar = new Button("Adicionar ao Tour Selecionado");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnGerenciarVisitante = new Button("Gerenciar Visitante");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnDeletar = new Button("Deletar");
	
	private VisitaControl control = new VisitaControl();
	private TableView<Visita> table = new TableView<>();
	
	private Principal principal;
	
	
	@SuppressWarnings("unchecked")
	public void vincularCampos() {

		StringConverter<? extends Number> idConverter = new LongStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
		Bindings.bindBidirectional(txtIdVisitante.textProperty(), control.getIdVisitanteProperty(), (StringConverter<Number>)idConverter);
		Bindings.bindBidirectional(txtIdTour.textProperty(), control.getIdTourProperty(), (StringConverter<Number>)idConverter);
		Bindings.bindBidirectional(txtData.textProperty(), control.getDataProperty(), dateConverter);
		
		TableColumn<Visita, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Visita, Long> colIdVisitante = new TableColumn<>("Id Visitante");
		colIdVisitante.setCellValueFactory(new PropertyValueFactory<>("idVisitante"));
		
		TableColumn<Visita, String> colData = new TableColumn<>("Data");
		colData.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getData().format(dtf));
		});
		
		table.getColumns().addAll(colId, colIdVisitante, colData);
		
		table.setItems( control.getVisitas() );
		
		table.getSelectionModel().selectedItemProperty().addListener(
				(listener, antigo, novo) -> {
					control.setVisita(novo);
				});
	}
	
	public VisitaBoundary(Principal principal) {
		this.principal = principal;
		vincularCampos();
		dateField(txtData);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("ID"), 0, 0);
		paneCampos.add(txtId, 1, 0);
		
		paneCampos.add(new Label("ID Visitante"), 0, 1);
		paneCampos.add(txtIdVisitante, 1, 1);
		
		paneCampos.add(new Label("Data"), 0, 2);
		paneCampos.add(txtData, 1, 2);
		
		
		
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnDeletar.setOnAction(this);
		btnVoltarParaTour.setOnAction(this);
		btnGerenciarVisitante.setOnAction(this);
		
		GridPane btns = new GridPane();
		
		btns.add(btnVoltarParaTour, 1, 0);
		btns.add(btnAdicionar, 2,0);
		btns.add(btnPesquisar, 3,0);
		btns.add(btnAtualizar, 4,0);
		btns.add(btnDeletar, 5,0);
		btns.add(btnGerenciarVisitante, 6, 0);
		
		paneCampos.setAlignment(Pos.CENTER);
		btns.setAlignment(Pos.CENTER);
		btns.setHgap(5);
		paneCampos.setVgap(3);
		paneCampos.setHgap(5);
		
		
		tela.setTop(paneCampos);
		tela.setCenter(btns);
		tela.setBottom(table);
		
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) { 
			try {
				control.adicionar();
			} catch (VisitaException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar a visita").show();			
			}
			carregar();
		} else if (e.getTarget() == btnPesquisar) { 
			try {
				control.pesquisarPorIdTour();
			} catch (VisitaException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar a visita").show();
			}
		} else if (e.getTarget() == btnAtualizar) {
			try {
				control.update();
				carregar();
			} catch (VisitaException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao atualizar a visita").show();
			}
		} else if (e.getTarget() == btnDeletar) {
			try {
				control.delete();
				carregar();
			} catch (VisitaException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao deletar a visita").show();
			}
		} else if (e.getTarget() == btnVoltarParaTour) {
			this.principal.navegarPara("tour");
		}else if (e.getTarget() == btnGerenciarVisitante) {
			this.principal.setCaracteristicaVisitante("extencao");
			this.principal.navegarPara("visitante");
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

	@Override
	public Pane getTela() {
		return tela;
	}

	public void setIdTour(long id) {
		this.txtIdTour.setText(id + "");
	}

	public void setIdVisitante(String id) {
		this.txtIdVisitante.setText(id);
	}
	
	@Override
	public void carregar(){
		try {
			control.pesquisarPorIdTour();
		} catch (VisitaException e1) {
			e1.printStackTrace();
			new Alert(AlertType.ERROR, "Erro ao carregar dados").show();			
		}

	}
}
