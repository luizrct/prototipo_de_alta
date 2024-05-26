package com.example.prototipodealta;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservasRepository extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reservas.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela Usuarios
    private static final String TABLE_USUARIOS = "Usuarios";
    private static final String COLUMN_USER_ID = "Id";
    private static final String COLUMN_USER_NOME = "Nome";
    private static final String COLUMN_USER_SENHA = "Senha";
    private static final String COLUMN_USER_ISADMIN = "IsAdmin";
    private static final String COLUMN_USER_RESERVAS = "Reservas";

    // Tabela Reservas
    private static final String TABLE_RESERVAS = "Reservas";
    private static final String COLUMN_RESERVA_ID = "Id";
    private static final String COLUMN_RESERVA_SALAID = "SalaId";
    private static final String COLUMN_RESERVA_COMECO = "Comeco";
    private static final String COLUMN_RESERVA_FIM = "Fim";

    // Tabela Salas
    private static final String TABLE_SALAS = "Salas";
    private static final String COLUMN_SALA_ID = "Id";
    private static final String COLUMN_SALA_NOME = "Nome";
    private static final String COLUMN_SALA_RESPONSAVEL = "Responsavel";
    private static final String COLUMN_SALA_HORARIOS = "HorariosDisponiveis";

    // Queries para criar as tabelas
    private static final String CREATE_TABLE_USUARIOS =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_NOME + " TEXT, " +
                    COLUMN_USER_SENHA + " TEXT, " +
                    COLUMN_USER_ISADMIN + " BOOLEAN DEFAULT 0, " +
                    COLUMN_USER_RESERVAS + " TEXT);";

    private static final String CREATE_TABLE_RESERVAS =
            "CREATE TABLE " + TABLE_RESERVAS + " (" +
                    COLUMN_RESERVA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RESERVA_SALAID + " INTEGER, " +
                    COLUMN_RESERVA_COMECO + " TEXT, " +
                    COLUMN_RESERVA_FIM + " TEXT);";

    private static final String CREATE_TABLE_SALAS =
            "CREATE TABLE " + TABLE_SALAS + " (" +
                    COLUMN_SALA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SALA_NOME + " TEXT, " +
                    COLUMN_SALA_RESPONSAVEL + " INTEGER, " +
                    COLUMN_SALA_HORARIOS + " TEXT);";

    public ReservasRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_RESERVAS);
        db.execSQL(CREATE_TABLE_SALAS);
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALAS);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Inserir dados de exemplo para Usuarios
        long adminId = insertUser(db, "Admin", "adminpass", true, "");
        long user1Id = insertUser(db, "User1", "user1pass", false, "");
        long user2Id = insertUser(db, "User2", "user2pass", false, "");

        // Inserir dados de exemplo para Salas com horários distintos
        insertSala(db, "Sala A", adminId, "06262024,06272024,06282024,06292024");
        insertSala(db, "Sala B", adminId, "06302024,07012024,07052024,07062024");
        insertSala(db, "Sala C", adminId, "07012024,07022024");
        insertSala(db, "Sala D", adminId, "");
        insertSala(db, "Sala E", adminId, "07012024");

        // Inserir reservas iniciais para os usuários não administradores
        long reserva1 = insertReserva(db, user1Id, 1, "05202024", "06232024");
        long reserva2 = insertReserva(db, user1Id, 2, "05202024", "05222024");
        long reserva3 = insertReserva(db, user2Id, 3, "05272024", "05292024");

        // Atualizar reservas dos usuários com os IDs das reservas
        updateUserReservas(db, user1Id, new long[]{reserva1, reserva2});
        updateUserReservas(db, user2Id, new long[]{reserva3});
    }

    private long insertUser(SQLiteDatabase db, String nome, String senha, boolean isAdmin, String reservas) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NOME, nome);
        values.put(COLUMN_USER_SENHA, senha);
        values.put(COLUMN_USER_ISADMIN, isAdmin ? 1 : 0);
        values.put(COLUMN_USER_RESERVAS, reservas);
        return db.insert(TABLE_USUARIOS, null, values);
    }

    private void insertSala(SQLiteDatabase db, String nome, long responsavel, String horarios) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SALA_NOME, nome);
        values.put(COLUMN_SALA_RESPONSAVEL, responsavel);
        values.put(COLUMN_SALA_HORARIOS, horarios);
        db.insert(TABLE_SALAS, null, values);
    }

    private long insertReserva(SQLiteDatabase db, long usuarioId, int salaId, String comeco, String fim) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESERVA_SALAID, salaId);
        values.put(COLUMN_RESERVA_COMECO, comeco);
        values.put(COLUMN_RESERVA_FIM, fim);
        return db.insert(TABLE_RESERVAS, null, values);
    }

    private void updateUserReservas(SQLiteDatabase db, long usuarioId, long[] reservas) {
        String reservasStr = "";
        for (long reserva : reservas) {
            if (!reservasStr.isEmpty()) {
                reservasStr += ",";
            }
            reservasStr += reserva;
        }
        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_USER_RESERVAS, reservasStr);
        db.update(TABLE_USUARIOS, userValues, COLUMN_USER_ID + "=?", new String[]{String.valueOf(usuarioId)});
    }

    // Método para recuperar dados
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);
    }



    public Cursor getReserva(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RESERVAS + " WHERE " + COLUMN_RESERVA_ID + " IN (SELECT " + COLUMN_USER_RESERVAS + " FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_USER_ID + " = " + userId + ")", null);
    }

    public Cursor listSalas() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SALAS, null);
    }

    public Cursor getSala(long salaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SALAS + " WHERE " + COLUMN_SALA_ID + " = " + salaId, null);
    }

    public boolean reservar(long userId, long salaId, String comeco, String fim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues reservaValues = new ContentValues();
        reservaValues.put(COLUMN_RESERVA_SALAID, salaId);
        reservaValues.put(COLUMN_RESERVA_COMECO, comeco);
        reservaValues.put(COLUMN_RESERVA_FIM, fim);
        long reservaId = db.insert(TABLE_RESERVAS, null, reservaValues);
        if (reservaId == -1) {
            return false;
        }
        // Atualizar os horários disponíveis da sala
        Cursor salaCursor = getSala(salaId);
        if (salaCursor.moveToFirst()) {
            @SuppressLint("Range") String horarios = salaCursor.getString(salaCursor.getColumnIndex(COLUMN_SALA_HORARIOS));
            // Remover os dias reservados da lista de dias disponíveis
            horarios = removeReservados(horarios, comeco, fim);
            ContentValues salaValues = new ContentValues();
            salaValues.put(COLUMN_SALA_HORARIOS, horarios);
            db.update(TABLE_SALAS, salaValues, COLUMN_SALA_ID + " = ?", new String[]{String.valueOf(salaId)});
        }
        // Atualizar as reservas do usuário
        Cursor userCursor = getUserByid(userId);
        if (userCursor.moveToFirst()) {
            @SuppressLint("Range") String userReservas = userCursor.getString(userCursor.getColumnIndex(COLUMN_USER_RESERVAS));
            if (userReservas == null || userReservas.isEmpty()) {
                userReservas = String.valueOf(reservaId);
            } else {
                userReservas += "," + reservaId;
            }
            ContentValues userValues = new ContentValues();
            userValues.put(COLUMN_USER_RESERVAS, userReservas);
            db.update(TABLE_USUARIOS, userValues, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
        }
        return true;
    }

    private String removeReservados(String horarios, String comeco, String fim) {
        int startDate = Integer.parseInt(comeco);
        int endDate = Integer.parseInt(fim);
        String[] horariosArray = horarios.split(",");
        StringBuilder horariosAtualizados = new StringBuilder();

        for (String horario : horariosArray) {
            int dia = Integer.parseInt(horario.trim());
            if (dia < startDate || dia > endDate) {
                if (horariosAtualizados.length() > 0) {
                    horariosAtualizados.append(",");
                }
                horariosAtualizados.append(horario);
            }
        }
        return horariosAtualizados.toString();
    }

    public Cursor getUser(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_USER_NOME + " = ?", new String[]{userName});
    }

    public Cursor getUserByid(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

}
