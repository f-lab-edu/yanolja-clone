package com.moondysmell.yanoljaclone.exception;

public class NotEnoughRoomException extends RuntimeException {

    public NotEnoughRoomException(){
        super(ErrorCode.NOT_ENOUGH_ROOM.getMessage());
    }

    private NotEnoughRoomException(String msg){
        super(msg);
    }
}
