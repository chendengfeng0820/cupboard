package com.info.service;

public interface SendService {

    public void sendMessage(String message);

    public void sendFanoutMessage(String message);

    public void sendtopicMessage(String message);
}
