package dbconnection;

import model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBCrud {
    private Connection conn;
    private static final String GET_ALL_PHOTOGRAPHER_NAMES = "SELECT name_ FROM Photographers";
    private static final String GET_PICTURES_BY_PHOTOGRAPHER = "SELECT * FROM Pictures WHERE photographerId = ?";
    private static final String GET_PHOTOGRAPHER_ID = "SELECT photographerId FROM Photographers WHERE name_ = ?";
    private static final String GET_PICTURES_BY_PHOTOGRAPHER_AND_DATE = "SELECT * FROM Pictures WHERE photographerId = ? AND date_ > ?";
    private static final String UPDATE_PICTURE_VISITS = "UPDATE Pictures SET visits=(visits+1) WHERE pictureId = ?";
    private static final String GET_VISITS_OF_ALL_PHOTOGRAPHERS = "SELECT visits, photographerId FROM Pictures;";
    private static final String GET_PICTURES_WITHOUT_VISITS_AND_NOT_AWARDED = "SELECT pictureId, title FROM Pictures WHERE visits = ? AND photographerId = ?";
    private static final String GET_PHOTOGRAOGERS_NOT_AWARDED = "SELECT photographerId FROM Photographers WHERE awarded = ?";
    private static final String REMOVE_PICTURE_BY_ID = "DELETE FROM Pictures WHERE pictureId = ?";
    private static final String REMOVE_PHOTOGRAPHER_WITHOUT_PICTURES = "DELETE FROM Photographers WHERE photographerId = ?";
    private static final String GET_PHOTOGRAPHER_HAS_PICTURES  = "SELECT count(photographerId) FROM Pictures WHERE photographerId = ?";
    private static final String UPDATE_PHOTOGRAPHER_AWARDED = "UPDATE Photographers SET awarded = ? WHERE photographerId = ?";

    public DBCrud(Connection conn){
        this.conn = conn;
    }


    public List<String> getPhotographerNames(){
        List<String> photographerNames = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(GET_ALL_PHOTOGRAPHER_NAMES)){
            rs = st.executeQuery();

            while(rs.next()){
                photographerNames.add(rs.getString("name_"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photographerNames;
    }

    public List<Picture> getPicuresByPhotographer(String photographerName){
        List<Picture> pictures = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(GET_PICTURES_BY_PHOTOGRAPHER)) {
            st.setInt(1, getPhotographerId(photographerName));
            rs = st.executeQuery();

            while(rs.next()){
                pictures.add(sortuPicture(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pictures;
    }

    public List<Picture> getPicuresByPhotographerAndDate(String photographerName, String date){
        List<Picture> pictures = new ArrayList<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(GET_PICTURES_BY_PHOTOGRAPHER_AND_DATE)) {
            st.setInt(1, getPhotographerId(photographerName));
            st.setString(2,date);
            rs = st.executeQuery();

            while(rs.next()){
                pictures.add(sortuPicture(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pictures;
    }

    public int getPhotographerId(String photographerName){
        ResultSet rs = null;
        int photographerId = 0;

        try(PreparedStatement st = conn.prepareStatement(GET_PHOTOGRAPHER_ID)){
            st.setString(1, photographerName);
            rs = st.executeQuery();

            if(rs.next()){
                photographerId = rs.getInt("photographerId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photographerId;
    }

    public void updateVisits(int pictureId){
        try(PreparedStatement st = conn.prepareStatement(UPDATE_PICTURE_VISITS)) {
            st.setInt(1, pictureId);
            int updated = st.executeUpdate();
            System.out.println(updated+ " column updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, Integer> createVisitsMap(){
        Map<Integer, Integer> visitsMap = new HashMap<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(GET_VISITS_OF_ALL_PHOTOGRAPHERS)) {
            rs = st.executeQuery();

            while(rs.next()){
                int photographerId = rs.getInt("photographerId");
                int visits = rs.getInt("visits");
                if(visitsMap.containsKey(photographerId)){
                    int actualValue = visitsMap.get(photographerId);
                    visitsMap.put(photographerId, actualValue + visits);
                }else{
                    visitsMap.put(photographerId, visits);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visitsMap;
    }

    public Map<Integer, String> getPicturesWithoutVisitsMap(int photographerId){
        Map<Integer, String> pictures = new HashMap<>();
        ResultSet rs = null;

        try(PreparedStatement st = conn.prepareStatement(GET_PICTURES_WITHOUT_VISITS_AND_NOT_AWARDED)) {
            st.setBoolean(1, false);
            st.setInt(2,photographerId);
            rs = st.executeQuery();

            while(rs.next()){
                pictures.put(rs.getInt("pictureId"), rs.getString("title"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pictures;
    }


    public List<Integer> getPhotographersNotAwarded() {
        List<Integer> photographers = new ArrayList<>();

        try(PreparedStatement st = conn.prepareStatement(GET_PHOTOGRAOGERS_NOT_AWARDED)) {
            st.setBoolean(1,false);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                photographers.add(rs.getInt("photographerId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photographers;
    }

    public void removePicture(int pictureId) {
        try(PreparedStatement st = conn.prepareStatement(REMOVE_PICTURE_BY_ID)) {
            st.setInt(1, pictureId);
            int deleted = st.executeUpdate();
            System.out.println(deleted > 0 ? "Succesfuly deleted" : "Not deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getPhotographerHasPictures(int photographerId){
        boolean verify = true;
        try(PreparedStatement st = conn.prepareStatement(GET_PHOTOGRAPHER_HAS_PICTURES)) {
            st.setInt(1, photographerId);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                if(rs.getInt(1)==0){
                    verify = false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return verify;
    }

    public void removePhotographerWithoutPictures(int photographerId){
        try(PreparedStatement st = conn.prepareStatement(REMOVE_PHOTOGRAPHER_WITHOUT_PICTURES)) {
            st.setInt(1,photographerId);
            int deleted = st.executeUpdate();
            System.out.println(deleted > 0 ? "\nSuccesfuly deleted  photographer "+photographerId : "Not deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updatePhotographerAwarded(int photographerId) {
        try(PreparedStatement st = conn.prepareStatement(UPDATE_PHOTOGRAPHER_AWARDED)) {
            st.setBoolean(1, true);
            st.setInt(2, photographerId);
            int updated = st.executeUpdate();
            System.out.println(updated+ " column updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Picture sortuPicture(ResultSet rs) throws SQLException {
        int pictureId = rs.getInt("pictureId");
        String title = rs.getString("title");
        Date date_ = rs.getDate("date_");
        String file = rs.getString("file_");
        int visits = rs.getInt("visits");
        int photographerId = rs.getInt("photographerId");

        return new Picture(pictureId,title,date_,file,visits,photographerId);

    }
}
