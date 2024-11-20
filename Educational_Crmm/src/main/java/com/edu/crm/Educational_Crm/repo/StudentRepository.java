package com.edu.crm.Educational_Crm.repo;

import com.edu.crm.Educational_Crm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE "
         + "(:name IS NULL OR s.name = :name) AND "
         + "(:phone IS NULL OR s.phone = :phone) AND "
         + "(:courses IS NULL OR s.courses = :courses) AND "
         + "(:hr IS NULL OR s.hr = :hr) AND "
         + "(:stage IS NULL OR s.stage = :stage)")
    List<Student> findStudents(
        @Param("name") String name,
        @Param("phone") Long phone,
        @Param("courses") String courses,
        @Param("hr") String hr,
        @Param("stage") String stage
    );

	Optional<Student> findById(Long id);

	void deleteById(Long id);

	List<Student> findByStage(String string);
}
