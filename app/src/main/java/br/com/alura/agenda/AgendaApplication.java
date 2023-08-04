package br.com.alura.agenda;

import android.app.Application;

import androidx.room.Room;

import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.AlunoDAO;

// Esta classe existe apenas para a criação inicial de exemplos.
// Com a utilização do Room ela poderia ser excluida do projeto.
// Vou manter apenas para efeito didático.

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeExemplo();
    }

    private void criaAlunosDeExemplo() {
        AgendaDatabase database = Room
                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
        AlunoDAO dao = database.getAlunoDAO();
        //dao.salva(new Aluno("Clau", "11223344", "clau@gmail.com"));
        //dao.salva(new Aluno("Re", "5566778899", "re@gmail.com"));
        //dao.salva(new Aluno("Thomas", "888888", "thomas@gmail.com"));
    }
}
