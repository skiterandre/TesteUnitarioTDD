package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public void propoe(Lance lance) {
        lances.add(lance);
        Collections.sort(lances);
        double valorLance = lance.getValor();
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> getTresMaioresLances() {
        int qtdeMaximaLances = lances.size();
        if (qtdeMaximaLances > 3)
            qtdeMaximaLances = 3;

        return lances.subList(0, qtdeMaximaLances);
    }
}
