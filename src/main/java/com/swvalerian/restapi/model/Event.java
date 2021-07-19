package com.swvalerian.restapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "updated")
    private LocalDateTime updated;
    @Column(name = "deleted")
    private LocalDateTime deleted;

    @OneToOne(cascade = CascadeType.DETACH) // при таком ключе, поле из внешней таблицы не удаляется
    @JoinColumn(name="file_id") // было id стало file_id произвольное имя или как при создании таблицы
    private File file; // или как названо поле в сущности events?

    // подсказано
//    @ManyToOne(cascade = CascadeType.DETACH) // добавил чтобы поле из таблицы User не удалялось
    @ManyToOne // добавил чтобы поле из таблицы User не удалялось
    @JoinColumn(name = "user_id") // здесь указываетcя имя поля в ТАБЛИЦЕ !!!
    private User user;


    public Event() {
    }

    public Event(Integer eventId, LocalDateTime created, LocalDateTime updated, LocalDateTime deleted, File file, User user) {
        this.eventId = eventId;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.file = file;
        this.user = user;
    }

    @Override
    public String toString() {
        return "\nEvent{" +
                "\neventId=" + eventId +
                "\n, created=" + created +
                "\n, updated=" + updated +
                "\n, deleted=" + deleted +
                "\n, file=" + file +
                "\n, user=" + user +
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

