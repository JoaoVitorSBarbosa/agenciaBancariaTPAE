import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
 * @version 2.0
 */
public class Simulacao {
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Queue<Cliente> filaCliente;
    private List<Atendente> listaAtendentes;
    private Random rand;

    private static String[] nomesClientes = {
        "Ana",
        "Pedro",
        "Joao",
        "Luiz",
        "Maria"
    };

    /**
     * Construtor da classe Simulacao.
     *
     * @param numeroClientes   o número de clientes a serem gerados na simulação
     * @param numeroAtendentes o número de atendentes a serem criados na simulação
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

    /**
     * Gera a fila de clientes para a simulação.
     *
     * @param numeroClientes o número de clientes a serem gerados
     */
    private void gerarFilaClientes(int numeroClientes) {
        for (int i = 0; i < numeroClientes; i++) {
            Cliente cliente = new Cliente(new Localizacao(1, 34 - i), getRandomName(), i, rand.nextInt(i));
            cliente.setLocalizacaoDestino(
                    new Localizacao(rand.nextInt(mapa.getLargura()), rand.nextInt(mapa.getAltura())));
            filaCliente.add(cliente);
        }
    }

    /**
     * Obtém um nome de cliente aleatório da matriz de nomes de clientes.
     * 
     * @return um nome de cliente aleatório.
     */
    private String getRandomName() {
        int randomIndex = rand.nextInt(nomesClientes.length);
        return nomesClientes[randomIndex];
    }

    /**
     * Cria os atendentes para a simulação.
     *
     * @param numeroAtendentes o número de atendentes a serem criados
     */
    private void criarAtendentes(int numeroAtendentes) {
        for (int i = 0; i < numeroAtendentes; i++) {
            listaAtendentes.add(new Atendente(new Localizacao(5, (i * 5) + 5), "Ana", 0));
        }
    }

    /**
     * Executa a simulação por um número específico de passos.
     *
     * @param numeroPassos o número de passos a serem executados na simulação
     */
    public void executarSimulacao(int numeroPassos) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numeroPassos; i++) {
            executarUmPasso();
            esperar(100);
        }
    }

    /**
     * Executa um único passo da simulação.
     * Move o próximo cliente na fila e atualiza o mapa.
     */
    private void executarUmPasso() {
        if (!filaCliente.isEmpty()) {
            Cliente cliente = filaCliente.poll();
            mapa.removerItem(cliente);
            cliente.mover();
            mapa.adicionarItem(cliente);
            janelaSimulacao.executarAcao();
        }
    }

    /**
     * Espera um número de milissegundos especificado.
     *
     * @param milisegundos o número de milissegundos a serem esperados
     */
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
