package com.distribuida.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.distribuida.db.ManejadorDb;
import com.distribuida.dominio.Persona;
import com.mysql.cj.xdevapi.DbDoc;


@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {
	
	@Inject
	private DataSource dataSource;

	// Sentencias sql
	private static final String SQL_SELECT = "SELECT id, cedula, nombre, direccion, correo_electronico FROM persona";
	private static final String SQL_INSERT = "INSERT INTO persona(cedula, nombre, direccion, correo_electronico) VALUES(?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE persona SET cedula=?, nombre=?, direccion=?, correo_electronico=? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM persona WHERE id=?";
    private static final String SQL_SELECT_BY_ID = "SELECT id, nombre, cedula, direccion, correo_electronico  FROM persona WHERE id = ?";

	public ServicioPersonaImpl() {

	}

	@Override
	public List<Persona> listar(Connection cnn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona persona = null;
		List<Persona> personas = new ArrayList<Persona>();

		try {

			stmt = cnn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			//Probar en consola
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String cedula = rs.getString("cedula");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String correoElectronico = rs.getString("correo_electronico");

				persona = new Persona();
				persona.setId(id);
				persona.setCedula(cedula);
				persona.setNombre(nombre);
				persona.setDireccion(direccion);
				persona.setCorreoElectronico(correoElectronico);
				;

				personas.add(persona);
			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			ManejadorDb.close(stmt);
			ManejadorDb.close(rs);
			ManejadorDb.close(cnn);

		}

		return personas;
	}

	@Override
	public int insert(Persona persona,Connection cnn) throws SQLException {

		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = cnn.prepareStatement(SQL_INSERT);
			stmt.setString(1, persona.getCedula());
			stmt.setString(2, persona.getNombre());
			stmt.setString(3, persona.getDireccion());
			stmt.setString(4, persona.getCorreoElectronico());

			System.out.println("ejecutando query:" + SQL_INSERT);
			rows = stmt.executeUpdate();
			System.out.println("Registros afectados:" + rows);
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			ManejadorDb.close(stmt);
			ManejadorDb.close(cnn);
		}

		return rows;
	}

	@Override
	public int update(Persona persona,  Connection cnn) throws SQLException {


		PreparedStatement stmt = null;
		int rows = 0;

		try {
	
			System.out.println("ejecutando query: " + SQL_UPDATE);
			
			stmt = cnn.prepareStatement(SQL_UPDATE);
			stmt.setString(3, persona.getCedula());
			stmt.setString(3, persona.getNombre());
			stmt.setString(4, persona.getDireccion());
			stmt.setString(5, persona.getCorreoElectronico());
			stmt.setInt(1, persona.getId());

			rows = stmt.executeUpdate();
			System.out.println("Registros actualizado:" + rows);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			ManejadorDb.close(stmt);
			ManejadorDb.close(cnn);
		}

		return rows;
	}

	@Override
	public int delete(Persona persona,Connection cnn) throws SQLException {
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = cnn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getId());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
        	ManejadorDb.close(stmt);
        	ManejadorDb.close(cnn);
        }
        
        return rows;
    }
	
	
	@Override
	public Persona buscar(Persona persona, Connection cnn) {
     
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = cnn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1,persona.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String cedula = rs.getString("cedula");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            String email = rs.getString("correo_electronico");


            persona.setCedula(cedula);
            persona.setNombre(nombre);
            persona.setCorreoElectronico(direccion);
            persona.setCorreoElectronico(email);
        

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
        	ManejadorDb.close(rs);
        	ManejadorDb.close(stmt);
        	ManejadorDb.close(cnn);
        }
        return persona;
    }
	

}
