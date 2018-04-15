package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Provider implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @NotNull
    private String mail;

    @NotNull
    private String type;

    @NotNull
    private float price;

    @ManyToOne
    private Event event;

    public Provider() {
    }

    public Provider(String name, String adress, String phone, String mail, String type, float price) {
        this.name = name;
        this.address = adress;
        this.phone = phone;
        this.mail = mail;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Float.compare(provider.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), provider.getName()) &&
                Objects.equals(getAddress(), provider.getAddress()) &&
                Objects.equals(getPhone(), provider.getPhone()) &&
                Objects.equals(getMail(), provider.getMail()) &&
                Objects.equals(getType(), provider.getType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAddress(), getPhone(), getMail(), getType(), getPrice());
    }
}
