package vinnsla;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Askrifandi {

    private final StringProperty nafnProperty = new SimpleStringProperty();


    public Askrifandi(String nafn) {
        setNafn(nafn);
    }



    public String getNafn() {
        return nafnProperty.get();
    }

    public void setNafn(String nafn) {
        nafnProperty.set(nafn);
    }


}
