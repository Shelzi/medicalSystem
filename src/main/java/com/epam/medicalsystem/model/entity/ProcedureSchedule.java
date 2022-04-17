package com.epam.medicalsystem.model.entity;

public class ProcedureSchedule {
    private long id;
    private Procedure procedure;
    private boolean isDone;

    public ProcedureSchedule(long id, Procedure procedure, boolean isDone) {
        this.id = id;
        this.procedure = procedure;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static class Procedure {
        private String name;
        private String description;

        public Procedure(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Procedure{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProcedureSchedule{" +
                "id=" + id +
                ", procedure=" + procedure +
                ", isDone=" + isDone +
                '}';
    }
}