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
                (:address IS NULL OR lower(o.address) LIKE lower(concat('%', :address, '%')))
            AND
                (:name IS NULL OR lower(o.name) LIKE lower(concat('%', :name, '%')))
            ORDER BY
                o.id
            """)
    List<Organization> search(@Param("address") String address, @Param("name") String name,
                              Pageable pageable);

}
