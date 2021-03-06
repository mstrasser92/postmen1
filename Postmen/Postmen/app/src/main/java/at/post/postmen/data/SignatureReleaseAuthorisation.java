package at.post.postmen.data;

import java.util.List;

/**
 * Created by Michael on 13.07.2015.
 */
public class SignatureReleaseAuthorisation {
    private long id;
    private Adress adress;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public SignatureReleaseAuthorisation(long id, Adress adress, String name) {
//        this.id = id;
//        this.adress = adress;
//        this.name = name;
//    }

    @Override
    public String toString() {
        return name;
    }
}
