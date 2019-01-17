package ach;

import java.io.IOException;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class Agent1 extends Agent {

	@Override
	protected void setup() {
		

		
		ACLMessage initMsg = new ACLMessage(ACLMessage.REQUEST);
		initMsg.setProtocol("MyProtocol");
		initMsg.addReceiver(new AID("Resp", false));
		
		final AchieveREInitiator init = new AchieveREInitiator(this, initMsg);
		
		Behaviour onAllMsg = new OneShotBehaviour() {
			
			@Override
			public void action() {
				Vector<ACLMessage> msg = (Vector<ACLMessage>)this.getDataStore().get(init.ALL_RESULT_NOTIFICATIONS_KEY);
				System.out.println("Result:" + msg.get(0).getContent());
			}
		};
		
		
		init.registerHandleAllResultNotifications(onAllMsg);
		addBehaviour(init);
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


