import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Responsável pela simulação.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * Este código é baseado em um trabalho anterior de David J. Barnes, Michael
 * Kolling,
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
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Queue<Cliente> filaCliente;
    private ArrayList<Atendente> listaAtendentes;
    private Random rand;
    private Cliente cliente;

    /**
     * Construtor da classe Simulacao.
     * Inicializa a simulação e configura os elementos necessários.
     */
    public Simulacao(int numeroClientes, int numeroAtendentes) {

        rand = new Random();
        mapa = new Mapa();
        filaCliente = new LinkedList<>();
        listaAtendentes = new ArrayList<>();
        criarAtendentes(numeroAtendentes);
        gerarFilaClientes(numeroClientes);

        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    private void gerarFilaClientes(int numeroClientes) {
        for (int i = 0; i < 1; i++) {
            cliente = new Cliente("Jozé Maria", 6666666, rand.nextInt(8),
                    new Localizacao(0, 0));
            cliente.setLocalizacaoDestino(
                    new Localizacao(rand.nextInt(mapa.getLargura()), rand.nextInt(mapa.getAltura())));
            filaCliente.add(cliente);
            mapa.adicionarItem(cliente);
        }
    }

    private void criarAtendentes(int numeroAtendentes) {
        for (int i = 0; i < numeroAtendentes; i++) {
            listaAtendentes.add(new Atendente("Ana", 0));
        }
    }

    /**
     * Executa a simulação por um número específico de passos.
     * 
     * @param numPassos O número de passos da simulação a serem executados.
     */
    public void executarSimulacao(int numPassos) {
        janelaSimulacao.executarAcao();
        if (filaCliente.peek() != null) {
            cliente = filaCliente.remove();
            for (int i = 0; i < numPassos; i++) {
                executarUmPasso();
                esperar(100);
            }
        }
    }

    private void executarUmPasso() {
        mapa.removerItem(cliente);
        cliente.mover();
        mapa.adicionarItem(cliente);
        janelaSimulacao.executarAcao();
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
