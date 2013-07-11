package org.haegerp.ws;

import javax.xml.ws.Endpoint;

import org.haegerp.ws.impl.HaegerpWSImpl;

/**
 * Das Web-Service wird publiziert
 * 
 * @author Wolf
 *
 */
public class HaegerpPublisher {
	
	public static void main(String[] args) throws Exception {
		 
		Endpoint.publish("http://localhost:9999/ws/haegerp", new HaegerpWSImpl());
    }
}
