package com.example.acreditareExamenAuto.chestionare;


import jakarta.persistence.*;

@Entity
@Table(name = "chestionare")
public class Intrebare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String intrebare;
    @Column(length = 40,nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String optiuneaa;
    @Column(nullable = false)
    private String optiuneab;
    @Column(nullable = false)
    private String optiuneac;

    private boolean optiuneaabool;

    private boolean optiuneabbool;
    private boolean optiuneacbool;

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