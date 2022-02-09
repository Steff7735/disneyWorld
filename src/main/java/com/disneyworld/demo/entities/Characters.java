
package com.disneyworld.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Characters implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    private Integer weight;

    private String history;

    @Column(name = "films_A")
    private String filmsA;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany( mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Films> filmsCharacters = new ArrayList();

}
