/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author BD Team
 */
public class BD {

    /**
     * @param args the command line arguments
     */
    
    public static String ini(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\dataBase.db");
            System.out.println("Opened database successfully");
            Statement stmt = null;
            stmt = c.createStatement();
            String sql = "CREATE TABLE PERSONA " +
                   "(ID INT PRIMARY KEY    NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " TELEFONO            TEXT, " + 
                   " CORREO        TEXT);";
            String sql1= "CREATE TABLE BLOQUE " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " TIMESTAMP_INICIO           DATETIME    NOT NULL, " + 
                   " TIMESTAMP_TERMINO            DATETIME    NOT NULL); ";
            String sql2 = "CREATE TABLE RECURSO " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " DESCRIPCION            TEXT, " + 
                   " TIPO_RECURSO        TEXT);";
            String sql3 = "CREATE TABLE R_EVE_BLOQ " +
                   "(FK_ID_EVENTO INT      NOT NULL," +
                   " FK_ID_BLOQUE INT    NOT NULL, " + 
                   " FOREIGN KEY(FK_ID_EVENTO) REFERENCES EVENTO(ID),"+
                   " FOREIGN KEY(FK_ID_BLOQUE) REFERENCES BLOQUE(ID));";
            String sql4 = "CREATE TABLE R_EVE_REC " +
                   "(FK_ID_EVENTO INT      NOT NULL," +
                   " FK_ID_RECURSO INT    NOT NULL, " + 
                   " FOREIGN KEY(FK_ID_EVENTO) REFERENCES EVENTO(ID),"+
                   " FOREIGN KEY(FK_ID_RECURSO) REFERENCES RECURSO(ID));";
            String sql5 = "CREATE TABLE EVENTO " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " RESPONSABLE           INT, " +
                   " BLOQUES           INT, " +
                   " DESCRIPCION            TEXT, " + 
                   " RECURSO            INT,"+ 
                   " FOREIGN KEY(RESPONSABLE) REFERENCES PERSONA(ID),"+
                   " FOREIGN KEY(BLOQUES) REFERENCES BLOQUE(ID),"+
                   " FOREIGN KEY(RECURSO) REFERENCES RECURSO(ID));";
                   
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql5);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return e.getClass().getName() + ": " + e.getMessage();
        }
        System.out.println("Opened database successfully");
        return "OK";
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        String execute = ini();
        System.out.println(execute);
       
    }
    
}
