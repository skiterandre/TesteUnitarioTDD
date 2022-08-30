package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao leilaoConsole = new Leilao("Console");
    private final Usuario usuarioAndre = new Usuario("Andre");
    private final Usuario usuarioMaria = new Usuario("Maria");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        
        //executar ação esperada
        String descricao = leilaoConsole.getDescricao();
        //testar resultado esperado
        assertEquals("Console", descricao);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeUmValor(){
        insereValorUnico();
        double maiorLanceDevolvido = leilaoConsole.getMaiorLance();

        //delta = a diferença entre valores com ponto flutuante, e se for maior, significa que valores são equivalentes.
        assertEquals(200.0, maiorLanceDevolvido,DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeUmValor(){
        insereValorUnico();
        double menorLanceDevolvido = leilaoConsole.getMenorLance();

        //delta = a diferença entre valores com ponto flutuante, e se for maior, significa que valores são equivalentes.
        assertEquals(200.0, menorLanceDevolvido,DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_ComDoisValoresOrdemCrescente(){
        insereValoresCrescente();

        double maiorLanceDevolvido = leilaoConsole.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido,DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_ComDoisValoresOrdemCrescente(){
        insereValoresCrescente();
        double menorLanceDevolvido = leilaoConsole.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido,DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_ComDoisValoresOrdemDescrescente(){
        insereValoresDecrescentes();
        double maiorLanceDevolvido = leilaoConsole.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido,DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_ComDoisValoresOrdemDescrescente(){
        insereValoresDecrescentes();
        double menorLanceconsoleDevolvido = leilaoConsole.getMenorLance();

        assertEquals(100.0, menorLanceconsoleDevolvido,DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebidoExatosTresLances(){

        leilaoConsole.propoe(new Lance(usuarioAndre, 400.0));
        leilaoConsole.propoe(new Lance(usuarioMaria, 500.0));
        leilaoConsole.propoe(new Lance(usuarioAndre, 600.0));

        List<Lance> tresMaioresLances = leilaoConsole.getTresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(600.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = leilaoConsole.getTresMaioresLances();
        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        leilaoConsole.propoe(new Lance(usuarioAndre, 200.0));
        List<Lance> tresMaioresLances = leilaoConsole.getTresMaioresLances();

        assertEquals(1, tresMaioresLances.size());
        assertEquals(200.0, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances(){
        leilaoConsole.propoe(new Lance(usuarioMaria, 300.0));
        leilaoConsole.propoe(new Lance(usuarioAndre, 200.0));
        List<Lance> tresMaioresLances = leilaoConsole.getTresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(300.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        leilaoConsole.propoe(new Lance(usuarioAndre, 200.0));
        leilaoConsole.propoe(new Lance(usuarioMaria, 300.0));
        leilaoConsole.propoe(new Lance(usuarioAndre, 400.0));
        leilaoConsole.propoe(new Lance(usuarioMaria, 500.0));
        final List<Lance> tresMaioresLancesParaTresLances = leilaoConsole.getTresMaioresLances();

        assertEquals(3, tresMaioresLancesParaTresLances.size());
        assertEquals(500.0, tresMaioresLancesParaTresLances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesParaTresLances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesParaTresLances.get(2).getValor(), DELTA);

        leilaoConsole.propoe(new Lance(usuarioAndre, 700.0));
        final List<Lance> tresMaioresLancesParaCincoLances = leilaoConsole.getTresMaioresLances();

        assertEquals(3, tresMaioresLancesParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesParaCincoLances.get(2).getValor(), DELTA);

    }

    private void insereValorUnico(){
        leilaoConsole.propoe(new Lance(usuarioAndre,200.0));
    }

    private void insereValoresCrescente(){
        leilaoConsole.propoe(new Lance(usuarioAndre,100.0));
        leilaoConsole.propoe(new Lance(usuarioMaria,200.0));
    }

    private void insereValoresDecrescentes(){
        leilaoConsole.propoe(new Lance(usuarioMaria,200.0));
        leilaoConsole.propoe(new Lance(usuarioAndre,100.0));
    }

}