package com.project1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter

public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fullname")
    private String fullname;
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="status")
    private Integer status;

    @Column(name="classId")
    private String classId;

    @Column(name="phone_number")
    private String phone_number;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+ this.getRole()));
        return authorityList;
    }

    @ManyToMany(mappedBy="userEntities", fetch = FetchType.LAZY)
    private List<ContestEntity> contestEntities = new ArrayList<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SubmissionEntity> submissions = new ArrayList<>();
}
