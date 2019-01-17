package ach;

import java.io.IOException;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Agent4 extends Agent {
	@Override
	protected void setup() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setProtocol("MyProtocol");
		MyInit init = new MyInit(this, msg);
		addBehaviour(init);
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
