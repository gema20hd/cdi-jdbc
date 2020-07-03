package com.distribuida.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.distribuida.dominio.Persona;
import com.distribuida.servicio.ServicioPersonaImpl;

@WebServlet("/test")
//@WebServlet(urlPatterns = "/test")
public class ServletPersona extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Persona persona=new Persona();
	// public ServicioPersonaImpl servicio=new ServicioPersonaImpl();

	//Aplicamos CDI
	@Inject
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "editar":
				try {
					this.editarPersona(request, response);
				} catch (ServletException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "eliminar":
				try {
					this.eliminarPersona(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				try {
					this.accionDefault(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				this.accionDefault(request, response);

			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

//    //==========================DO POST=============================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			switch (accion) {
			case "insertar":
				try {
					this.insertarPersona(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "modificar":
				try {
					this.modificarPersona(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				try {
					this.accionDefault(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				this.accionDefault(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//    //=================================DEFAULT==================

	private void accionDefault(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("Funcionando");
		List<Persona> personas = null;
		personas = new ServicioPersonaImpl().listar(dataSource.getConnection());
		System.out.println("personas = " + personas);
		HttpSession sesion = req.getSession(); //
		sesion.setAttribute("personas", personas);

//		// request.getRequestDispatcher("clientes.jsp").forward(request, response);
		resp.sendRedirect("personas.jsp");
	}

//    
//    //==========================EDitar======================
	private void editarPersona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// recuperamos el id
		int id = Integer.parseInt(request.getParameter("id"));
		Persona persona = new ServicioPersonaImpl().buscar(new Persona(id), dataSource.getConnection());
		request.setAttribute("persona", persona);
		String jspEditar = "/WEB-INF/paginas/cliente/editarPersona.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	}

//    
//    //===============================================
//    
//    
//    
//	//==============================Eliminar ===========================================
//	
	private void eliminarPersona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
//	        //recuperamos los valores del formulario 
		int id = Integer.parseInt(request.getParameter("id"));
//	     
//
//	        //Creamos el objeto
		Persona persona = new Persona(id);
//
//	        //Eliminamos el  objeto en la base de datos
		int registroEliminado = new ServicioPersonaImpl().delete(persona, dataSource.getConnection());
		System.out.println("registrosModificados = " + registroEliminado);
//
//	        //Redirigimos hacia accion por default
		this.accionDefault(request, response);
	}

//	
//	//====================================INsertar===========
	private void insertarPersona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
//	        //recuperamos los valores del formulario agregar
		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("correo_electronico");
//
//
//	        //Creamos el objeto 
		Persona persona = new Persona(cedula, nombre, direccion, email);
//
//	        //Insertamos el nuevo objeto en la base de datos
		int registrosModificados = new ServicioPersonaImpl().insert(persona, dataSource.getConnection());
		System.out.println("registrosModificados = " + registrosModificados);
//
		// Redirigimos hacia accion por default
		this.accionDefault(request, response);
	}
	
	 private void modificarPersona(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException, SQLException { 
 //recuperamos los valores  del formulario editar
	 int id =Integer.parseInt(request.getParameter("id")); 
	 String cedula =request.getParameter("cedula"); 
	 String nombre =request.getParameter("nombre"); 
	 String direccion =request.getParameter("direccion"); 
	 String email =request.getParameter("correo_electronico"); 
	  //Creamos el objeto de cliente (modelo) 
	 Persona persona= new Persona(id, cedula, nombre, direccion, email);
	  
	  //Modificar el objeto en la base de datos 
	 int registrosModificados =  new ServicioPersonaImpl().update(persona, dataSource.getConnection());
	  System.out.println("registrosModificados = " + registrosModificados);
	  
	  //Redirigimos hacia accion por default this.accionDefault(request, response);
	  }




}
