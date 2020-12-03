package boundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Principal extends Application 
					implements EventHandler<ActionEvent>, AssinanteComando{
	
	private BorderPane panePrincipal = new BorderPane();
	
	private AutorBoundary autorTela = new AutorBoundary(this);
	private ObraBoundary obraTela = new ObraBoundary(this);
	private FuncionarioBoundary funcionarioTela = new FuncionarioBoundary(this);
	private VisitanteBoundary visitanteTela = new VisitanteBoundary(this);
	
	private TelaStrategy tela = autorTela;
	
	private MenuBar mnuPrincipal = new MenuBar();
	
	private Menu mnuCadastro = new Menu("Cadastro");
	private Menu mnuAjuda = new Menu("Ajuda");
	
	private MenuItem mnuContato = new MenuItem("Autor");
	
//	private MenuItem mnuObra = new MenuItem("Obra");
	private MenuItem mnuFuncionario = new MenuItem("Funcionario");
	private MenuItem mnuVisitante = new MenuItem("Visitante");
	
//	private MenuItem mnuCreditos = new MenuItem("Creditos");
	private MenuItem mnuSair = new MenuItem("Sair");
	
	@Override
	public void start(Stage stage) { 
		Scene scn = new Scene(panePrincipal, 800, 600);
		
		panePrincipal.setTop(mnuPrincipal);
		mnuPrincipal.getMenus().addAll(mnuCadastro, mnuAjuda);
		
		mnuCadastro.getItems().addAll(mnuContato, mnuFuncionario, mnuVisitante, mnuSair);
//		mnuAjuda.getItems().addAll(mnuCreditos);
		
		mnuContato.setOnAction(this);
//		mnuObra.setOnAction(this);
		mnuFuncionario.setOnAction(this);
		mnuVisitante.setOnAction(this);
		
		mnuSair.setOnAction(this);
		
		
		this.telaContext();
		
		stage.setScene(scn);
		stage.setTitle("Tela principal");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == mnuSair) { 
			this.executarComando("sair");
		} else if (e.getTarget() == mnuContato) { 
			this.executarComando("autor");
//		} else if (e.getTarget() == mnuObra) { 
//			this.executarComando("obra");
		} else if (e.getTarget() == mnuFuncionario) { 
			this.executarComando("funcionario");
		} else if (e.getTarget() == mnuVisitante) { 
			this.executarComando("visitante");
		} 

	}
	
	public void telaContext() { 
		panePrincipal.setCenter(tela.getTela());
	}
	
	public void navegarPara(String tela) {
		executarComando(tela);
	}

	@Override
	public void executarComando(String cmd) {
		if ("sair".equals(cmd)) { 
			System.exit(0);
		} else if ("autor".equals(cmd)) { 
			tela = autorTela;
		} else if ("obra".equals(cmd)) { 
			tela = obraTela;
		} else if ("funcionario".equals(cmd)) { 
			tela = funcionarioTela;
		} else if ("visitante".equals(cmd)) { 
			tela = visitanteTela;
		} 
		
		this.telaContext();
	}

	public void setAutor(Long id) {
		this.obraTela.setAutoId(id);
	}
}