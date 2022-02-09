
package com.disneyworld.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author fanny
 */

@Entity
@Table(name = "films")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Films implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;

    private String title;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    private Integer stars;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "films_characters",
            joinColumns= @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    
    private List<Characters> characters = new ArrayList<>();

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "films_genders",
            joinColumns= @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "genders_id"))
    
    private List<Genders> genders = new ArrayList<>();

    public void addCharacters(Characters addCharacters) {

        this.characters.add(addCharacters);
    }

    public void addGenders(Genders addGenders) {

        this.genders.add(addGenders);
    }
    public void removeCharacters(Characters charactersToBeRemoved) {

        this.characters.remove(charactersToBeRemoved);
    }
    public void removeGenders(Genders gendersToBeRemoved) {

        this.genders.remove(gendersToBeRemoved);
    }
    
}
