package com.iagobl.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "surname")
    private String surname;

    //@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    //private List<>
}
