package org.zerock.moviereview.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @ToString
@Table(name = "m_member")
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long mid;

    private String email;

    private String pw;

    private String nickname;
}
