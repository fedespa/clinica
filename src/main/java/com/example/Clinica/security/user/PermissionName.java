package com.example.Clinica.security.user;

public enum PermissionName {
    // =========================
    // PATIENT
    // =========================
    PATIENT_READ,
    PATIENT_CREATE,
    PATIENT_UPDATE,

    // =========================
    // USER / EMPLOYEES
    // =========================
    USER_READ,
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
    ROLE_ASSIGN,
    EXPORT_DATA,

    // =========================
    // ADMISSION
    // =========================
    ADMISSION_CREATE,
    ADMISSION_UPDATE,
    ADMISSION_CLOSE,
    ADMISSION_READ_ALL,

    // =========================
    // REPORTS & AUDIT
    // =========================
    REPORT_PATIENTS,
    REPORT_FINANCIAL,
    AUDIT_LOGS
}

/*
 * PATIENT_READ: Ver historial básico del paciente.
 * PATIENT_CREATE: Registrar un nuevo paciente en el sistema.
 * PATIENT_UPDATE: Modificar datos demográficos o de contacto.
 *
 * USER_READ: Ver lista de empleados/usuarios.
 * USER_CREATE: Registrar nuevos empleados.
 * USER_UPDATE: Editar perfiles o cambiar contraseñas.
 * USER_DELETE: Dar de baja a un empleado.
 * ROLE_ASSIGN: Poder cambiar el rol de un usuario.
 * EXPORT_DATA: Capacidad de bajar la base de datos a un Excel.
 *
 * ADMISSION_CREATE: Crear un nuevo ingreso hospitalario.
 * ADMISSION_UPDATE: Cambiar el estado de la admisión (ej: de "Pendiente" a "Activa").
 * ADMISSION_CLOSE: Dar de alta al paciente del hospital.
 * ADMISSION_READ_ALL: Ver todos los ingresos actuales de la clínica.
 *
 * REPORT_PATIENTS: Generar PDF/Excel de pacientes atendidos.
 * REPORT_FINANCIAL: Ver costos de admisiones (si aplica).
 * AUDIT_LOGS: Ver quién hizo qué en el sistema (ideal para el Admin).
 */