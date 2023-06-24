import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes, Michael Kolling, Luiz Merschmann and João Barbosa and Ana Clara
 */
public class Simulacao {
    private Cliente cliente;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Queue<Cliente> filaCliente;
    private ArrayList<Atendente> listaAtendentes;
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        Random randCli = new Random();
        filaCliente = new LinkedList<>();
        listaAtendentes = new ArrayList<>();
        criarAtendentes();
        cliente = new Cliente("Jozé Maria", 6666666, randCli.nextInt(8), new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um cliente em uma posicao aleatoria
        cliente.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(cliente);//Inicializando o mapa com o veículo
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    private void criarAtendentes(){
        listaAtendentes.add(new Atendente("Ana", 0));
        listaAtendentes.add(new Atendente("Joao", 0));
        listaAtendentes.add(new Atendente("Pedro", 0));
    }
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }        
    }

    private void executarUmPasso() {
        mapa.removerItem(cliente);
        cliente.executarAcao();
        mapa.adicionarItem(cliente);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
