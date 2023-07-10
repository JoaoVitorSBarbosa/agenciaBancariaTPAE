/**
 * Classe que representa um atendente.
 * 
 * @author João Vitor
 * @author Ana Clara
 */
public class Atendente extends Pessoa {
    private int horarioLivre;
    private Cliente clienteAtual;
    private boolean atendendo;

    /**
     * Construtor da classe Atendente.
     * 
     * @param localizacao a localização do atendente
     * @param nome o nome do atendente
     * @param horarioAtual o horário em que a simulação se encontra ao criar o objeto
     */
    public Atendente(Localizacao localizacao, String nome) {
        super("atendente", localizacao, nome);
        horarioLivre = 0;
        atendendo = false;
    }

    /**
     * Realiza o atendimento de um cliente, definindo o horário em que o atendente estará livre.
     * 
     * @param cliente o cliente a ser atendido
     */
    public void atenderCliente(Cliente cliente) {
        System.out.println("Atendente: " + getNome() + " está atendendo o cliente: " + cliente.getNome());
        horarioLivre += cliente.getDuracaoAtendimento();
        atendendo = true;
        cliente.setAtendido(true);
    }

    /**
     * Verifica se o atendente está livre em um determinado tempo.
     * 
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
     * 
     * @return o cliente atual
     */
    public Cliente getCliente() {
        return clienteAtual;
    }

    public void encerrarAtendimento(Cliente cliente) {
        cliente.setAtendido(false);
        clienteAtual = null;
        atendendo = false;
    }

    public boolean estaAtendendo() {
        return atendendo;
    }

    /**
     * Define o cliente a ser atendido pelo atendente.
     * 
     * @param clienteAtual o cliente atual
     */
    public void setCliente(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }
}
