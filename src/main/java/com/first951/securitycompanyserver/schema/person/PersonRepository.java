package com.first951.securitycompanyserver.schema.person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
            SELECT
                p
            FROM
                Person p
            WHERE
                (:lastName IS NULL OR p.lastName LIKE %:lastName%)
            AND
                (:firstName IS NULL OR p.firstName LIKE %:firstName%)
            AND
                (:patronymic IS NULL OR p.patronymic LIKE %:patronymic%)
            AND
                (:phoneNumber IS NULL OR p.phoneNumber LIKE %:phoneNumber%)
            ORDER BY
                p.id
            """)
    List<Person> search(@Param("lastName") String lastName, @Param("firstName") String firstName,
                        @Param("patronymic") String patronymic, @Param("phoneNumber") String phoneNumber,
                        Pageable pageable);

}
