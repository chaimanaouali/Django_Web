package services;

import java.sql.SQLException;
import java.util.List;

public interface Crud3<T> {
        public void ajouter(T t) throws SQLException;
        public void modifier(T t) throws SQLException;
        public void supprimer(T t) throws SQLException;
        public List<T> afficher() throws SQLException;

}
