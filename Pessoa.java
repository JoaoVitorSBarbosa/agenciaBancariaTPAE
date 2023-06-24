/**
 * Essa é a classe Pessoa, que representa uma pessoa genérica.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Pessoa {
    private String nome;
    private int codigo;
    
    /**
     * Construtor da classe Pessoa.
     * Cria uma nova instância de Pessoa com o nome e o código fornecidos.
     * 
     * @param nome O nome da pessoa.
     * @param codigo O código da pessoa.
     */
    public Pessoa(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
    
    /**
     * Retorna o nome da pessoa.
     * 
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Retorna o código da pessoa.
     * 
     * @return O código da pessoa.
     */
    public int getCodigo() {
        return codigo;
    }
}
