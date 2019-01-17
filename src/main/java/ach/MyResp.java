package ach;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class MyResp extends AchieveREResponder {

	public MyResp(Agent a, MessageTemplate mt) {
		super(a, mt);
	}

	@Override
	protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
		ACLMessage msg = request.createReply();
		msg.setPerformative(ACLMessage.AGREE);
		msg.setContent("OK");
		return msg;
	}
	
	@Override
	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		ACLMessage inform = request.createReply();
		inform.setPerformative(ACLMessage.INFORM);
		inform.setContent("INFO from " + myAgent.getLocalName());
		return inform;
	}
}
