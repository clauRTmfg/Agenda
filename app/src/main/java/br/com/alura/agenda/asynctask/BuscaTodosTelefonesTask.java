package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesEncontradosListener listener;

    public BuscaTodosTelefonesTask(TelefoneDAO telefoneDAO, Aluno aluno,
                                   TelefonesEncontradosListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO
                .buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesEncontradosListener {
        void quandoEncontrados(List<Telefone> telefones);
    }
}
