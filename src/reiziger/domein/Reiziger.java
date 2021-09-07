package reiziger.domein;

import java.time.LocalDate;

public class Reiziger {

    public int reiziger_id;
    public String voorletters;
    public String tussenvoegsel;
    public String achternaam;
    public LocalDate geboortedatum;


    @Override
    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                '}';
    }
}
