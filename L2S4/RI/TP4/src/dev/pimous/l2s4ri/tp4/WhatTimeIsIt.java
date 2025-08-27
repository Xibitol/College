package dev.pimous.l2s4ri.tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.time.Instant;

public class WhatTimeIsIt{

	private static final String SERVER_DN= "pool.ntp.org";
	private static final int SERVER_PORT = 123;

	// FUNCTIONS
	public void main(String[] args){
		InetAddress address;
		ByteBuffer bb = ByteBuffer.allocate(48);

		try{
			address = InetAddress.getByName(SERVER_DN);
		}catch (UnknownHostException e){
			System.err.println("Unable to retrieve server address;");
			return;
		}
		System.out.printf("Dialing with NTP server at %s.\n", address);

		DatagramPacket packet = new DatagramPacket(
			bb.array(), bb.capacity(), address, SERVER_PORT
		);
		try(DatagramSocket ds = new DatagramSocket()){
			try{
				bb.clear();
				bb.put((byte) 0b00100011);
				ds.send(packet);

				try{
					bb.clear();
					ds.receive(packet);

					System.out.println(
						Instant.ofEpochSecond(bb.getInt(40))
					);
				}catch(IOException e){
					System.err.println("Cannot receive the response;");
				}
			}catch(IOException e){
				System.err.println("Cannot send the packet;");
			}
		}catch(IOException e){
			System.err.println("Cannot open the datagram socket;");
		}
	}
}
