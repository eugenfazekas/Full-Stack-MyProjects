package com.repository.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.model.User;
import com.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
private final Logger log = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
    private JdbcTemplate jdbc;
			
	final RowMapper<User> mapper = new RowMapper<User>() {

		public User mapRow(ResultSet rs ,int rowNum) throws SQLException {
			
			User n = new User();
			
			n.setId(rs.getString("id"));
			n.setUsername(rs.getString("username"));
			n.setPassword(rs.getString("password"));
			n.setAuthorities(Arrays.asList(rs.getString("authorities").split(" ")));
			
			return n;
		}
	};

	public User findById(String id) {
		
		User user = null;
		final String  sql ="SELECT * FROM users WHERE id = ?";
		try {
			user = jdbc.queryForObject(sql, mapper, id);
		}catch(EmptyResultDataAccessException e) {
			log.debug("Fullname: "+ id +" Not Fonud!");
			}
		return user;
	}

	public User findByEmail(String email) {
		
		User user = null;
		final String  sql ="SELECT * FROM users WHERE email = ?";
		try {
			user = jdbc.queryForObject(sql, mapper, email);
		}catch(EmptyResultDataAccessException e) {
			log.debug("Fullname: "+ email +" Not Fonud!");
			}
		return user;
	}

	public String registerUser(User user,String authorities) {
	
		final String sql = "INSERT INTO USERS (id,username,password,activationCode,active,authorities) VALUES (?,?,?,?,?,?)";
		jdbc.update(sql,user.getId(),user.getUsername(),user.getPassword(),user.getActivationCode(),user.isActive(),authorities);
		log.info(user.toString());
		return "User Registered";
	}
}
