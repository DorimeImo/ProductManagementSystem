package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsServiceDAO implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UserDetailsServiceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> mapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setEnabled(rs.getBoolean(3));
            System.out.println(user.getName()+" "+user.getPassword());
            return user;
        }
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final String sql
                = "select u.userName name, u.userPassword password, u.isEnabled enabled " +
                "from users u where u.userName ='" + username +"'";

        User user= jdbcTemplate
                .queryForObject(sql, mapper);

        return new CustomUserDetails(user);
    }
}
