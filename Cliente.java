import java.util.Random;

/**
 * Representa os clientes da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Pedro Ernesto
 */
public class Cliente extends Pessoa{
    public static Random rand;
    private static int id=0;

    private ContaBancaria conta;
    private Localizacao localizacaoDestino;
    private int duracaoAtendimento;
    private int operacao;
    private int userId;

    public Cliente(Localizacao localizacao, String nome, int numeroConta) {
        super("cliente", localizacao, nome);
        rand = new Random();
        conta = new ContaBancaria(numeroConta,rand.nextInt(100,2000));
        localizacaoDestino = null;
        
        duracaoAtendimento = rand.nextInt(50,80);
        operacao = rand.nextInt(2);
        userId = id;
        id++;
    }

    /**
     * @return Localizacao: localizacao para qual o objeto irá no mapa
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * @param Localizacao Seta a localizacao para qual o objeto irá no mapa
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Move o cliente para a proxima localizacao
     */
    public void mover(){
        if(getLocalizacaoDestino() != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    } 

    /**
     * @return int Tempo que durara o atendimento daquele cliente
     */
    public int getDuracaoAtendimento(){
        return duracaoAtendimento;
    }

    /**Realiza operacao na conta bancaria
     * @param inteiro que indica qual operacao realizar:
     * 0: visualizar saldo
     * 1: sacar um valor
     * 2: depositar um valor
     */
    public void operaConta(){
        switch(operacao){
            case 0:
                System.out.println("Sr(a) "+ getNome() + ", seu saldo é de "+ conta.getSaldo()); 
                break;
            case 1:
                conta.sacar(rand.nextInt(50,400));
                break;
            case 2:
                conta.depositar(1320);
                break;
            default: break;
        }
    }
    /*
     * Retorna uma String com os estados dos atributos das classes
     */
    @Override
    public String toString() {
        return super.toString() + "\n:Numero conta " + conta.getNumero() + "\nSaldo: "+ conta.getSaldo();
    }

    /**
     * @return Código identificador do usuário
     */
    public int getId() {
        return userId;
    }
}
