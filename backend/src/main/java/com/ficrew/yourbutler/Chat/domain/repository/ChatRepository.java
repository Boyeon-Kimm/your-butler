package com.ficrew.yourbutler.chat.domain.repository;

import com.ficrew.yourbutler.chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.member.domain.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    ChatRoom save(ChatRoom chatRoom);

    Optional<ChatRoom> findByMemberAndAptId(Member member, Long aptId);

    Optional<ChatRoom> findById(Long chatRoomId);

    List<ChatRoom> findAllByMember(Member member);

}
