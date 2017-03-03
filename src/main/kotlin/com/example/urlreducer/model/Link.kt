package com.example.urlreducer.model

import javax.persistence.*

@Entity
@Table(name = "Links")
data class Link(
    var text: String = "",
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
    var id: Long = 0
)
