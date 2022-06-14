package com.iagobl.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comic")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @Column(length = Integer.MAX_VALUE)
    private byte[] image;

    @NotBlank
    private String synopsis;

    @NotNull
    private Integer number;

    @NotNull
    private Integer page;

    @NotNull
    private String tapa;

    @NotNull
    private Integer anhoPublication;

    @NotNull
    private LocalDate dateAcquistion;

    @NotBlank
    private String state;

    @NotNull
    private double price;

    @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AuthorComic> authorComic;

    @JsonBackReference (value = "collection1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", foreignKey = @ForeignKey(name = "fk_collection_comic"))
    private Collection collection;

}
