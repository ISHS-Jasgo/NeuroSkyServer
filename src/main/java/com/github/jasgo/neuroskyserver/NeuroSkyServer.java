package com.github.jasgo.neuroskyserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NeuroSkyServer extends Thread{
    public static Socket socket;

    public NeuroSkyServer(Socket socket) {
        NeuroSkyServer.socket = socket;
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);
            while (true) {
                Socket socket = server.accept();
                Thread thread = new NeuroSkyServer(socket);
                thread.start();
                new ListeningThread().start();
                System.out.println("사용자가 접속했습니다.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class ListeningThread extends Thread {
        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String readValue;
                while ((readValue = reader.readLine()) != null) {
                    System.out.println(readValue);

                }
            } catch (IOException e) {
                System.out.println("사용자와 연결이 종료되었습니다.");
            }
        }
    }
    public static JSONObject parseToJSON(String s) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(s);
    }
}
