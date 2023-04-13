package lib;

public class Person {
    private String name;
    private String id;
    private String address;
    private Gender gender;

    private enum Gender {
        LAKI_LAKI,
        PEREMPUAN
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.name = id;
    }
}
