/**
 * Essa é a classe ContaBancaria, que representa uma conta bancária básica.
 * Descrição detalhada da classe e suas funcionalidades.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class ContaBancaria {
    private int saldo;
    private int numero;
    
    /**
     * Construtor da classe ContaBancaria.
     * Cria uma nova instância de ContaBancaria com o número fornecido.
     * 
     * @param numero O número da conta bancária.
     */
    public ContaBancaria(int numero) {
        this.numero = numero;
        saldo = 0;
    }
    public ContaBancaria(int numero, int saldo){
        this.numero = numero;
        this.saldo = saldo;
    }
    
    /**
     * Retorna o número da conta bancária.
     * 
     * @return O número da conta bancária.
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * Retorna o saldo da conta bancária.
     * 
     * @return O saldo da conta bancária.
     */
    public int getSaldo() {
        return saldo;
    }
    
    /**
     * Realiza um depósito na conta bancária.
     * Adiciona o valor fornecido ao saldo da conta.
     * 
     * @param valor O valor a ser depositado.
     */
    public void depositar(int valor) {
        saldo += valor;
        System.out.println("Depósito concluído com sucesso!");
    }
    
    /**
     * Realiza um saque na conta bancária.
     * Verifica se o saldo é suficiente para o saque e, se sim, subtrai o valor do saldo.
     * 
     * @param valor O valor a ser sacado.
     */
    public void sacar(int valor) {
        if (saldo < valor) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        }
    }
}
