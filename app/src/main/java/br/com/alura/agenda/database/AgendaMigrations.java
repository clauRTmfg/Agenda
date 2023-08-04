package br.com.alura.agenda.database;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.TipoTelefone;

public class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // PASSOS ABAIXO NECESSARIOS PQ o SQLite não possui o comando DROP COLUMN
            // Criar nova tabela com estrutura desejada
            database.execSQL("CREATE TABLE IF NOT EXISTS `aluno2` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");

            // Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO aluno2 (id, nome, telefone, email) " +
                    "SELECT id, nome, telefone, email FROM aluno");

            // Remover tabela antiga
            database.execSQL("DROP TABLE aluno");

            // Renomear tabela nova com nome da antiga
            database.execSQL("ALTER TABLE aluno2 RENAME TO aluno");

        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN dataCadastro TEXT");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN celular TEXT");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(5,6) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`dataCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, dataCadastro) " +
                    "SELECT id, nome, email, dataCadastro FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Telefone (numero, alunoId) " +
                    "SELECT telefone, id FROM Aluno");

            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[]{FIXO});

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,
    MIGRATION_4_5, MIGRATION_5_6};
}
