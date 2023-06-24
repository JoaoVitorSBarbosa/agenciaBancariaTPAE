import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Responsável pela simulação.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * Este código é baseado em um trabalho anterior de David J. Barnes, Michael Kolling,
 * Luiz Merschmann, João Barbosa e Ana Clara.
 * 
 * @author David J. Barnes
 * @author Michael Kolling
 * @author Luiz Merschmann
 * @author João Barbosa
 * @author Ana Clara
 * @version 2.0
 */
public class Simulacao {
    private Cliente cliente;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Queue<Cliente> filaCliente;
    private ArrayList<Atendente> listaAtendentes;
    
    /**
     * Construtor da classe Simulacao.
     * Inicializa a simulação e configura os elementos necessários.
     */
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        Random randCli = new Random();
        filaCliente = new LinkedList<>();
        listaAtendentes = new ArrayList<>();
        criarAtendentes();
        cliente = new Cliente("Jozé Maria", 6666666, randCli.nextInt(8), new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        cliente.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        mapa.adicionarItem(cliente);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    private void criarAtendentes(){
        listaAtendentes.add(new Atendente("Ana", 0));
        listaAtendentes.add(new Atendente("Joao", 0));
        listaAtendentes.add(new Atendente("Pedro", 0));
    }

    /**
     * Executa a simulação por um número específico de passos.
     * 
     * @param numPassos O número de passos da simulação a serem executados.
     */
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
