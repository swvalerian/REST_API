package com.swvalerian.restapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "updated")
    private LocalDateTime updated;
    @Column(name = "deleted")
    private LocalDateTime deleted;

//    @OneToOne(cascade = CascadeType.DETACH) // при таком ключе, поле из внешней таблицы не удаляется
//    @JoinColumn(name="id")
//    private File file;
    @OneToOne(mappedBy = "event")
    private File file;

    public Event() {
    }

    public Event(Integer eventId, LocalDateTime created, LocalDateTime updated, LocalDateTime deleted, File file) {
        this.eventId = eventId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.file = file;
    }

    @Override
    public String toString() {
        return "\nEvent{" +
                "\neventId=" + eventId +
                "\n, created=" + created +
                "\n, updated=" + updated +
                "\n, deleted=" + deleted +
                "\n, file=" + file +
                '}' +"\n";
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

