package com.edu.crm.Educational_Crm.schedular;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
    Optional<EmailLog> findByStudentIdAndEmailType(int i, String emailType);

	
}
