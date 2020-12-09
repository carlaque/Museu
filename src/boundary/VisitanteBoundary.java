package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.VisitanteControl;
import entity.Visitante;
import exceptions.VisitanteException;
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

public class VisitanteBoundary implements EventHandler<ActionEvent>, TelaStrategy {

	private BorderPane tela = new BorderPane();

	private TextField txtCpf = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtNascimento = new TextField();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnVoltarParaTour = new Button("Voltar para Tour");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnDeletar = new Button("Deletar");

	private VisitanteControl control = new VisitanteControl();
	private TableView<Visitante> table = new TableView<>();

	private Principal principal;

	private TextField caracteristica = new TextField();

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
		colNascimento.setCellValueFactory((item) -> {
			return new ReadOnlyStringWrapper(item.getValue().getNascimento().format(dtf));
		});

		table.getColumns().addAll(colCpf, colNome, colNascimento);

		table.setItems(control.getVisitantes());

		table.getSelectionModel().selectedItemProperty().addListener((listener, antigo, novo) -> {
			control.setVisitante(novo);
		});
	}

	public VisitanteBoundary(Principal principal) {
		this.principal = principal;
		vincularCampos();
		dateField(txtNascimento);

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("CPF"), 0, 0);
		paneCampos.add(txtCpf, 1, 0);

		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);

		paneCampos.add(new Label("Nascimento"), 0, 2);
		paneCampos.add(txtNascimento, 1, 2);

		paneCampos.add(btnAdicionar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		paneCampos.add(btnAtualizar, 0, 6);
		paneCampos.add(btnDeletar, 1, 6);
		paneCampos.add(btnVoltarParaTour, 2, 6);
		
		btnVoltarParaTour.setVisible(false);

		btnVoltarParaTour.setOnAction(this);
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnDeletar.setOnAction(this);

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
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar o visitante").show();
			}
			carregar();
		} else if (e.getTarget() == btnPesquisar) {
			try {
				control.pesquisarPorNome();
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar o visitante").show();

			}
		} else if (e.getTarget() == btnAtualizar) {
			try {
				control.update();
				carregar();
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao atualizar o visitante").show();
			}
		} else if (e.getTarget() == btnDeletar) {
			try {
				control.delete();
				carregar();
			} catch (VisitanteException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao deletar o visitante").show();
			}
		} else if (e.getTarget() == btnVoltarParaTour) {
			if (!txtNome.getText().isEmpty()) {
				this.principal.setIdVisitante(control.getVisitante().getCpf());
				this.principal.navegarPara("visita");
			} else {
				new Alert(AlertType.ERROR, "Selecione um Visitante para atribuir a Visita").show();
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
	public void carregar() {
		try {
			control.carregar();
		} catch (VisitanteException e1) {
			e1.printStackTrace();
			new Alert(AlertType.ERROR, "Erro ao carregar dados").show();
		}

	}

}
