package paczka8;

import java.io.Serializable;
import java.util.Date;


public class Notyfikacja implements Serializable  {
     public Date data;
     public String tekst;

     public String getTekst() {
         return this.tekst;
     }
    public Notyfikacja(Date v, String s) {
        this.data = v;
        this.tekst = s;
    }
    public int compareTo(Notyfikacja porownaj) {
        if(this.data.compareTo(porownaj.data)>0)
            return 1;
        else if(this.data.compareTo(porownaj.data)<0)
            return -1;
        else
            return 0;
    }
    public int compareDate(Date porownaj) throws DatowyException{
         long helper = this.data.getTime()-porownaj.getTime();
        if(Math.abs(helper)<=1000) {
            return 1;
        }
        else if(helper <0 ) {
            throw new DatowyException();
        }
        else
            return 0;
    }

}