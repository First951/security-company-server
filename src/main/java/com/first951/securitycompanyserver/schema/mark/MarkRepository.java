package com.first951.securitycompanyserver.schema.mark;

import com.first951.securitycompanyserver.schema.mark.type.MarkType;
import com.first951.securitycompanyserver.schema.schedule.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {

    @Query("""
            SELECT
                m
            FROM
                Mark m
            WHERE
                (:schedule IS NULL OR m.schedule = :schedule)
            AND
                ((cast(:planTimestamp AS DATE) IS NULL) OR (cast(m.planTimestamp AS DATE)) < (cast(:planTimestamp AS DATE)))
            AND
                ((cast(:factTimestamp AS DATE) IS NULL) OR (cast(m.factTimestamp AS DATE)) > (cast(:factTimestamp AS DATE)))
            AND
                (:type IS NULL OR m.type = :type)
            AND
                (:comment IS NULL OR lower(m.comment) LIKE lower(concat('%', :comment, '%')))
            ORDER BY
                m.planTimestamp
            """)
    List<Mark> search(@Param("schedule") Schedule schedule, @Param("planTimestamp") Date planTimestamp,
                      @Param("factTimestamp") Date factTimestamp, @Param("type") MarkType type,
                      @Param("comment") String comment, Pageable pageable);

}