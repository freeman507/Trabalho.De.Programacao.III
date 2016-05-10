/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import com.mongodb.MongoClient;
import com.mongodb.operation.UpdateOperation;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 *
 * @author freeman
 */
public class Correio {
    private final Morphia morphia;
    private final Datastore datastore;
    private Agenda agenda;
    private Contato contato;

    public Correio() {
        this.morphia = new Morphia();
        morphia.mapPackage("com.mycompany.models");
        datastore = morphia.createDatastore(new MongoClient(), "Agenda_de_Contatos");
        datastore.ensureIndexes();
    }

    public Correio(Agenda agenda) {
        
        this.morphia = new Morphia();
        morphia.mapPackage("com.mycompany.models");
        datastore = morphia.createDatastore(new MongoClient(), "Agenda_de_Contatos");
        datastore.ensureIndexes();
        
        this.agenda = agenda;
    }
    
    public Correio(Morphia morphia, Datastore datastore) {
        this.morphia = morphia;
        this.datastore = datastore;
    }

    public Correio(Morphia morphia, Datastore datastore, Agenda agenda, Contato contato) {
        this.morphia = morphia;
        this.datastore = datastore;
        this.agenda = agenda;
        this.contato = contato;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    public Agenda receberAgenda() {
        this.agenda = new Agenda();
        List<Contato> contatos = datastore.createQuery(Contato.class).asList();
        for(Contato c : contatos) {
            //this.agenda.adicionaContato(c);
            this.agenda.getContatos().add(c);
        }
        return this.agenda;
    }
    
    public void enviarAgenda() {
        ArrayList<Contato> contatos = agenda.getContatos();
        for(Contato c : contatos) {
            datastore.save(c);
        }
    }
    
    public void deletarContato(int contatoId) {
        final Query<Contato> query = datastore.createQuery(Contato.class)
                .filter("id ==", contatoId);
        datastore.delete(query);
    }
    
    public void enviarContato(Contato c) {
        datastore.save(c);
    }
    
    public void atualizarContato(String contatoId, String campo, String valor) {
        final Query<Contato> query = datastore.createQuery(Contato.class)
                .filter("id ==", contatoId);
        final UpdateOperations<Contato> updateOperations = datastore.createUpdateOperations(Contato.class)
                .set(campo, valor);
        final UpdateResults results = datastore.update(query, updateOperations);
    }
}
