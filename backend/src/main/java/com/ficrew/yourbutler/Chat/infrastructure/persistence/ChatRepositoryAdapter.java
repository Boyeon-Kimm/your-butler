package com.ficrew.yourbutler.chat.infrastructure.persistence;

import com.ficrew.yourbutler.chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.chat.infrastructure.persistence.jpa.JpaChatRoomRepository;
import com.ficrew.yourbutler.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements ChatRepository {

    private final JpaChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public Optional<ChatRoom> findByMemberAndAptId(Member member, Long aptId) {
        return chatRoomRepository.findByMemberAndAptId(member, aptId);
    }

    @Override
    public Optional<ChatRoom> findById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }

    @Override
    public List<ChatRoom> findAllByMember(Member member) {
        return chatRoomRepository.findAllByMember(member);
    }

}
