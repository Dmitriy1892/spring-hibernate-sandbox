package com.github.dmitriy1892.spring.crud.app1.dao

import com.github.dmitriy1892.spring.crud.app1.models.Person
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class PersonMapper : RowMapper<Person> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Person? {
        return Person(
            id = rs.getInt("id"),
            name = rs.getString("name"),
            age = rs.getInt("age"),
            email = rs.getString("email"),
            address = rs.getString("address")
        )
    }
}