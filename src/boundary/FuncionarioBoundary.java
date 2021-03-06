package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.FuncionarioControl;
import entity.Funcionario;
import exceptions.FuncionarioException;
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

public class FuncionarioBoundary implements EventHandler<ActionEvent>, TelaStrategy{
	
	private BorderPane tela = new BorderPane();	
	
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNascimento = new TextField();
	private TextField txtTelefone = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnVoltarParaTour = new Button("Voltar para Tour");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnDeletar = new Button("Deletar");
	
	private FuncionarioControl control = new FuncionarioControl();
	private TableView<Funcionario> table = new TableView<>();
	
	private Principal principal;

	private TextField caracteristica = new TextField();
	
	@SuppressWarnings("unchecked")
	public void vincularCampos() {
		StringConverter<? extends Number> idConverter = new LongStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(), (StringConverter<Number>)idConverter);
		Bindings.bindBidirectional(txtNome.textProperty(), control.getNomeProperty());
		Bindings.bindBidirectional(txtCpf.textProperty(), control.getCpfProperty());
		Bindings.bindBidirectional(txtNascimento.textProperty(), control.getNascimentoProperty(), dateConverter);
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.getTelefoneProperty());
		
		TableColumn<Funcionario, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Funcionario, String> colCpf = new TableColumn<>("CPF");
		colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Funcionario, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(
				(item) -> {return new ReadOnlyStringWrapper(item.getValue().getNascimento().format(dtf));}
				);
		
		TableColumn<Funcionario, String> colTelefone = new TableColumn<>("Telefone");
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		
		table.getColumns().addAll(colId, colNome,colCpf, colNascimento, colTelefone);
		
		table.setItems( control.getFuncionarios() );
		
		table.getSelectionModel().selectedItemProperty().addListener(
				(listener, antigo, novo) -> {
					control.setFuncionario(novo);
				});
		
	}
	
	public FuncionarioBoundary(Principal principal) {
		this.principal = principal;
		vincularCampos();
		dateField(txtNascimento);
		
		GridPane paneCampos = new GridPane();
		
		paneCampos.add(new Label("Id"), 0, 0);
		paneCampos.add(txtId, 1, 0);
		
		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);
		
		paneCampos.add(new Label("CPF"), 0, 2);
		paneCampos.add(txtCpf, 1, 2);
		
		paneCampos.add(new Label("Nascimento"), 0, 3);
		paneCampos.add(txtNascimento, 1, 3);
		
		paneCampos.add(new Label("Telefone"), 0, 4);
		paneCampos.add(txtTelefone, 1, 4);
		
		
		btnVoltarParaTour.setOnAction(this);
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnDeletar.setOnAction(this);
		
		btnVoltarParaTour.setVisible(false);
		
		
		GridPane btns = new GridPane();
		
		btns.add(btnVoltarParaTour, 1, 0);
		btns.add(btnAdicionar, 2,0);
		btns.add(btnPesquisar, 3,0);
		btns.add(btnAtualizar, 4,0);
		btns.add(btnDeletar, 5,0);
		
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
			} catch (FuncionarioException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar o funcionario").show();			
			}
			carregar();
		} else if (e.getTarget() == btnPesquisar) { 
			try {
				control.pesquisarPorNome();
			} catch (FuncionarioException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar o funcionario").show();
			}
		} else if (e.getTarget() == btnAtualizar) {
			try {
				control.update();
				carregar();
			} catch (FuncionarioException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao atualizar o funcionario").show();
			}
		} else if (e.getTarget() == btnDeletar) {
			try {
				control.delete();
				carregar();
			} catch (FuncionarioException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao deletar o funcionario").show();
			}
		} else if (e.getTarget() == btnVoltarParaTour) {
			if(!txtId.getText().isEmpty() && !txtNome.getText().isEmpty()) {
				this.principal.setIdFuncionario(control.getFuncionario().getId());
				this.principal.navegarPara("tour");
				
			}else {
				new Alert(AlertType.ERROR, "Selecione um Funcionario para atribuir ao Tour").show();
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

	public void setCaracteristica(String caract) {
		this.caracteristica.setText(caract);
		
		if(caract == "")
    		btnVoltarParaTour.setVisible(false);
    	else
    		btnVoltarParaTour.setVisible(true);
	}
	
	@Override
	public void carregar(){
		try {
			control.carregar();
		} catch (FuncionarioException e1) {
			e1.printStackTrace();
			new Alert(AlertType.ERROR, "Erro ao carregar dados").show();			
		}
		
		
	}

}
