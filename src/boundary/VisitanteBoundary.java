package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.VisitanteControl;
import entity.Visitante;
import exceptions.FuncionarioException;
import exceptions.VisitanteException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class VisitanteBoundary implements EventHandler<ActionEvent>, TelaStrategy {
	
	private BorderPane tela = new BorderPane();
	
	private TextField txtCpf = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtNascimento = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private VisitanteControl control = new VisitanteControl();
	private TableView<Visitante> table = new TableView<>();
	
	private Principal principal;
	
	@SuppressWarnings("unchecked")
	public void vincularCampos() {

		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Bindings.bindBidirectional(txtCpf.textProperty(), control.getCpfProperty());
		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
		Bindings.bindBidirectional(txtNascimento.textProperty(), control.getNascimentoProperty(), dateConverter);
		
		TableColumn<Visitante, String> colCpf = new TableColumn<>("CPF");
		colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Visitante, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Visitante, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getNascimento().format(dtf));
		});
		
		table.getColumns().addAll(colCpf, colNome, colNascimento);
		
		table.setItems(control.getVisitantes());
	}
	
	public VisitanteBoundary(Principal principal) {
		vincularCampos();
		dateField(txtNascimento);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("CPF"), 0, 0);
		paneCampos.add(txtCpf, 1, 0);
		
		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);
		
		paneCampos.add(new Label("Nascimento"), 0, 2);
		paneCampos.add(txtNascimento, 1, 2);
		
		paneCampos.add(btnAdicionar, 0, 3);
		paneCampos.add(btnPesquisar, 1, 3);
		
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		
		tela.setTop(paneCampos);
		tela.setCenter(table);
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) { 
			System.out.println("Botão adicionar foi pressionado");
			try {
				control.adicionar();
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar o visitante").show();			
			}
		} else if (e.getTarget() == btnPesquisar) { 
			System.out.println("Botão pesquisar foi pressionado");
			try {
				control.pesquisarPorNome();
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar o visitante").show();

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
}
