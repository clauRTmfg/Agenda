package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

public abstract class BaseAlunoTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    protected BaseAlunoTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    protected void vinculaAlunoTelefone(int id, Telefone... telefones) {
        for (Telefone tel :
                telefones) {
            tel.setAlunoId(id);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener{
        void quandoFinalizada();
    }
}
