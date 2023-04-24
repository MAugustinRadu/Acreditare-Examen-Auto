package com.example.training2.chestionare;


import jakarta.persistence.*;

@Entity
@Table(name = "chestionare")
public class Intrebare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 255)
    private String intrebare;
    @Column(length = 40,nullable = false)
    private String categoria;
    @Column(length = 255,nullable = false)
    private String optiuneaa;
    @Column(length = 255,nullable = false)
    private String optiuneab;
    @Column(length = 255,nullable = false)
    private String optiuneac;
    @Column
    private boolean optiuneaabool;
    @Column
    private boolean optiuneabbool;
    @Column
    private boolean optiuneacbool;

    public Intrebare(Integer id, String intrebare, String categoria, String optiuneaa, String optiuneab, String optiuneac, boolean optiuneaabool, boolean optiuneabbool, boolean optiuneacbool) {
        this.setId(id);
        this.setIntrebare(intrebare);
        this.setCategoria(categoria);
        this.setOptiuneaa(optiuneaa);
        this.setOptiuneab(optiuneab);
        this.setOptiuneac(optiuneac);
        this.setOptiuneaabool(optiuneaabool);
        this.setOptiuneabbool(optiuneabbool);
        this.setOptiuneacbool(optiuneacbool);
    }

    public Intrebare() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOptiuneaa() {
        return optiuneaa;
    }

    public void setOptiuneaa(String optiuneaa) {
        this.optiuneaa = optiuneaa;
    }

    public String getOptiuneab() {
        return optiuneab;
    }

    public void setOptiuneab(String optiuneab) {
        this.optiuneab = optiuneab;
    }

    public String getOptiuneac() {
        return optiuneac;
    }

    public void setOptiuneac(String optiuneac) {
        this.optiuneac = optiuneac;
    }

    public boolean isOptiuneaabool() {
        return optiuneaabool;
    }

    public void setOptiuneaabool(boolean optiuneaabool) {
        this.optiuneaabool = optiuneaabool;
    }

    public boolean isOptiuneabbool() {
        return optiuneabbool;
    }

    public void setOptiuneabbool(boolean optiuneabbool) {
        this.optiuneabbool = optiuneabbool;
    }

    public boolean isOptiuneacbool() {
        return optiuneacbool;
    }

    public void setOptiuneacbool(boolean optiuneacbool) {
        this.optiuneacbool = optiuneacbool;
    }

    @Override
    public String toString() {
        return "intrebare{" +
                "id=" + getId() +
                ", intrebare='" + getIntrebare() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                ", optiuneaa='" + getOptiuneaa() + '\'' +
                ", optiuneab='" + getOptiuneab() + '\'' +
                ", optiuneac='" + getOptiuneac() + '\'' +
                ", optiuneaabool=" + isOptiuneaabool() +
                ", optiuneabbool=" + isOptiuneabbool() +
                ", optiuneacbool=" + isOptiuneacbool() +
                '}';
    }
}