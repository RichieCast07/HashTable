package src.Business;

public class Business {
    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String state;

    public Business(String id, String name, String address, String city, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nombre: " + name + "\n" +
                "Direccion: " + address + "\n" +
                "Ciudad: " + city + "\n" +
                "Estado: " + state;
    }
}
