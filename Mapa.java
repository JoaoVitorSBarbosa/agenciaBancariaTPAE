/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes, Michael Kolling, Luiz Merschmann and João Barbosa
 */
public class Mapa {
    private Item[][] itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 35;
    private static final int ALTURA_PADRAO = 35;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Cliente[altura][largura];
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public void adicionarItem(Cliente cliente){
        itens[cliente.getLocalizacaoAtual().getX()][cliente.getLocalizacaoAtual().getY()] = cliente;
    }
    
    public void removerItem(Cliente cliente){
        itens[cliente.getLocalizacaoAtual().getX()][cliente.getLocalizacaoAtual().getY()] = null;
    }
    
    public void atualizarMapa(Cliente cliente){
        removerItem(cliente);
        adicionarItem(cliente);
    }
    
    public Cliente getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
}
