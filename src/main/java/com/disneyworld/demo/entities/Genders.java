
package com.disneyworld.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "genders")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genders SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Genders implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @Column(name = "films_A")
    private String filmsA;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany( mappedBy = "genders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Films> filmsGenders = new ArrayList<>();

    
}
