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
                (:lastName IS NULL OR lower(p.lastName) LIKE lower(concat('%', :lastName, '%')))
            AND
                (:firstName IS NULL OR lower(p.firstName) LIKE lower(concat('%', :firstName, '%')))
            AND
                (:patronymic IS NULL OR lower(p.patronymic) LIKE lower(concat('%', :patronymic, '%')))
            AND
                (:phoneNumber IS NULL OR lower(p.phoneNumber) LIKE lower(concat('%', :phoneNumber, '%')))
            ORDER BY
                p.id
            """)
    List<Person> search(@Param("lastName") String lastName, @Param("firstName") String firstName,
                        @Param("patronymic") String patronymic, @Param("phoneNumber") String phoneNumber,
                        Pageable pageable);

}
