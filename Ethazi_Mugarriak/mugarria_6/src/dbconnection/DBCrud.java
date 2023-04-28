package dbconnection;

import model.Photographer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCrud {
    private Connection conn;
    private static final String QUERY_GET_ALL_PHOTOGRAPHERS = "SELECT name_ FROM Photographers";
    private static final String QUERY_UPDATE_PICTURE_VISITS = "UPDATE Pictures SET visits=(visits+1) WHERE title=?";

    public DBCrud(Connection conn){
        this.conn = conn;
    }

    public List<Photographer> getPhotographers(){
        List<Photographer> photographers = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(QUERY_GET_ALL_PHOTOGRAPHERS)) {
            rs = st.executeQuery();

            while(rs.next()){
                photographers.add(sortuPhotographer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photographers;
    }

    private Photographer sortuPhotographer(ResultSet rs) throws SQLException {
        int photographerId = rs.getInt("photographersId");
        String name = rs.getString("name_");
        boolean awarded = rs.getBoolean("awarded");

        return new Photographer(photographerId,name,awarded);
    }
}
