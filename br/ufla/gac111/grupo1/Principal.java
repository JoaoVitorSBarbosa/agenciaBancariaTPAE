package br.ufla.gac111.grupo1;
/**
 * @author Luiz Merschmann
 */
public class Principal {
    public static void main(String[] args) {
        Simulacao sim = Simulacao.getInstanceSimulacao(80,9);
        sim.executarSimulacao();
    }
}
