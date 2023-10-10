package com.ficrew.yourbutler.Chat.domain.entity;

import com.ficrew.yourbutler.member.domain.entity.Member;
import lombok.Getter;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long aptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String buildingName;

    public ChatRoom(Long aptId, Member member, String buildingName) {
        this.aptId = aptId;
        this.member = member;
        this.buildingName = buildingName;
    }

    protected ChatRoom() {

    }

}
