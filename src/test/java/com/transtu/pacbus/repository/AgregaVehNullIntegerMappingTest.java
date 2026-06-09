package com.transtu.pacbus.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import com.transtu.pacbus.dto.AgregaVehFilter;
import com.transtu.pacbus.entity.AgregaVeh;
import com.transtu.pacbus.specification.AgregaVehSpecification;

/**
 * Vérifie que le {@code @Convert} est bien appliqué par Hibernate : une ligne
 * contenant le texte littéral {@code 'NULL'} dans les colonnes entières
 * (kmchas / kmdebann) doit être lue sans {@code DataConversionException},
 * exactement via le chemin Specification utilisé par /api/vehicules/search.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:agregaveh;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
class AgregaVehNullIntegerMappingTest {

    @Autowired
    private AgregaVehFilterRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void insertRowWithLiteralNull() {
        // Insère le texte 'NULL' dans les colonnes entières (cas reproduisant la prod).
        jdbcTemplate.update(
                "insert into agrega_veh (vehnum, kmchas, kmdebann) values (?, 'NULL', 'NULL')",
                "V1");
        jdbcTemplate.update(
                "insert into agrega_veh (vehnum, kmchas, kmdebann) values (?, '12345', '7')",
                "V2");
    }

    @Test
    void filterDoesNotThrowAndMapsLiteralNullToNull() {
        Page<AgregaVeh> page = assertThatCodeReturns(() ->
                repository.findAll(AgregaVehSpecification.build(new AgregaVehFilter()),
                        PageRequest.of(0, 10)));

        assertThat(page.getTotalElements()).isEqualTo(2);

        AgregaVeh v1 = page.getContent().stream()
                .filter(v -> "V1".equals(v.getVehnum())).findFirst().orElseThrow();
        assertThat(v1.getKmchas()).isNull();
        assertThat(v1.getKmdebann()).isNull();

        AgregaVeh v2 = page.getContent().stream()
                .filter(v -> "V2".equals(v.getVehnum())).findFirst().orElseThrow();
        assertThat(v2.getKmchas()).isEqualTo(12345);
        assertThat(v2.getKmdebann()).isEqualTo(7);
    }

    private Page<AgregaVeh> assertThatCodeReturns(java.util.function.Supplier<Page<AgregaVeh>> supplier) {
        Page<AgregaVeh>[] holder = new Page[1];
        assertThatCode(() -> holder[0] = supplier.get()).doesNotThrowAnyException();
        return holder[0];
    }
}
