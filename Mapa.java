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
    
    public void adicionarItem(Item item){
        itens[item.getLocalizacaoAtual().getX()][item.getLocalizacaoAtual().getY()] = item;
    }
    
    public void removerItem(Item item){
        itens[item.getLocalizacaoAtual().getX()][item.getLocalizacaoAtual().getY()] = null;
    }
    
    public void atualizarMapa(){
        for (Item[] items : itens) {
            for (Item i : items) {
                if (i!=null) {
                    removerItem(i);
                    if (i instanceof Cliente) {
                        Cliente aux = (Cliente) i;
                        aux.mover();
                    }
                    adicionarItem(i);
                }
                /*if(i instanceof Cliente){
                    Cliente aux = (Cliente) i;
                    if(!aux.estaSendoAtendido() && aux.getLocalizacaoAtual().equals(aux.getLocalizacaoDestino())){
                        removerItem(aux);
                    }
                }*/
            }
        }
    }
    
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
}
