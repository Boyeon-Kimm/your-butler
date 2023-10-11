package com.ficrew.yourbutler.chat.application;

import com.ficrew.yourbutler.chat.application.result.ChatResult;
import com.ficrew.yourbutler.chat.application.result.MessageResult;
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

@RequiredArgsConstructor
public class GetAllChatListProcessor {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public List<ChatResult> execute(AuthenticatedMember member) {
        Member memberEntity = memberRepository.findById(member.getId());
        List<ChatResult> chatResultList = new ArrayList<>();
        // 현재 유저가 속한 모든 채팅방 받아오기
        List<ChatRoom> chatRoomList = chatRepository.findAllByMember(memberEntity);

        for (ChatRoom chatRoom : chatRoomList) {
            List<Message> messageList = messageRepository.findAllByChatRoom(chatRoom);
            List<MessageResult> messageResultList = new ArrayList<>();

            for (Message message : messageList) {
                messageResultList.add(new MessageResult(message));
            }

            chatResultList.add(new ChatResult(chatRoom.getBuildingName(), messageResultList));
        }

        return chatResultList;
    }

}
