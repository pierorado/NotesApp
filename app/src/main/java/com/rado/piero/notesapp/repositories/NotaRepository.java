package com.rado.piero.notesapp.repositories;

import com.orm.SugarRecord;
import com.rado.piero.notesapp.models.Nota;

import java.util.List;

public class NotaRepository {
    public static List<Nota> list(){
        List<Nota> notas = SugarRecord.listAll(Nota.class);
        return notas;
    }

    public static Nota read(Long id){
        Nota nota = SugarRecord.findById(Nota.class, id);
        return nota;
    }

    public static void create(String titulo, String contenido){
        Nota nota = new Nota();
        nota.setTitulo(titulo);
        nota.setContenido(contenido);
        SugarRecord.save(nota);
    }

    public static void update(String titulo, String contenido, Long id){
        Nota nota = SugarRecord.findById(Nota.class, id);
        nota.setTitulo(titulo);
        nota.setContenido(contenido);
        SugarRecord.save(nota);
    }

    public static void delete(Long id){
        Nota nota = SugarRecord.findById(Nota.class, id);
        SugarRecord.delete(nota);
    }

}
