package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;

import java.util.Optional;

@Component
public class RacingGameDao {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingGameDao(final JdbcTemplate template) {
        this.template = template;
        this.simpleJdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id", "created_at");
    }

    public Long save(final RacingGameEntity racingGameEntity) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(racingGameEntity);
        return (Long) simpleJdbcInsert.executeAndReturnKeyHolder(parameters)
                .getKeys()
                .get("id");
    }

    public Optional<RacingGameEntity> findById(final Long id) {
        return Optional.ofNullable(
                template.queryForObject("SELECT * FROM PLAY_RESULT WHERE id = ?",
                        (rs, rowNum) -> new RacingGameEntity(
                                rs.getLong("id"),
                                rs.getTimestamp("created_at").toLocalDateTime(),
                                rs.getInt("trial_count")
                        ), id)
        );
    }
}
