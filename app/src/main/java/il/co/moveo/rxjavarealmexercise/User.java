package il.co.moveo.rxjavarealmexercise;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    public static final String ID = "id";
    @PrimaryKey
    int id;
    String ImageUrl;
    String FullName;
    String Email;

    public User(int id, String imageUrl, String fullName, String email) {
        this.id = id;
        ImageUrl = imageUrl;
        FullName = fullName;
        Email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
