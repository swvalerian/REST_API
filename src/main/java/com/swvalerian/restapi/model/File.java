package com.swvalerian.restapi.model;

import javax.persistence.*;

@Entity
@Table(name = "Files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ref")
    private String reference;

//    @OneToOne (mappedBy = "file")
    @OneToOne(cascade = CascadeType.DETACH) // чтоб не удалялись
    @JoinColumn(name="id")
    private Event event;

    public File() {
    }

    public File(Integer id, String reference) {
        this.id = id;
        this.reference = reference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "\nFile{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                '}' + "\n";
    }
}
