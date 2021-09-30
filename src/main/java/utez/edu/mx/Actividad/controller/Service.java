package utez.edu.mx.Actividad.controller;


import utez.edu.mx.Actividad.dataBase.ConnectionMysql;
import utez.edu.mx.Actividad.model.Customers;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/customers")
public class Service{
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customers> getEmployees(){
        List<Customers> clientes = new ArrayList<>();
        try{
            con = ConnectionMysql.getConnection();
            String query = "SELECT customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, " +
                    "state, postalCode, country, salesRepEmployeeNumber, creditLimit FROM customers";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                Customers cliente = new Customers();
                cliente.setCustomerNumber(rs.getInt("customerNumber"));
                cliente.setCustomerName(rs.getString("customerName"));
                cliente.setContactLastName(rs.getString("contactLastName"));
                cliente.setContactFirstName(rs.getString("contactFirstName"));
                cliente.setPhone(rs.getString("phone"));
                cliente.setAddressLine1(rs.getString("addressLine1"));
                cliente.setAddressLine2(rs.getString("addressLine2"));
                cliente.setCity(rs.getString("city"));
                cliente.setState(rs.getString("state"));
                cliente.setPostalCode(rs.getString("postalCode"));
                cliente.setCountry(rs.getString("country"));
                cliente.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                cliente.setCreditLimit(rs.getDouble("creditLimit"));
                clientes.add(cliente);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return clientes;
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customers getEmployees(@PathParam("id") int employeeNumber){
        Customers cliente = new Customers();
        try{
            con = ConnectionMysql.getConnection();
            String query = "SELECT customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, " +
                    "state, postalCode, country, salesRepEmployeeNumber, creditLimit FROM customers WHERE customerNumber=?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,employeeNumber);
            rs = pstm.executeQuery();
            if (rs.next()){
                cliente.setCustomerNumber(rs.getInt("customerNumber"));
                cliente.setCustomerName(rs.getString("customerName"));
                cliente.setContactLastName(rs.getString("contactLastName"));
                cliente.setContactFirstName(rs.getString("contactFirstName"));
                cliente.setPhone(rs.getString("phone"));
                cliente.setAddressLine1(rs.getString("addressLine1"));
                cliente.setAddressLine2(rs.getString("addressLine2"));
                cliente.setCity(rs.getString("city"));
                cliente.setState(rs.getString("state"));
                cliente.setPostalCode(rs.getString("postalCode"));
                cliente.setCountry(rs.getString("country"));
                cliente.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                cliente.setCreditLimit(rs.getDouble("creditLimit"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return cliente;
    }

    @POST
    @Path("/{customerNumber}/{customerName}/{contactLastName}/{contactFirstName}/{addressLine1}/{addressLine2}/{phone}/{city}/{state}/{postalCode}/{country}" +
            "/{salesRepEmployeeNumber}/{creditLimit}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("customerNumber") int customerNumber, @PathParam("customerName") String customerName,
                         @PathParam("contactLastName") String contactLastName, @PathParam("contactFirstName") String contactFirstName,
                         @PathParam("phone") String phone, @PathParam("addressLine1") String addressLine1, @PathParam("addressLine2") String addressLine2,
                         @PathParam("city") String city, @PathParam("state") String state,
                         @PathParam("postalCode") String postalCode, @PathParam("country") String country,
                         @PathParam("salesRepEmployeeNumber") int salesRepEmployeeNumber, @PathParam("creditLimit") double creditLimit){
        String salida = "No entro";
        try{
            con = ConnectionMysql.getConnection();
            String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city," +
                    "state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,customerNumber);
            pstm.setString(2,customerName);
            pstm.setString(3, contactLastName);
            pstm.setString(4, contactFirstName);
            pstm.setString(5, phone);
            pstm.setString(6,addressLine1);
            pstm.setString(7, addressLine2);
            pstm.setString(8, city);
            pstm.setString(9, state);
            pstm.setString(10,postalCode);
            pstm.setString(11,country);
            pstm.setInt(12, salesRepEmployeeNumber);
            pstm.setDouble(13, creditLimit);
            if (pstm.executeUpdate() == 1){
                salida = "REGISTRO EXITOSO";
            }else{
                salida = "ERROR :(";
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return salida;
    }

    @PUT
    @Path("/{customerNumber}/{customerName}/{contactLastName}/{contactFirstName}/{addressLine1}/{addressLine2}/{phone}/{city}/{state}/{postalCode}/{country}" +
            "/{salesRepEmployeeNumber}/{creditLimit}")
    @Produces (MediaType.APPLICATION_JSON)
    public String update(@PathParam("customerNumber") int customerNumber, @PathParam("customerName") String customerName,
                         @PathParam("contactLastName") String contactLastName, @PathParam("contactFirstName") String contactFirstName,
                         @PathParam("phone") String phone, @PathParam("addressLine1") String addressLine1, @PathParam("addressLine2") String addressLine2,
                         @PathParam("city") String city, @PathParam("state") String state,
                         @PathParam("postalCode") String postalCode, @PathParam("country") String country,
                         @PathParam("salesRepEmployeeNumber") int salesRepEmployeeNumber, @PathParam("creditLimit") double creditLimit){
        String salida ="NAH";
        try{
            con = ConnectionMysql.getConnection();
            String query = "UPDATE customers SET customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, " +
                    "addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ?, WHERE customerNumber = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1,customerName);
            pstm.setString(2, contactLastName);
            pstm.setString(3, contactFirstName);
            pstm.setString(4, phone);
            pstm.setString(5,addressLine1);
            pstm.setString(6, addressLine2);
            pstm.setString(7, city);
            pstm.setString(8, state);
            pstm.setString(9,postalCode);
            pstm.setString(10,country);
            pstm.setInt(11, salesRepEmployeeNumber);
            pstm.setDouble(12, creditLimit);
            pstm.setInt(13,customerNumber);
            if (pstm.executeUpdate() == 1){
                salida = "ACTUALIZACION EXITOSA";
            }else{
                salida = "FALLO :V";
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return salida;
    }

    @DELETE
    @Path("/{employeeNumber}")
    @Produces (MediaType.APPLICATION_JSON)
    public String delete(@PathParam("employeeNumber") int customerNumber){
        String salida = "MENOS";
        try{
            con = ConnectionMysql.getConnection();
            String query = "DELETE FROM customers WHERE customerNumber = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,customerNumber);
            if (pstm.executeUpdate() == 1){
                salida = "ELIMINACION DE EMPLEADO";
            }else{
                salida = "NO SE ELIMINO XD";
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return salida;
    }



    public void closeConnection(){
        try {
            if (con != null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();

        }
    }

}