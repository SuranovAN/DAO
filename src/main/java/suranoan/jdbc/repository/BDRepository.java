package suranoan.jdbc.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Repository
public class BDRepository {
    private String sqlReq = "request.sql";
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BDRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    private String read(String scriptName) {
        try (InputStream is = new ClassPathResource(scriptName).getInputStream()) {
            return String.join("\n", readLines(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readLines(InputStream is) throws IOException {
        StringBuilder buf = new StringBuilder();
        int ch;
        while ((ch = is.read()) != -1) {
            buf.append((char) ch);
        }
        return buf.toString();
    }


    public String getProductName(String name) {
        StringBuilder resp = new StringBuilder();
        SqlRowSet rowSet = namedParameterJdbcTemplate.queryForRowSet(read(sqlReq), Map.of("name", name));
        int columns = rowSet.getMetaData().getColumnCount();
        resp.append("<p>");
        while (rowSet.next()) {
            for (int i = 1; i <= columns; i++) {
                resp.append(rowSet.getString(i)).append("\t");
            }
            resp.append("</p></b>");
        }
        return resp.toString();
    }
}

