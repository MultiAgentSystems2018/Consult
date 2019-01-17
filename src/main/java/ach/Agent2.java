package ach;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class Agent2 extends Agent{
	@Override
	protected void setup() {
		MessageTemplate mt = MessageTemplate.MatchProtocol("MyProtocol");
		final AchieveREResponder resp = new AchieveREResponder(this, mt);
		Behaviour prep = new OneShotBehaviour() {
			
			@Override
			public void action() {
				ACLMessage req = (ACLMessage)getDataStore().get(resp.REQUEST_KEY);
				ACLMessage response = req.createReply();
				response.setPerformative(ACLMessage.INFORM);
				response.setContent("UIIIIIII");
				getDataStore().put(resp.RESULT_NOTIFICATION_KEY, response);
			}
		};
		
		resp.registerPrepareResultNotification(prep);
		addBehaviour(resp);
	}
}
