package com.healthcare.dao;

import com.healthcare.model.Appointment;
import com.healthcare.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    private static final String INSERT_SQL =
            "INSERT INTO appointments (patient_id, doctor_id, appointment_date, time_slot, reason) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_PATIENT_SQL =
            "SELECT * FROM appointments WHERE patient_id = ? ORDER BY appointment_date DESC";
    private static final String CANCEL_SQL =
            "UPDATE appointments SET status = 'CANCELLED' WHERE id = ? AND patient_id = ?";
    private static final String SLOT_CHECK_SQL =
            "SELECT COUNT(*) FROM appointments " +
            "WHERE doctor_id = ? AND appointment_date = ? AND time_slot = ? AND status = 'BOOKED'";

    @Override
    public boolean bookAppointment(Appointment appointment) throws Exception {
        if (!isSlotAvailable(appointment.getDoctorId(),
                appointment.getAppointmentDate(), appointment.getTimeSlot())) {
            return false; // innovation: prevent double booking
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            ps.setString(4, appointment.getTimeSlot());
            ps.setString(5, appointment.getReason());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws Exception {
        List<Appointment> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_PATIENT_SQL)) {

            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appt = new Appointment();
                    appt.setId(rs.getInt("id"));
                    appt.setPatientId(rs.getInt("patient_id"));
                    appt.setDoctorId(rs.getInt("doctor_id"));
                    appt.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
                    appt.setTimeSlot(rs.getString("time_slot"));
                    appt.setReason(rs.getString("reason"));
                    appt.setStatus(rs.getString("status"));
                    list.add(appt);
                }
            }
        }

        return list;
    }

    @Override
    public boolean cancelAppointment(int appointmentId, int patientId) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(CANCEL_SQL)) {

            ps.setInt(1, appointmentId);
            ps.setInt(2, patientId);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public boolean isSlotAvailable(int doctorId, LocalDate date, String timeSlot) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SLOT_CHECK_SQL)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, timeSlot);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }
        }
        return false;
    }
}
