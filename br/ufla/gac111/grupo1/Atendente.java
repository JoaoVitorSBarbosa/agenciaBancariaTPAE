package br.ufla.gac111.grupo1;
/**
 * Classe que representa um atendente.
 * @author João Vitor
 * @author Ana Clara
 * @author Pedro Ernesto
 */
public class Atendente extends Pessoa {
    private int horarioLivre;
    private Cliente clienteAtual;

    /**
     * Construtor da classe Atendente.
     * @param localizacao a localização do atendente
     * @param nome o nome do atendente
     * @param horarioAtual o horário em que a simulação se encontra ao criar o objeto
     */
    public Atendente(Localizacao localizacao, String nome) {
        super("atendente", localizacao, nome);
        horarioLivre = 0;
    }

    /**
     * Realiza o atendimento de um cliente, definindo o horário em que o atendente estará livre.
     * @param cliente o cliente a ser atendido
     */
    public void atenderCliente(Cliente cliente) {
        System.out.println("Atendente: " + getNome() + " está atendendo o cliente: " + cliente.getId());
        cliente.operaConta();
        horarioLivre += cliente.getDuracaoAtendimento();
    }

    /**
     * Verifica se o atendente está livre em um determinado tempo.
     * @param tempoAtual o tempo atual da simulação
     * @return true se o atendente está livre, false caso contrário
     */
    public boolean estaLivre(int tempoAtual) {
        return tempoAtual >= horarioLivre;
    }

    @Override
    public String toString() {
        return super.toString() + " Próximo horário: " + horarioLivre;
    }

    /**
     * Obtém o cliente atualmente sendo atendido pelo atendente.
     * @return o cliente atual
     */
    public Cliente getCliente() {
        return clienteAtual;
    }

    /**
     * Define o cliente a ser atendido pelo atendente.
     * @param clienteAtual o cliente atual
     */
    public void setCliente(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    /**
     * Indica qual horário o atendente estará livre
     * @return
     */
    public int getHorarioLivre() {
        return horarioLivre;
    }
}
