package com.distribuida.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;

@ApplicationScoped
public class ManejadorDb {

	
//	// Con BD Mysql
//		@Produces @ApplicationScoped
//		public DataSource db() {
//			
//			BasicDataSource ds= new BasicDataSource();
//			ds.setDriverClassName("com.mysql.jdbc.Driver");
//			ds.setUrl("jdbc:mysql://127.0.0.1:3306/distribuida");
//			ds.setUsername("root");
//			ds.setPassword("123456");
//			
//			return ds;
//		}
		
	
	@Produces
	@ApplicationScoped
	public   DataSource db() {

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://127.0.0.1:5432/distribuida");
		ds.setUsername("postgres");
		ds.setPassword("123456");

		return ds;
	}




	public static void close(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
