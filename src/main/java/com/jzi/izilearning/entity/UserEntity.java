package com.jzi.izilearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter @NoArgsConstructor
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String firstname;
  private String lastname;
  private String username;

  @Column(unique = true)
  private String email;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(name = "user_entity_id"),
          inverseJoinColumns = @JoinColumn(name = "roles_id")
  )
  private Set<RoleEntity> roles;
  @Column(name = "avatar_url")
  private String avatarUrl;
}
