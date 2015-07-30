package at.post.postmen.data;

/**
 * Created by Michael on 04.07.2015.
 */
public class Adress {
    private long id;
    private  String street;
    private String number;
    private int parcels;
    private int money;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getParcels() {
        return parcels;
    }

    public void setParcels(int parcels) {
        this.parcels = parcels;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        String adresse = street + " " + number + "\n" + " Pakete: " + Integer.toString(parcels);
        return adresse;
    }

}
