package server_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import server_socket.entity.Room;


public class SimpleGUIserver {
	
	// 연결된 소켓의 리스트를 저장하는 공간
	public static List<ConnectedSocket> connectedSocketList = new ArrayList<>();
	// 방 생성시 방의 리스트를 저장하는 공간
	public static List<Room> roomList = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			// 8000 포트에서 서버 생성
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("[ 서버실행 ]");
			
			while(true) {
				// 클라이언트 연결을 기다리는 중
				Socket socket = serverSocket.accept();
				System.out.println("접속");
				// 소켓에 객체 생성 (쓰레드)
				ConnectedSocket connectedSocket = new ConnectedSocket(socket);
				// 쓰레드 실행
				connectedSocket.start();
				// 소켓리스트에 추가 
				connectedSocketList.add(connectedSocket);
			}
		}catch (IOException e) {
			e.printStackTrace(); 
		}
	}
}