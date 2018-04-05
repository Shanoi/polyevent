package fr.unice.polytech.isa.teamk.entities;

public class Material {

    private int quantity;
    private String type;

    public Material(int quantity, String type) {
        this.quantity = quantity;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }
}