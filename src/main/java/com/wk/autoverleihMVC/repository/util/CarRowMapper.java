//package com.wk.autoverleihMVC.repository.util;
//
//import com.wk.autoverleihMVC.model.Car;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CarRowMapper implements RowMapper<Car> {
//
//	@Override
//	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
//		Car car = new Car();
//		car.setId(rs.getLong("id"));
//		car.setName(rs.getString("name"));
//		//car.setStartdate(rs.get("date"));
//		return car;
//	}
//}
