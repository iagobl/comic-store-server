package com.iagobl.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authorComic", uniqueConstraints = {@UniqueConstraint(name = "unq_author_comic", columnNames = {"comic_id", "author_id"})})
public class AuthorComic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id", foreignKey = @ForeignKey(name = "fk_author_comic_id"))
    private Comic comic;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_author_comic_id"))
    private Author author;

    @NotNull
    private String job;

}
