package be.vsb.familydashboard.recipes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private long difficulty;

    @Enumerated(EnumType.STRING)
    @NotNull
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

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", categorie=" + categorie +
                '}';
    }
}
