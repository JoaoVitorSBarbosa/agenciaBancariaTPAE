/**
 * Classe que representa um atendente.
 */
public class Atendente {
    private String nome;

    /**
     * Construtor da classe Atendente.
     * @param nome Nome do atendente.
     */
    public Atendente(String nome) {
        this.nome = nome;
    }

    /**
     * Realiza o atendimento de um cliente.
     * @param cliente Cliente a ser atendido.
     */
    public void atenderCliente(Cliente cliente) {
        System.out.println("Atendente " + nome + " está atendendo o cliente " + cliente.getNome());
    }
}
