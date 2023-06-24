import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os clientes da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Pedro Ernesto
 */
public class Cliente extends Pessoa{

    public static Random rand;
    private int duracaoAtendimento;
    private int tempoChegada;
    private ContaBancaria conta;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;

    public Cliente(String nome, int tempoChegada, int numeroConta, Localizacao localizacao) {
        super(nome);
        conta = new ContaBancaria(numeroConta);
        this.tempoChegada = tempoChegada;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/cliente.png")).getImage();
    }

    /**
     * 
     * @return Localizacao: localizacao em que o objeto se encontra no mapa
     *  
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
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
     * 
     * @return Image: Pega a imagem relaionada ao objeto
     *  
     */
    public Image getImagem(){
        return imagem;
    }

    /**
     * @param Localizacao Seta a localizacao em que o objeto se encontra
     *  
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
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
