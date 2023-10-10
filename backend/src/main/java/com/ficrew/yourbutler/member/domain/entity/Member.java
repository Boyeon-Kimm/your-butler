package com.ficrew.yourbutler.member.domain.entity;

import com.ficrew.yourbutler.global.auth.Role;
import com.ficrew.yourbutler.member.application.command.CreateMemberCommand;
import com.ficrew.yourbutler.member.application.command.EditMemberCommand;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private String socialType;
    private String email;
    private String nickname;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer age;
    private Long holdingAsset;
    private Integer creditRating;
    private Long monthlyAvailableAsset;
    @Enumerated(EnumType.STRING)
    private NumberOfHouses numberOfHouses;

    public Member(String identifier, String socialType, String email, String nickname, String encryptedPassword) {
        this.identifier = identifier;
        this.socialType = socialType;
        this.email = email;
        this.nickname = nickname;
        this.password = encryptedPassword;
        this.role = Role.MEMBER;
        this.numberOfHouses = NumberOfHouses.NONE;
    }

    public void update(CreateMemberCommand command) {
        this.age = command.getAge();
        this.holdingAsset = command.getHoldingAsset();
        this.creditRating = command.getCreditRating();
        this.monthlyAvailableAsset = command.getMonthlyAvailableAsset();
        this.numberOfHouses = NumberOfHouses.valueOf(command.getNumberOfHouses());
    }

    public void edit(EditMemberCommand command) {
        this.age = command.getAge();
        this.holdingAsset = command.getHoldingAsset();
        this.creditRating = command.getCreditRating();
        this.monthlyAvailableAsset = command.getMonthlyAvailableAsset();
        this.numberOfHouses = NumberOfHouses.valueOf(command.getNumberOfHouses());
    }

    protected Member() {

    }

}
