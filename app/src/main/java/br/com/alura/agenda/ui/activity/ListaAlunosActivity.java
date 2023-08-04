package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.R.layout.activity_lista_alunos;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

@SuppressWarnings("CommentedOutCode")
public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Alunos";
    private ListaAlunosView listaAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_lista_alunos);
        setTitle(TITLE_APPBAR);
        listaAlunosView = new ListaAlunosView(this);
        configFABNovoAluno();
        configLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configFABNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsert());
    }

    private void abreFormularioModoInsert() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    private void configLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista);
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configItemClick(listaDeAlunos);
        //configItemLongClick(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configItemClick(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, pos, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(pos);
            abreFormularioModoEdit(alunoEscolhido);
        });
    }

    private void abreFormularioModoEdit(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }

}
