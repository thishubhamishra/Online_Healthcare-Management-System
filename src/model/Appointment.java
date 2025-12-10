package com.healthcare.model;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;
    private String timeSlot;
    private String reason;
    private String status; // BOOKED / CANCELLED / COMPLETED

    public Appointment() {}

    public Appointment(int id, int patientId, int doctorId,
                       LocalDate appointmentDate, String timeSlot,
                       String reason, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.reason = reason;
        this.status = status;
    }

    public Appointment(int patientId, int doctorId,
                       LocalDate appointmentDate, String timeSlot,
                       String reason) {
        this(0, patientId, doctorId, appointmentDate, timeSlot, reason, "BOOKED");
    }

    // Getters & setters ...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
