package boundary;

import control.ObraControl;
import entity.Obra;
import exceptions.ObraException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
import javafx.util.converter.LongStringConverter;

public class ObraBoundary implements EventHandler<ActionEvent>, TelaStrategy {

	private BorderPane tela = new BorderPane();

	private Principal principal;

	private TextField txtId = new TextField();
	private TextField txtTitulo = new TextField();
	private TextField txtDescricao = new TextField();
	private TextField txtPeriodo = new TextField();

	private TextField txtAutorId = new TextField();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnVoltar = new Button("Voltar para Autores");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnDeletar = new Button("Deletar");

	private ObraControl control = new ObraControl();
	private TableView<Obra> table = new TableView<>();

	@SuppressWarnings("unchecked")
	public void vincularCampos() {
		StringConverter<? extends Number> idConverter = new LongStringConverter();

		Bindings.bindBidirectional(txtId.textProperty(), control.getIdProperty(),
				(StringConverter<Number>) idConverter);
		Bindings.bindBidirectional(txtTitulo.textProperty(), control.getTituloProperty());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.getDescricaoProperty());
		Bindings.bindBidirectional(txtPeriodo.textProperty(), control.getPeriodoProperty());
		Bindings.bindBidirectional(txtAutorId.textProperty(), control.getAutorIdProperty(),
				(StringConverter<Number>) idConverter);

		TableColumn<Obra, Long> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Obra, String> colTitulo = new TableColumn<>("Titulo");
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		TableColumn<Obra, String> colDescricao = new TableColumn<>("Descrição");
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		TableColumn<Obra, String> colPeriodo = new TableColumn<>("Periodo");
		colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

		table.getColumns().addAll(colId, colTitulo, colDescricao, colPeriodo);

		table.setItems(control.getObras());

		table.getSelectionModel().selectedItemProperty().addListener((listener, antigo, novo) -> {
			control.setObra(novo);
		});

	}

	public ObraBoundary(Principal principal) {
		this.principal = principal;
		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Id"), 0, 0);
		paneCampos.add(txtId, 1, 0);

		// paneCampos.add(new Label("IdAutor"), 2, 0);
		// paneCampos.add(txtAutorId, 2, 0);

		paneCampos.add(new Label("Titulo"), 0, 1);
		paneCampos.add(txtTitulo, 1, 1);

		paneCampos.add(new Label("Descricao"), 0, 2);
		paneCampos.add(txtDescricao, 1, 2);

		paneCampos.add(new Label("Periodo"), 0, 3);
		paneCampos.add(txtPeriodo, 1, 3);


		btnVoltar.setOnAction(this);
		btnAdicionar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnDeletar.setOnAction(this);

		GridPane btns = new GridPane();
		
		btns.add(btnVoltar, 1, 0);
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
			} catch (ObraException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao adicionar a obra").show();
			}
			carregar();
		} else if (e.getTarget() == btnPesquisar) {
			try {
				control.pesquisarPorNome();
			} catch (ObraException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao pesquisar a obra").show();

			}
		} else if (e.getTarget() == btnAtualizar) {
			try {
				control.update();
				carregar();
			} catch (ObraException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao atualizar a obra").show();
			}
		} else if (e.getTarget() == btnDeletar) {
			try {
				control.delete();
				carregar();
			} catch (ObraException e1) {
				e1.printStackTrace();
				new Alert(AlertType.ERROR, "Erro ao deletar a obra").show();
			}
		} else if (e.getTarget() == btnVoltar) {
			this.principal.navegarPara("autor");
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

	public void setAutoId(long id) {
		this.txtAutorId.setText(((int) id) + "");
	}

	@Override
	public void carregar() {
		try {
			control.pesquisarPorNome();
			;
		} catch (ObraException e1) {
			e1.printStackTrace();
			new Alert(AlertType.ERROR, "Erro ao carregar dados").show();
		}

	}

}
