package br.ufla.gac111.grupo1;

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
    private int numeroClientes;
    private int numeroReparticoes;
    private static String[] nomes = {
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
     * @param numeroClientes   o número de clientes a serem gerados na simulação
     * @param numeroAtendentes o número de atendentes a serem criados na simulação
     */
    private Simulacao(int numeroClientes, int numeroAtendentes) {
        rand = new Random();
        mapa = new Mapa();
        this.numeroClientes = numeroClientes;
        numeroReparticoes = numeroClientes / 20;
        filaCliente = new LinkedList<>();
        listaAtendentes = new ArrayList<>();
        gerarCenario(numeroAtendentes);

        gerarFilaClientes();

        janelaSimulacao = new JanelaSimulacao(mapa);
        tempoSimulacao = 0;

    }

    /**
     * Método que retorna a instância única garantida pelo padrão de projeto
     * Singleton
     * 
     * @param numeroClientes
     * @param numeroAtendentes
     */
    public static Simulacao getInstanceSimulacao(int numeroClientes, int numeroAtendentes){
        if (singleTon == null){
            singleTon = new Simulacao(numeroClientes, numeroAtendentes);
        } return singleTon;
    }

    /**
     * Gera a fila de clientes para a simulação.
     * @param numeroClientes o número de clientes a serem gerados
     */
    private void gerarFilaClientes() {
        Localizacao proximaLocalizacao = null;

        for (int i = 0; i < numeroClientes; i++) {
            proximaLocalizacao = getProximoLugarFila(proximaLocalizacao);
            Cliente cliente = new Cliente(new Localizacao(proximaLocalizacao.getX(), proximaLocalizacao.getY()),getRandomName(), 12548 + i); // gera fila S
            cliente.setLocalizacaoDestino(null);
            filaCliente.add(cliente);
            mapa.adicionarItem(cliente);
        }
    }

    /**
     * Obtém um nome de cliente aleatório da matriz de nomes de clientes.
     * @return um nome de cliente aleatório.
     */
    private String getRandomName() {
        int randomIndex = rand.nextInt(nomes.length);
        return nomes[randomIndex];
    }

    /**
     * Cria os atendentes para a simulação.
     * @param numeroAtendentes o número de atendentes a serem criados
     */
    private void criarAtendentes(int numeroAtendentes) {
        for (int i = 0; i < numeroAtendentes; i++) {
            listaAtendentes.add(new Atendente(new Localizacao(mapa.getLargura() - ((i * 3) + 5), 5), getRandomName()));
            mapa.adicionarItem(listaAtendentes.get(i));
        }
    }

    /**
     * Método para construção das paredes do ambiente
     * @param numero de cabines a serem implementadas
     */
    private void gerarParedes(int numeroCabines) {
        for (int i = 7; i <= mapa.getAltura() - 20; i++) {
            mapa.adicionarItem(new Item("quadropreto", new Localizacao(4, i)));
        }
        for (int i = mapa.getAltura() - 20; i < mapa.getAltura(); i++) {
            for (int j = 4; j < (mapa.getLargura() - ((numeroReparticoes) * 2)) - 2; j++) {
                mapa.adicionarItem(new Item("quadropreto", new Localizacao(j, i)));
            }
        }
        for (int i = ((mapa.getLargura() - ((numeroReparticoes) * 2)) - 2); i < mapa.getLargura(); i++) {
            mapa.adicionarItem(new Item("quadropreto", new Localizacao(i, mapa.getAltura()-1)));
        }
        for (int i = 0; i < mapa.getAltura(); i++) {
            mapa.adicionarItem(new Item("quadropreto", new Localizacao(mapa.getLargura() - 1, i)));
        }
        for (int i = 0; i <= mapa.getAltura() - 23; i++) {
            for (int j = 1; j <= mapa.getLargura() - ((numeroCabines * 3)+4); j++) {
                mapa.adicionarItem(new Item("quadropreto", new Localizacao(mapa.getLargura() - j,i)));
            }
        }  
    }

    /**
     * Método responsável por gerar itens inanimados  do cenário
     * @param numero de cabines a serem implementadas
     */
    private void gerarCenario(int numeroCabines) {
        criarAtendentes(numeroCabines);
        for (int i = 0; i < numeroCabines; i++) {
            mapa.adicionarItem(new Item("policial", new Localizacao((i * 3) + 6, 1)));
            mapa.adicionarItem(new Item("saida", new Localizacao((i * 3) + 4, 0)));
            for (int j = 1; j <= 6; j++) {
                mapa.adicionarItem(new Item("quadropreto", new Localizacao((i * 3) + 4, j)));
            }
        }
        gerarParedes(numeroCabines);
        gerarSeparadores(numeroCabines);
    }

    /**
     * Método para alocar os separadores da fila
     * @param numero de cabines a serem implementadas
     */
    private void gerarSeparadores(int numeroCabines) {
        for(int i = 0; i < 18; i++) {
            for(int j = mapa.getLargura() - 1; j >= mapa.getLargura() - 1  - ((numeroReparticoes * 2) + 1); j--) {
                if(((mapa.getLargura() - 1) - j) % 2 != 0) {
                    mapa.adicionarItem(new Item("separadorV", new Localizacao(j, (mapa.getAltura() - 3) - i)));
                }
            }
        }
    }

    /**
     * Método que retorna a localização do próximo lugar da fila
     * @param localizacaoAtual
     * @return Localização - proximo lugar da fila. Posição inicial se localização atual é null
     */
    private Localizacao getProximoLugarFila(Localizacao localizacaoAtual) {
        int primeiraPos = mapa.getAltura() - 21;
        int yPos = primeiraPos;
        int xPos = (mapa.getLargura() - ((numeroReparticoes) * 2)) - 1;

        if (localizacaoAtual != null) {
            if (localizacaoAtual.getY() == primeiraPos + 19) {
                xPos = localizacaoAtual.getX() + 2;
                yPos = primeiraPos;
            } else {
                yPos = localizacaoAtual.getY() + 1;
                xPos = localizacaoAtual.getX();
            }
        }
        return new Localizacao(xPos, yPos);
    }

    /**
     * Executa a simulação por até o fim da fila de clientes
     */
    public void executarSimulacao() {
        janelaSimulacao.executarAcao();
        do {
            esperar(100);
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
        for (Atendente at : listaAtendentes) {
            if (at.getCliente() == null) {
                if (!filaCliente.isEmpty()) {
                    cliente = filaCliente.poll();
                    Localizacao cliAux = cliente.getLocalizacaoAtual();
                    Localizacao proxLocalizacao;
                    cliente.setLocalizacaoDestino(new Localizacao(at.getLocalizacaoAtual().getX(), at.getLocalizacaoAtual().getY() + 1));
                    mapa.atualizarMapa();
                    janelaSimulacao.executarAcao();

                    for (Cliente cli : filaCliente) {
                        mapa.removerItem(cli);
                        proxLocalizacao = cli.getLocalizacaoAtual();
                        cli.setLocalizacaoAtual(new Localizacao(cliAux.getX(), cliAux.getY()));
                        mapa.adicionarItem(cli);
                        cliAux = proxLocalizacao;
                    }

                    at.setCliente(cliente);
                    at.atenderCliente(cliente);

                }
            } else if (at.getHorarioLivre() - tempoSimulacao == 0) {
                cliente = at.getCliente();
                cliente.setLocalizacaoDestino(new Localizacao(cliente.getLocalizacaoAtual().getX() - 1, 1));
                mapa.atualizarMapa();
                janelaSimulacao.executarAcao();
                at.setCliente(null);
            }
        }
        mapa.atualizarMapa();
        janelaSimulacao.executarAcao();
    }

    /**
     * Espera um número de milissegundos especificado.
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
