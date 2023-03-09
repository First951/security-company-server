package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.schema.person.Person;
import com.first951.securitycompanyserver.schema.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    @Query("""
            SELECT
                s
            FROM
                Schedule s
            WHERE
                (:post IS NULL OR s.post = :post)
            AND
                (:person IS NULL OR s.person = :person)
            AND
                ((cast(:begin AS DATE) IS NULL) OR (cast(s.begin AS DATE)) < (cast(:end AS DATE)))
            AND
                ((cast(:end AS DATE) IS NULL) OR (cast(s.end AS DATE)) > (cast(:begin AS DATE)))
            ORDER BY
                s.begin
            """)
    List<Schedule> search(@Param("post") Post post, @Param("person") Person person,
                          @Param("begin") Date begin, @Param("end") Date end, Pageable pageable);

}
