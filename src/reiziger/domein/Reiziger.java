package reiziger.domein;

import java.sql.Date;
import java.time.LocalDate;

public class Reiziger {

    public int reiziger_id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    private LocalDate geboortedatum;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;

        setGeboortedatum(geboortedatum);
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum.toLocalDate();
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    @Override
    public String toString() {
        return "Reiziger{ " +
                "reiziger_id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                " }";
    }
}
