package com.hxgz.jpa.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="talent")
@Entity
@Data
public class Talent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String phone;
}
