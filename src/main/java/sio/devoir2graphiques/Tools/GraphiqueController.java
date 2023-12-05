package sio.devoir2graphiques.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GraphiqueController
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public GraphiqueController() {
        cnx = ConnexionBDD.getCnx();
    }
    // A vous de jouer

    public HashMap<Integer, Double> getDatasGraphique1()
    {
        HashMap<Integer,Double> datas = new HashMap<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT employe.ageEmp,AVG(employe.salaireEmp)\n" +
                    "FROM employe\n" +
                    "WHERE employe.ageEmp = ?");;
            rs = ps.executeQuery();
            while (rs.next()){
                datas.put(rs.getInt(1), rs.getDouble(2));
            }
            rs.close();
        } catch (SQLException ex){
            throw  new RuntimeException(ex);
        }
        return datas;
    }

    public HashMap<String, Integer> getDatasGgraphique3()
    {
        HashMap<String,Integer> datas = new HashMap<>();
        try {
            ps = cnx.prepareStatement("SELECT employe.sexe, round((count(*) / (select count(*) as total from employe)*100),2)\n" +
                    "from employe\n" +
                    "GROUP BY employe.sexe");
            rs = ps.executeQuery();
            while (rs.next()){
                datas.put(rs.getString("sexe"),rs.getInt("total"));
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return datas;
    }

    public HashMap<String, Integer> getDatasGgraphique2()
    {
        HashMap<String,Integer> datas = new HashMap<>();
        try {
            ps = cnx.prepareStatement("");
            rs = ps.executeQuery();
            while (rs.next()){
                datas.put(rs.getString("sexe"),rs.getInt("total"));
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return datas;
    }
}
