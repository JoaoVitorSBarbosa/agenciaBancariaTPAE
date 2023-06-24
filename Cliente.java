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

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    } 
    public int getDuracaoAtendimento(){
        return duracaoAtendimento;
    }

    public int getTempoChegada(){
        return tempoChegada;
    }
    @Override
    public String toString() {
        return super.toString() + "\n:Numero conta " + conta.getNumero() + "\nSaldo: "+ conta.getSaldo();
    }
}
