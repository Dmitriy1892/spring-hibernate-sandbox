package com.github.dmitriy1892.hibernate.app2.model

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "passport_table")
data class PassportEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault(value = "id")
    var id: Int = 0,

    @OneToOne
    @JoinColumn(
        name = "person_id",
        referencedColumnName = "id",
        unique = true
    )
    var person: PersonEntity? = null,

    @Column(name = "passport_number", nullable = false)
    var passportNumber: Int = 0

) {

    override fun toString(): String {
        return "Passport number = $passportNumber, person name = ${person?.name}"
    }
}
