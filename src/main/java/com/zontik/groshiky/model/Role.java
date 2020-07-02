package com.zontik.groshiky.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", unique = true)
    @Enumerated(value = EnumType.STRING)
    private Roles name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
