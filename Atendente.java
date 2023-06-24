/**
 * Classe que representa um atendente.
 * @author João Vitor e Ana Clara
 */
public class Atendente extends Pessoa{
    private int horarioLivre;
    /**
     * Construtor da classe Atendente.
     * @param String nome: Nome do atendente.
     * @param int horarioAtual: horario em que a simulação se encontra ao criar o objeto.
     */
    public Atendente(String nome, int horarioAtual) {
        super(nome);
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
}
