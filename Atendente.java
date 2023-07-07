/**
 * Classe que representa um atendente.
 * @author João Vitor e Ana Clara
 */
public class Atendente extends Pessoa{
    private int horarioLivre;
    private Cliente clienteAtual;
    /**
     * Construtor da classe Atendente.
     * @param String nome: Nome do atendente.
     * @param int horarioAtual: horario em que a simulação se encontra ao criar o objeto.
     */
    public Atendente(Localizacao localizacao, String nome, int horarioAtual) {
        super("atendente", localizacao, nome);
        horarioLivre = horarioAtual;
    }

    /**
     * Realiza o atendimento de um cliente setando um valor para quando o atendente em questão estará livre.
     * @param Cliente cliente a ser atendido.
     */
    public void atenderCliente(Cliente cliente) {
        System.out.println("Atendente: " + getNome() + ", está atendendo o cliente: " + cliente.getNome());
        horarioLivre = horarioLivre + cliente.getDuracaoAtendimento();
    }

    /**
     * @param int tempoAtual, tempo em atual da simulação
     * @return boolean true se esta livre, false se está ocupado
     */
    public boolean estaLivre(int tempoAtual) {
        return tempoAtual >= horarioLivre;
    }

    @Override
    public String toString() {
        return super.toString() + "Proximo horário: " + horarioLivre;
    }

    public Cliente getCliente() {
        return clienteAtual;
    }
    
    public void setCliente(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }
}
