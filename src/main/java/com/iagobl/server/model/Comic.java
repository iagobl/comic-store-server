package com.iagobl.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull
    private String image;

    @NotBlank
    private String synopsis;

    @NotNull
    private Integer number;

    @NotNull
    private Integer page;

    @NotNull
    private Integer anhoPublication;

    @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AuthorComic> authorComic;

    @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ComicDetails> comicDetails;

    @JsonBackReference (value = "collection1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", foreignKey = @ForeignKey(name = "fk_collection_comic"))
    private Collection collection;

}
