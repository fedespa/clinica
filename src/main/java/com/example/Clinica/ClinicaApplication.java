package com.example.Clinica;

import com.example.Clinica.modules.staff.model.DoctorProfile;
import com.example.Clinica.modules.staff.model.DoctorSpecialty;
import com.example.Clinica.modules.staff.model.DoctorSpecialtyEnum;
import com.example.Clinica.modules.staff.repository.DoctorSpecialtyRepository;
import com.example.Clinica.security.user.model.*;
import com.example.Clinica.security.user.repository.PermissionRepository;
import com.example.Clinica.security.user.repository.RoleRepository;
import com.example.Clinica.security.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;


@SpringBootApplication
public class ClinicaApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ClinicaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			UserRepository userRepository,
			RoleRepository roleRepository,
			PermissionRepository permissionRepository,
			DoctorSpecialtyRepository doctorSpecialtyRepository
	) {
		return  args -> {

			if (roleRepository.count() > 0) {
				return;
			}

			PermissionEntity patientRead = PermissionEntity.builder()
					.name(PermissionName.PATIENT_READ)
					.build();
			PermissionEntity patientCreate = PermissionEntity.builder()
					.name(PermissionName.PATIENT_CREATE)
					.build();
			PermissionEntity patientUpdate = PermissionEntity.builder()
					.name(PermissionName.PATIENT_UPDATE)
					.build();
			PermissionEntity userRead = PermissionEntity.builder()
					.name(PermissionName.USER_READ)
					.build();
			PermissionEntity userCreate = PermissionEntity.builder()
					.name(PermissionName.USER_CREATE)
					.build();
			PermissionEntity userUpdate = PermissionEntity.builder()
					.name(PermissionName.USER_UPDATE)
					.build();
			PermissionEntity userDelete = PermissionEntity.builder()
					.name(PermissionName.USER_DELETE)
					.build();
			PermissionEntity roleAssign = PermissionEntity.builder()
					.name(PermissionName.ROLE_ASSIGN)
					.build();
			PermissionEntity exportData = PermissionEntity.builder()
					.name(PermissionName.EXPORT_DATA)
					.build();
			PermissionEntity admissionCreate = PermissionEntity.builder()
					.name(PermissionName.ADMISSION_CREATE)
					.build();
			PermissionEntity admissionUpdate = PermissionEntity.builder()
					.name(PermissionName.ADMISSION_UPDATE)
					.build();
			PermissionEntity admissionClose = PermissionEntity.builder()
					.name(PermissionName.ADMISSION_CLOSE)
					.build();
			PermissionEntity admissionReadAll = PermissionEntity.builder()
					.name(PermissionName.ADMISSION_READ_ALL)
					.build();
			PermissionEntity reportPatients = PermissionEntity.builder()
					.name(PermissionName.REPORT_PATIENTS)
					.build();
			PermissionEntity reportFinancial = PermissionEntity.builder()
					.name(PermissionName.REPORT_FINANCIAL)
					.build();
			PermissionEntity auditLogs = PermissionEntity.builder()
					.name(PermissionName.AUDIT_LOGS)
					.build();

			permissionRepository.saveAll(List.of(
					patientRead, patientCreate, patientUpdate,
					userRead, userCreate, userUpdate,
					userDelete, roleAssign, exportData,
					admissionCreate, admissionUpdate, admissionClose,
					admissionReadAll, reportPatients, reportFinancial,
					auditLogs
			));

			Role rolAdmin = Role.builder()
							.name(RoleName.ROLE_ADMIN)
							.permissions(Set.of(
									patientRead, patientCreate, patientUpdate,
									userRead, userCreate, userUpdate, userDelete,
									roleAssign, exportData, admissionCreate,
									admissionUpdate, admissionClose, admissionReadAll,
									reportPatients, reportFinancial, auditLogs
							))
							.build();
			Role rolDoctor = Role.builder()
							.name(RoleName.ROLE_DOCTOR)
							.permissions(Set.of(
									patientRead, patientUpdate, admissionCreate,
									admissionUpdate, admissionClose, admissionReadAll,
									reportPatients
							))
							.build();
			Role rolNurse = Role.builder()
							.name(RoleName.ROLE_NURSE)
							.permissions(Set.of(
									patientRead, admissionCreate,
									admissionUpdate, admissionReadAll
							))
							.build();

			roleRepository.saveAll(List.of(rolAdmin, rolDoctor, rolNurse));

			DoctorSpecialty cardiologist = DoctorSpecialty.builder()
					.name(DoctorSpecialtyEnum.CARDIOLOGIST)
					.description("Médico especialista en el diagnóstico, tratamiento y prevención de enfermedades del corazón y del sistema circulatorio.")
					.build();

			doctorSpecialtyRepository.saveAll(List.of(cardiologist));

			AppUser userAdmin = AppUser.builder()
					.email("spagnolofede@gmail.com")
					.firstName("Federico")
					.lastName("Spagnolo")
					.password(this.passwordEncoder.encode("roque1819"))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(rolAdmin))
					.build();

			AppUser userDoctor = AppUser.builder()
					.email("eduardo@gmail.com")
					.firstName("Eduardo")
					.lastName("Spagnolo")
					.password(this.passwordEncoder.encode("roque1819"))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(rolDoctor))
					.build();

			DoctorProfile doctorProfile = DoctorProfile.builder()
					.user(userDoctor)
					.yearsOfExperience(25)
					.workStartTime(LocalTime.parse("07:00:00"))
					.workEndTime(LocalTime.parse("15:00:00"))
					.workingDays(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
					.specialties(Set.of(cardiologist))
					.build();

			userDoctor.setDoctorProfile(doctorProfile);

			userRepository.saveAll(List.of(userAdmin, userDoctor));
		};
	};

}
