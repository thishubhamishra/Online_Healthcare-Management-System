package com.healthcare.dao;

import com.healthcare.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDAO {
    boolean bookAppointment(Appointment appointment) throws Exception;
    List<Appointment> getAppointmentsForPatient(int patientId) throws Exception;
    boolean cancelAppointment(int appointmentId, int patientId) throws Exception;
    boolean isSlotAvailable(int doctorId, LocalDate date, String timeSlot) throws Exception;
}
