package com.websocket.demo.api;

import com.websocket.demo.request.CreateRoomRequest;
import com.websocket.demo.request.FindChatListRequest;
import com.websocket.demo.request.LoginRequest;
import com.websocket.demo.request.RoomOutRequest;
import com.websocket.demo.response.ApiResponse;
import com.websocket.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.websocket.demo.response.ApiResponse.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChatApi {

    private final ChatService chatService;

    @GetMapping("/chat")
    public ApiResponse getChattingList(@ModelAttribute FindChatListRequest request) {
        return success(chatService.findChatList(request));
    }

    @GetMapping("/room")
    public ApiResponse getRoomList(@SessionAttribute("user") LoginRequest userInfo ) {
        return success(chatService.findRoomList(userInfo.getNickname()));
    }
    @PostMapping("/room")
    public ApiResponse createRoomList(@RequestBody CreateRoomRequest request){
        return success(chatService.createRoom(request));
    }
    @DeleteMapping("/room")
    public ApiResponse getOutRoom(@RequestBody RoomOutRequest request, @SessionAttribute("user") LoginRequest userInfo){
        chatService.getOutRoom(request, userInfo.getNickname());
        return success(null);
    }
}
