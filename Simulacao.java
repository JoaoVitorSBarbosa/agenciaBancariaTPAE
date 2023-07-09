import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Responsável pela simulação.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * Este código é baseado em um trabalho anterior de David J. Barnes, Michael Kolling,
 * Luiz Merschmann, João Barbosa, Pedro Ernesto e Ana Clara.
 * 
 * @author David J. Barnes
 * @version 2.0
 */
public class Simulacao {
    private static Simulacao singleTon;

    public static int tempoSimulacao;
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
        "Maria",
        "Jose",
        "Carlos",
        "Paulo",
        "Lucas",
        "Marcos",
        "Mateus",
        "Luciana",
        "Mariana",
        "Juliana",
        "Fernanda",
        "Fernando",
        "Rafael",
        "Rafaela",
        "Danilo",
        "Daniel",
        "Daniela",
        "Gabriel",
        "Gabriela",
        "Gustavo",
    };

    /**
     * Construtor da classe Simulacao.
     *
     * @param numeroClientes   o número de clientes a serem gerados na simulação
     * @param numeroAtendentes o número de atendentes a serem criados na simulação
     */
    private Simulacao(int numeroClientes, int numeroAtendentes) {
        rand = new Random();
        mapa = new Mapa();

        filaCliente = new LinkedList<>();
        listaAtendentes = new ArrayList<>();
        criarAtendentes(numeroAtendentes);
        gerarFilaClientes(numeroClientes);

        janelaSimulacao = new JanelaSimulacao(mapa);
        tempoSimulacao = 0;

    }

    /**
     * Método que retorna a instância única garantida pelo padrão de projeto Singleton
     * 
     * @param numeroClientes
     * @param numeroAtendentes
     */
    public static Simulacao getInstanceSimulacao(int numeroClientes, int numeroAtendentes){
        return (singleTon==null) ? new Simulacao(numeroClientes, numeroAtendentes) : singleTon;
    }

    /**
     * Gera a fila de clientes para a simulação.
     *
     * @param numeroClientes o número de clientes a serem gerados
     */
    private void gerarFilaClientes(int numeroClientes) {
        for (int i = 0; i < numeroClientes; i++) {
            Cliente cliente = new Cliente(new Localizacao((5+i)-(i/20)*20, 15+(i/20)), getRandomName(), 12548+i); // gera fila S
            cliente.setLocalizacaoDestino(null);
            filaCliente.add(cliente);
            mapa.adicionarItem(cliente);
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
            listaAtendentes.add(new Atendente(new Localizacao( (i * 3) + 5, 5), getRandomName()));
            mapa.adicionarItem(listaAtendentes.get(i));
        }
    }

    /**
     * Executa a simulação por até o fim da fila de clientes
     */
    public void executarSimulacao() {
        janelaSimulacao.executarAcao();
        do {
            esperar(200);
            executarUmPasso();
            tempoSimulacao++;
        } while (listaAtendentes.stream().anyMatch(at -> at.getCliente() != null) || !filaCliente.isEmpty());
    }

    /**
     * Executa um único passo da simulação.
     * Move o próximo cliente na fila e atualiza o mapa.
     */
    private void executarUmPasso() {
        Cliente cliente;
        for (Atendente at: listaAtendentes) {
            if (at.getCliente()==null) {
                if (!filaCliente.isEmpty()) {
                    cliente = filaCliente.poll();

                    cliente.setLocalizacaoDestino(new Localizacao(cliente.getLocalizacaoAtual().getX(),cliente.getLocalizacaoAtual().getY()-1));
                    mapa.atualizarMapa();
                    janelaSimulacao.executarAcao();

                    cliente.setLocalizacaoDestino(new Localizacao(at.getLocalizacaoAtual().getX(),at.getLocalizacaoAtual().getY()+1));
                    at.setCliente(cliente);
                }
            } else {
                cliente = at.getCliente();
                if (cliente.getLocalizacaoAtual().equals(cliente.getLocalizacaoDestino()) && !at.estaAtendendo()) {
                    at.atenderCliente(cliente);
                } else if (at.estaAtendendo()) {
                    if (at.estaLivre(tempoSimulacao)) {
                        cliente.operaConta();
                        at.encerrarAtendimento(cliente);
                        saiDeCena(cliente);
                    }
                }
            }
        }
        mapa.atualizarMapa();
        janelaSimulacao.executarAcao();
    }

    private void saiDeCena(Cliente cliente){
        cliente.setLocalizacaoDestino(new Localizacao(cliente.getLocalizacaoAtual().getX()-1, 1));
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
