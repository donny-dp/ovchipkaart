import reiziger.dao.ReizigerDAO;
import reiziger.dao.ReizigerDAOPsql;
import reiziger.domein.Reiziger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        try {
            String url = "jdbc:postgresql://localhost:5432/ovchip";

            Connection connection = DriverManager.getConnection(url, "postgres", "secret");

            ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection);

            testReizigerDAO(reizigerDAO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


        System.out.println("[Test] ReizigerDAO.update() geeft Sietske een nieuwe achternaam");
        sietske.achternaam = "Jansen";
        rdao.update(sietske);
        System.out.println(rdao.find(77));

        System.out.println();

        Reiziger nieuweReiziger = rdao.find(77);
        System.out.print("[Test] ReizigerDAO.find(77) geeft de volgende reiziger:");

        System.out.println(nieuweReiziger.toString());

        System.out.println();

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete(77) ");
        rdao.delete(77);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

    }
}
