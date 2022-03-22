package settlers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SettlerRepository {

    private JdbcTemplate jdbcTemplate;

    public SettlerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveNewSettler(Settler settler) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO settlers (name_of_settler, amount_of_tobacco, expected_income) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, settler.getNameOfSettler());
            statement.setInt(2, settler.getAmountOfTobacco());
            statement.setInt(3, settler.getExpectedIncome());
            return statement;
        }, holder);
        return getKeyHolder(holder);
    }

    private Long getKeyHolder(KeyHolder holder) {
        try {
            return holder.getKey().longValue();
        } catch (NullPointerException npe) {
            throw new IllegalStateException("Cannot insert!");
        }
    }

    public Settler findSettlerById(long id) {
        return jdbcTemplate.queryForObject("select * from settlers where id = ?",
                (rs, rowNum) -> new Settler(
                        rs.getLong("id"),
                        rs.getString("name_of_settler"),
                        rs.getInt("amount_of_tobacco")
                ), id);
    }

    public void updateGrowthAndIncome(long id, int amount) {
        Settler settler = findSettlerById(id);
        settler.setAmountOfTobacco(settler.getAmountOfTobacco() - amount);
        jdbcTemplate.update("update settlers set amount_of_tobacco = ?, expected_income = ? where id = ?",
                settler.getAmountOfTobacco(), settler.getExpectedIncome(), id);
    }
}
