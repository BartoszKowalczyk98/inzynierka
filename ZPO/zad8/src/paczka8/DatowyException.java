package paczka8;

public class DatowyException extends Exception {
    public DatowyException(){}
    public  String GetWarning(){
        return "Podana data już minela!";
    }
}
