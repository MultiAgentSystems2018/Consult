package ach;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.MessageTemplate;

public class Agent3 extends Agent {
	
	@Override
	protected void setup() {
		ServiceDescription sd = new ServiceDescription();
		sd.setType("MyServ");
		sd.setName("NAME");
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MessageTemplate mt = MessageTemplate.MatchProtocol("MyProtocol");
		MyResp resp = new MyResp(this, mt);
		addBehaviour(resp);
	}
}
