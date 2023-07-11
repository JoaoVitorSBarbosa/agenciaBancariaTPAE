/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes, Michael Kolling, Luiz Merschmann, Pedro Ernesto and João Barbosa
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
        itens = new Item[altura][largura];
    }

    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    /**
     * Adiciona objeto Item ao mapa
     * @param Item a ser adicionado
     */
    public void adicionarItem(Item item){
        itens[item.getLocalizacaoAtual().getX()][item.getLocalizacaoAtual().getY()] = item;
    }
    
    /**
     * Remove objeto Item ao mapa
     * @param Item a ser removido
     */
    public void removerItem(Item item){
        itens[item.getLocalizacaoAtual().getX()][item.getLocalizacaoAtual().getY()] = null;
    }
    
    /**
     * Método que atualiza o mapa com as novas posicoes
     */
    public void atualizarMapa(){
        for (Item[] items : itens) {
            for (Item i : items) {
                if (i!=null) {
                    removerItem(i);
                    if (i instanceof Cliente) {
                        Cliente aux = (Cliente) i;
                        aux.mover();
                        if(aux.getLocalizacaoAtual().getY()!=1){
                            adicionarItem(aux);
                        }
                    } else {
                        adicionarItem(i);
                    }
                }
            }
        }
    }
    
    /**
     * Metódo que retorna um item em uma posição x,y caso solicitado
     * @param x
     * @param y
     * @return Item na posição [x][y] do mapa
     */
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    /**
     * @return largura do mapa
     */
    public int getLargura() {
        return largura;
    }

    /**
     * @return altura do mapa
     */
    public int getAltura() {
        return altura;
    }
    
}