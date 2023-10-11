package com.ficrew.yourbutler.chat.application;

import com.ficrew.yourbutler.chat.application.result.MessageResult;
import com.ficrew.yourbutler.chat.application.result.MessageResultList;
import com.ficrew.yourbutler.chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.chat.domain.entity.Message;
import com.ficrew.yourbutler.chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GetMessageListProcessor {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public MessageResultList execute(Long aptId, AuthenticatedMember member) {
        Member memberEntity = memberRepository.findById(member.getId());
        Optional<ChatRoom> chatRoom = chatRepository.findByMemberAndAptId(memberEntity, aptId);
        Long chatRoomNumber;
        List<Message> messageList;
        List<MessageResult> messageResultList = new ArrayList<>();

        if (chatRoom.isPresent()) {
            chatRoomNumber = chatRoom.get().getId();
            messageList = messageRepository.findAllByChatRoom(chatRoom.get());

            for (Message message : messageList) {
                messageResultList.add(new MessageResult(message));
            }
        } else {
            chatRoomNumber = -1L;
        }

        return new MessageResultList(chatRoomNumber, messageResultList);
    }

}
