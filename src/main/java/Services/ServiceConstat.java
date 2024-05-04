package Services;

import com.example.finalpidev.RelatedRecordsException;
import utils.MyDB;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import models.constat;

public class ServiceConstat implements IService<constat> {
    private Connection cnn;

    public ServiceConstat() {
        cnn = MyDB.getInstance().getConnection();
    }

    @Override
    public void create(constat constat) throws SQLException {
        String request = "INSERT INTO constat (id, date, lieu, description, conditionroute, photo, rapportepolice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = cnn.prepareStatement(request);

        // Set the values using the appropriate methods of the constat object
        preparedStatement.setInt(1, constat.getId());
        preparedStatement.setObject(2, constat.getDate());
        preparedStatement.setString(3, constat.getLieu());
        preparedStatement.setString(4, constat.getDescription());
        preparedStatement.setString(5, constat.getConditionroute());
        preparedStatement.setString(6, constat.getPhoto());
        preparedStatement.setInt(7, constat.getRapportpolice());

        // Execute the query
        preparedStatement.executeUpdate();

        // Close the PreparedStatement (optional but recommended)
        preparedStatement.close();
    }

    @Override
    public void delete(constat c) throws SQLException, RelatedRecordsException {
        try {
            // Delete the constat record
            PreparedStatement preparedStatement = cnn.prepareStatement("DELETE FROM constat WHERE id=?");
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle the foreign key constraint violation
            throw new RelatedRecordsException("Cannot delete constat record due to existing related records.");
        }
    }



    @Override
    public List<constat> read() throws SQLException {
        String request = "SELECT * FROM constat";
        Statement ste = cnn.createStatement();

        ResultSet res = ste.executeQuery(request);
        List<constat> list = new ArrayList<>();
        while (res.next()) {
            constat sd = new constat();
            sd.setId(res.getInt(1));
            LocalDate date = res.getDate(2).toLocalDate(); // Get the date part
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT); // Combine with midnight time
            sd.setDate(dateTime);
            sd.setLieu(res.getString(3));
            sd.setDescription(res.getString(4));
            sd.setConditionroute(res.getString(5));
            sd.setPhoto(res.getString(7));
            sd.setRapportpolice(res.getInt(6)); // Corrected to getByte for tinyint
            list.add(sd);
        }
        return list;
    }

    @Override
    public void update(constat constat) throws SQLException {
        String request = "UPDATE constat SET lieu=?, description=?, conditionroute=?, photo=?, rapportepolice=? WHERE id=?";
        PreparedStatement pre = cnn.prepareStatement(request);
        pre.setString(1, constat.getLieu());
        pre.setString(2, constat.getDescription());
        pre.setString(3, constat.getConditionroute());
        pre.setString(4, constat.getPhoto());
        pre.setInt(5, constat.getRapportpolice());
        pre.setInt(6, constat.getId());
        pre.executeUpdate();
    }

}