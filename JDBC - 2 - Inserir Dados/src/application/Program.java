package application;

import db.DB;
import db.DBException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)");
            st.setString(1, "Pedro Amorim");
            st.setString(2, "amorim@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("29/09/1995").getTime()));
            st.setDouble(4, 15000.0);
            st.setInt(5, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows Affected: " + rowsAffected);

        }catch (SQLException | ParseException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }




    }

}
