package be.vsb.familydashboard.recipes;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long difficulty;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String url;

    protected Recipe() {
    }

    public Recipe(long id, String name, long difficulty, Categorie categorie) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}
