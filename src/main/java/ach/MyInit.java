package ach;

import java.util.Vector;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class MyInit extends AchieveREInitiator {

	public MyInit(Agent a, ACLMessage msg) {
		super(a, msg);
	}

	@Override
	protected Vector prepareRequests(ACLMessage request) {
		
		Vector<ACLMessage> allMessages = new Vector<ACLMessage>();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("MyServ");
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		DFAgentDescription[] result = null;
		try {
			result = DFService.search(myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		for (DFAgentDescription a : result) {
			ACLMessage newRq = request.shallowClone();
			newRq.addReceiver(a.getName());
			allMessages.add(newRq);
		}
		
		return allMessages;
	}
	
	@Override
	protected void handleAllResponses(Vector responses) {
		for (ACLMessage m : (Vector<ACLMessage>)responses) {
			System.out.println("Response: "+ m.getContent());
		}
	}
}
