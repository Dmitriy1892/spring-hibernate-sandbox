package com.github.dmitriy1892.hibernate.app2.model

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "person_table")
data class PersonEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault(value = "id")
    var id: Int = 0,

    @Column(name = "name", nullable = false, length = 100)
    var name: String = "",

    @Column(name = "age", nullable = false, columnDefinition = "int not null check(age<100)")
    var age: Int = 0,

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.PERSIST])
    var items: List<ItemEntity> = emptyList()
) {

    override fun toString(): String {
        return "Person(id=$id, name=$name, age=$age, itemsCount=${items.size})"
    }
}
