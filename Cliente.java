import java.util.Random;

/**
 * Representa os clientes da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Pedro Ernesto
 */
public class Cliente extends Pessoa{

    public static Random rand;
    private int duracaoAtendimento;
    private int tempoChegada;
    private ContaBancaria conta;
    private Localizacao localizacaoDestino;

    public Cliente(Localizacao localizacao, String nome, int tempoChegada, int numeroConta) {
        super("cliente", localizacao, nome);
        conta = new ContaBancaria(numeroConta);
        this.tempoChegada = tempoChegada;
        localizacaoDestino = null;
    }
    /**
     * 
     * @return Localizacao: localizacao para qual o objeto irá no mapa
     *  
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    /**
     * @param Localizacao Seta a localizacao para qual o objeto irá no mapa
     *  
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    /**
     * Move o cliente para a proxima localizacao
     */
    public void mover(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
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
    /**
     * @return int Tempo em que o cliente chegou
     */
    public int getTempoChegada(){
        return tempoChegada;
    }
    @Override
    public String toString() {
        return super.toString() + "\n:Numero conta " + conta.getNumero() + "\nSaldo: "+ conta.getSaldo();
    }
}
