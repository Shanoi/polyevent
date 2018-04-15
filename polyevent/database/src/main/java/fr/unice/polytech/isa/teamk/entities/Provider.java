package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Provider implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String adress;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
                Objects.equals(getAdress(), provider.getAdress()) &&
                Objects.equals(getPhone(), provider.getPhone()) &&
                Objects.equals(getMail(), provider.getMail()) &&
                Objects.equals(getType(), provider.getType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAdress(), getPhone(), getMail(), getType(), getPrice());
    }
}
