package com.socketsample;


public interface ServerListener {
	
	public void clientConnected(Server server, Server.ConnectionToClient client);
	
	public void messageReceived(Server server, Server.ConnectionToClient client, Object msg);
	
	public void commandReceived(Server server, Server.ConnectionToClient client, Command cmd);
	
	public void clientDisconnected(Server server, Server.ConnectionToClient client);
	
	public void messageSent(Server server, Server.ConnectionToClient toClient, Object msg);
	
	public void commandSent(Server server, Server.ConnectionToClient toClient, Command cmd);

}
