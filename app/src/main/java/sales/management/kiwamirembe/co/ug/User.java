package sales.management.kiwamirembe.co.ug;

public class User {

    public int user_id;
    public String user_identification;
    public String name;
    public String truckID;

    public User() {
    }

    public User(int user_id, String user_identification, String name, String truckID) {
        this.user_id = user_id;
        this.user_identification = user_identification;
        this.name = name;
        this.truckID = truckID;
    }

    public User(String user_identification, String name, String truckID) {
        this.user_id = user_id;
        this.user_identification = user_identification;
        this.name = name;
        this.truckID = truckID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_identification() {
        return user_identification;
    }

    public void setUser_identification(String user_identification) {
        this.user_identification = user_identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTruckID() {
        return truckID;
    }

    public void setTruckID(String truckID) {
        this.truckID = truckID;
    }
}
