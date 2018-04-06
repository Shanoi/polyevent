package fr.unice.polytech.isa.teamk.entities.user;

import fr.unice.polytech.isa.teamk.entities.users.User;

import javax.validation.constraints.NotNull;

public class Responsible {

    @NotNull
    private String id;

    @NotNull
    private String password;

    public Responsible() {

    }

    public Responsible(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        Responsible user = (Responsible) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}