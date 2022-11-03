package com.github.mnemalotebya.spingbootsimplechat.dto;

import com.github.mnemalotebya.spingbootsimplechat.model.entity.Message;
import org.springframework.stereotype.Component;


public class MessageMapper {
    public static DtoMessage map(Message message) {
        DtoMessage dtoMessage = new DtoMessage();
        dtoMessage.setDatetime(message.getDateTime().toString());
        dtoMessage.setUsername(message.getUser().getName());
        dtoMessage.setText(message.getMessage());
        return dtoMessage;
    }
}
