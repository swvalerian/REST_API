package com.swvalerian.restapi.model;

public class Event {
    private Integer fileId;
    private String create;
    private String update;
    private String delete;
    File file;

    public Event() {
    }

    public Event(Integer fileId, String create, String update, String delete, File file) {
        this.fileId = fileId;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.file = file;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "\nEvent{" +
                "fileId=" + fileId +
                ", create='" + create + '\'' +
                ", update='" + update + '\'' +
                ", delete='" + delete + '\'' +
                ", file=" + file +
                '}';
    }
}
