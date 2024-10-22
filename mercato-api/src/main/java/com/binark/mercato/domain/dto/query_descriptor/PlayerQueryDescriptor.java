package com.binark.mercato.domain.dto.query_descriptor;

import io.github.binark.querypredicate.annotation.EntityFieldName;
import io.github.binark.querypredicate.descriptor.QueryDescriptor;
import io.github.binark.querypredicate.filter.IntegerFilter;
import io.github.binark.querypredicate.filter.LocalDateFilter;
import io.github.binark.querypredicate.filter.StringFilter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The query descriptor
 * <p>This particular class is used to build Spring Specification for advanced jpa queries</p>
 *
 * @see <a href="https://github.com/binark/query-predicate">Query Predicate</a>
 */
@Getter
@Setter
public class PlayerQueryDescriptor implements QueryDescriptor {
    private StringFilter id;
    private StringFilter lastname;
    private StringFilter firstname;
    @EntityFieldName("playerName")
    private StringFilter nickName;
    private IntegerFilter jerseyNumber;
    private LocalDateFilter<LocalDate> birthdate;
    private LocalDateFilter<LocalDate> registrationDate;
}
