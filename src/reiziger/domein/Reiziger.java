package reiziger.domein;

import adres.domein.Adres;
import ovchipkaart.domein.OVChipkaart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
//    public List<OVChipkaart> ovChipkaarten = new ArrayList<>();

//    public Adres adres;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        setId(id);
        setVoorletters(voorletters);
        setTussenvoegsel(tussenvoegsel);
        setAchternaam(achternaam);
        setGeboortedatum(geboortedatum);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum.toLocalDate();
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }


    @Override
    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
//                ", ovChipkaarten=" + ovChipkaarten +
//                ", adres=" + adres +
                '}';
    }
}
