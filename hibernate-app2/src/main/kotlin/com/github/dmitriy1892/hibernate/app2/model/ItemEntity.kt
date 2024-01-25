package com.github.dmitriy1892.hibernate.app2.model

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "item_table")
data class ItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault(value = "id")
    var id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    var owner: PersonEntity? = PersonEntity(),

    @Column(name = "item_name", nullable = false, length = 100)
    var itemName: String = ""
) {
    override fun toString(): String {
        return "Item(id=$id, owner=${owner?.name}, itemName=${itemName}"
    }
}
