package com.github.dmitriy1892.spring.crud.app1.dao

import com.github.dmitriy1892.spring.crud.app1.models.Person
import com.github.dmitriy1892.spring.crud.app1.models.PersonMutable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.PreparedStatement

@Component
class PersonDao @Autowired constructor(
    private val jdbcTemplate: JdbcTemplate
) {

    fun index(): List<Person> = jdbcTemplate.query("SELECT * FROM Person", PersonMapper())

    fun show(id: Int): Person? = jdbcTemplate
        .query("SELECT * FROM Person WHERE id=?", PersonMapper(), id)
        .firstOrNull()

    fun show(email: String): Result<Person> = runCatching {
        jdbcTemplate
            .query("SELECT * FROM Person WHERE email=?", PersonMapper(), email)
            .first()
    }

    fun save(personMutable: PersonMutable) {
        jdbcTemplate.update(
            "INSERT INTO person(name, age, email, address) VALUES(?, ?, ?, ?)",
            personMutable.name,
            personMutable.age,
            personMutable.email,
            personMutable.address
        )
    }

    fun update(id: Int, personMutable: PersonMutable) {
        jdbcTemplate.update(
            "UPDATE person SET name=?, age=?, email=?, address=? WHERE id=?",
            personMutable.name,
            personMutable.age,
            personMutable.email,
            personMutable.address,
            id
        )
    }

    fun delete(id: Int) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id)
    }

    // Test batch insert performance
    fun testMultipleUpdate() {
        val people = createThousandPeople()

        val measuredTime = measureTime {
            people.forEach { person ->
                jdbcTemplate.update(
                    "INSERT INTO person(name, age, email, address) VALUES (?, ?, ?, ?)",
                    person.name,
                    person.age,
                    person.email,
                    person.address
                )
            }
        }

        println("Time for multiple update: $measuredTime")
    }

    fun testBatchUpdate() {
        val people = createThousandPeople()

        val measuredTime = measureTime {
            jdbcTemplate.batchUpdate(
                "INSERT INTO person(name, age, email, address) VALUES (?, ?, ?)",
                object : BatchPreparedStatementSetter {
                    override fun setValues(ps: PreparedStatement, i: Int) {
                        val person = people[i]
                        ps.apply {
                            setString(1, person.name)
                            setInt(2, person.age)
                            setString(3, person.email)
                            setString(4, person.address)
                        }
                    }

                    override fun getBatchSize(): Int = people.size
                }
            )
        }

        println("Time for batch update: $measuredTime")
    }

    private fun createThousandPeople(): List<Person> = run {
        val people = mutableListOf<Person>()
        repeat(1000) { index ->
            val person = Person(
                id = index,
                name = "Name $index",
                age = 30,
                email = "test$index@mail.ru",
                address = "City$index, Street$index, 000000"
            )
            people.add(person)
        }

        return@run people
    }

    private fun measureTime(block: () -> Unit): Long {
        val beforeTime = System.currentTimeMillis()

        block()

        val afterTime = System.currentTimeMillis()

        return afterTime - beforeTime
    }
}