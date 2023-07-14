package br.ufla.gac111.grupo1;
/**
 * Essa é a classe Pessoa, que representa uma pessoa genérica.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public abstract class Pessoa extends Item{
    private String nome;
    
    /**
     * Construtor da classe Pessoa.
     * Cria uma nova instância de Pessoa com o nome e o código fornecidos.
     * 
     * @param nome O nome da pessoa.
     */
    public Pessoa(String descricao, Localizacao localizacao, String nome) {
        super(descricao, localizacao);
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
    /*
     * Retorna uma String com os estados dos atributos das classes
     */
    @Override
    public String toString() {
        return super.toString() + "Nome: " + nome;
    }
}
