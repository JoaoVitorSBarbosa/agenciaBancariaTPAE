import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os clientes da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Pedro Ernesto
 */
public class Item {

    private Localizacao localizacaoAtual;
    private Image imagem;
    private String descricao;

    public Item(String descricao, Localizacao localizacao) {
        this.localizacaoAtual = localizacao;
        this.descricao = descricao;
        imagem = new ImageIcon(getClass().getResource("Imagens/" + descricao + ".png")).getImage();
    }

    /**
     * @return Localizacao: localizacao em que o objeto se encontra no mapa
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }
    
    /**
     * @return Image: Pega a imagem relacionada ao objeto
     */
    public Image getImagem(){
        return imagem;
    }

    /**
     * @param Localizacao Seta a localizacao em que o objeto se encontra
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    @Override
    public String toString() {
        return "Item: " + descricao + "\n";
    }
}
