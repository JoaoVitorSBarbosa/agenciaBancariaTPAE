/**
 * Essa é a classe Pessoa, que representa uma pessoa genérica.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public abstract class Pessoa {
    private String nome;
    
    /**
     * Construtor da classe Pessoa.
     * Cria uma nova instância de Pessoa com o nome e o código fornecidos.
     * 
     * @param nome O nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna o nome da pessoa.
     * 
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return super.toString() + "\n:Nome: " + nome;
    }
}
