package br.com.alura.agenda.database;

import static br.com.alura.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.converter.ConversorTipoTelefone;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_DATABASE = "agenda.db";
    private static AgendaDatabase instance;

    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, NOME_DATABASE)
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
                    .addMigrations(TODAS_MIGRATIONS)
                    .build();
        }
        return instance;
    }
}
