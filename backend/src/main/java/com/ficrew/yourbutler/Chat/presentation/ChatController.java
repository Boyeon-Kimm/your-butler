package com.ficrew.yourbutler.chat.presentation;

import com.ficrew.yourbutler.chat.application.facade.ChatFacade;
import com.ficrew.yourbutler.chat.application.result.ChatResult;
import com.ficrew.yourbutler.chat.presentation.request.CreateMessageRequest;
import com.ficrew.yourbutler.chat.presentation.response.ChatResponse;
import com.ficrew.yourbutler.chat.presentation.response.MessageResponse;
import com.ficrew.yourbutler.chat.presentation.response.MessageResponseList;
import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatFacade chatFacade;

    // 내가 참가한 채팅방 번호 아니면 오류 띄우는 메세지도 만들어야 함
    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@RequestBody @Valid CreateMessageRequest request, @AuthenticationPrincipal AuthenticatedMember member) {
        MessageResponse messageResponse = chatFacade.createMessage(request.toCommand(), member).toResponse();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/{aptId}")
    public ResponseEntity<MessageResponseList> getMessages(@PathVariable Long aptId, @AuthenticationPrincipal AuthenticatedMember member) {
        // Entity -> Result -> Response 이거 너무 비효율적인데 이게 맞나
        MessageResponseList messageResponseList = chatFacade.getMessageList(aptId, member).toResponse();
        return new ResponseEntity<>(messageResponseList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getAllMessages(@AuthenticationPrincipal AuthenticatedMember member) {
        List<ChatResult> chatResultList = chatFacade.getAllChatList(member);
        List<ChatResponse> chatResponseList = chatResultList.stream()
                .map(ChatResult::toResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(chatResponseList, HttpStatus.OK);
    }

}
