package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", leilao);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("André"),2000.0));
        console.propoe(new Lance(new Usuario("João"),3000.0));
        console.propoe(new Lance(new Usuario("Pedro"),3200.0));
        console.propoe(new Lance(new Usuario("André"),3400.0));

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("André"),20000.0));
        carro.propoe(new Lance(new Usuario("João"),30000.0));
        carro.propoe(new Lance(new Usuario("André"),50000.0));
        carro.propoe(new Lance(new Usuario("João"),51000.0));
        carro.propoe(new Lance(new Usuario("André"),52000.0));

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("André"),200.0));
        computador.propoe(new Lance(new Usuario("João"),300.0));
        computador.propoe(new Lance(new Usuario("André"),400.0));
        computador.propoe(new Lance(new Usuario("João"),500.0));
        computador.propoe(new Lance(new Usuario("André"),700.0));

        return new ArrayList<>(Arrays.asList(
                console,carro,computador
        ));
    }

}
