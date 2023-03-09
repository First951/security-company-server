package com.first951.securitycompanyserver.schema.organization;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("""
            SELECT
                o
            FROM
                Organization o
            WHERE
                (:address IS NULL OR o.address LIKE %:address%)
            AND
                (:name IS NULL OR o.name LIKE %:name%)
            ORDER BY
                o.id
            """)
    List<Organization> search(@Param("address") String address, @Param("name") String name,
                              Pageable pageable);

}
