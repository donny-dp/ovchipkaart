import adres.dao.AdresDAO;
import adres.dao.AdresDAOPsql;
import adres.domein.Adres;
import reiziger.dao.ReizigerDAO;
import reiziger.dao.ReizigerDAOPsql;
import reiziger.domein.Reiziger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static Connection connection;

    public static void main(String args[]) {
        try {
            getConnection();

            AdresDAO adresDAO = new AdresDAOPsql(connection);
            ReizigerDAO reizigerDAO = new ReizigerDAOPsql(connection, adresDAO);


            testReizigerDAO(reizigerDAO);
            testAdresDAO(adresDAO, reizigerDAO);

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ovchip";

        connection = DriverManager.getConnection(url, "postgres", "secret");
    }

    private static void closeConnection() throws SQLException {
        connection.close();
    }


    /**
     * P2. Reiziger DAO: persistentie van een klasse
     * <p>
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
        sietske.setAchternaam("Jansen");
        rdao.update(sietske);
        System.out.println(rdao.findById(77));

        System.out.println();

        Reiziger nieuweReiziger = rdao.findById(77);
        System.out.print("[Test] ReizigerDAO.find(77) geeft de volgende reiziger:");

        System.out.println(nieuweReiziger.toString());

        System.out.println();

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete(77) ");
        rdao.delete(rdao.findById(77));
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

    }

    private static void testAdresDAO(AdresDAO adresDAO, ReizigerDAO reizigerDAO) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");
        List<Adres> adressen = adresDAO.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres adres : adressen) {
            System.out.println(adres);
        }
        System.out.println();

        Adres adres = new Adres(20, "3564DF", "23", "Kanarielaan", "Terneuzen", 10);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adresDAO.save(adres);
        adressen = adresDAO.findAll();
        System.out.println(adressen.size() + " adressen\n");

        System.out.println("[Test] adresDAO.findByReiziger() geeft het volgende resultaat");
        Reiziger reiziger = reizigerDAO.findById(10);
        System.out.println(adresDAO.findByReiziger(reiziger));

        System.out.println();

        System.out.println("[Test] Adres voor de AdresDAO.update()");
        System.out.println(adresDAO.findById(20));
        adres.setHuisnummer("67");
        adresDAO.update(adres);

        System.out.println();

        System.out.println("[Test] Adres na de AdresDAO.update()");
        System.out.println(adresDAO.findById(20));

        System.out.println();

        adressen = adresDAO.findAll();
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.delete() ");
        adresDAO.delete(adres);
        adressen = adresDAO.findAll();
        System.out.println(adressen.size() + " adressen");
    }
}
