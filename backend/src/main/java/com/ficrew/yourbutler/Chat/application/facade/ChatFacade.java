package com.ficrew.yourbutler.chat.application.facade;

import com.ficrew.yourbutler.chat.application.CreateMessageProcessor;
import com.ficrew.yourbutler.chat.application.GetAllChatListProcessor;
import com.ficrew.yourbutler.chat.application.GetMessageListProcessor;
import com.ficrew.yourbutler.chat.application.command.CreateMessageCommand;
import com.ficrew.yourbutler.chat.application.result.ChatResult;
import com.ficrew.yourbutler.chat.application.result.MessageResult;
import com.ficrew.yourbutler.chat.application.result.MessageResultList;
import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatFacade {

    private final CreateMessageProcessor createMessageProcessor;
    private final GetMessageListProcessor getMessageListProcessor;
    private final GetAllChatListProcessor getAllChatListProcessor;

    @Transactional
    public MessageResult createMessage(CreateMessageCommand command, AuthenticatedMember member) {
        return createMessageProcessor.execute(command, member);
    }

    @Transactional
    public MessageResultList getMessageList(Long aptId, AuthenticatedMember member) {
        return getMessageListProcessor.execute(aptId, member);
    }

    @Transactional
    public List<ChatResult> getAllChatList(AuthenticatedMember member) {
        return getAllChatListProcessor.execute(member);
    }

}
