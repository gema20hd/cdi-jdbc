package com.distribuida.servicio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.distribuida.dominio.Persona;



public interface ServicioPersona {
	
    public List<Persona> listar(Connection cnn) throws SQLException;
    
    public int insert(Persona persona ,Connection cnn) throws SQLException;
    
    public int update(Persona persona,Connection cnn) throws SQLException;
    
    public abstract int delete(Persona persona,Connection cnn) throws SQLException;
    
    public Persona buscar(Persona persona, Connection cnn);
}
