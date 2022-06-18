package com.iagobl.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(name = "unq_name_surname", columnNames = {"name", "surname"})})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(length = Integer.MAX_VALUE)
    private byte[] image;

    @Column(name = "surname")
    private String surname;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AuthorComic authorComic;

}
