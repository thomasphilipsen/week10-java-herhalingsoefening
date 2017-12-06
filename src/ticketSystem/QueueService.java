package ticketSystem;

import java.util.HashMap;
import java.util.LinkedList;

public class QueueService {
	private HashMap<Event, LinkedList<User>> queueCollection;
	//private HashMap<String, LinkedList<String>> queueCollection;
	
	public QueueService() {
		queueCollection = new HashMap<Event, LinkedList<User>>();
		//queueCollection = new HashMap<String, LinkedList<String>>();
	}
	
	public void addToQueue(String eventId, String userId){
		Event event = TicketSystem.getEventById(eventId);
		if(event != null && !queueCollection.containsKey(event)){
			queueCollection.put(event, new LinkedList<User>());
		}
		queueCollection.get(eventId).add(TicketSystem.getUserById(userId));
	}
	
	public User getNextInLine(String eventId){
		Event event = TicketSystem.getEventById(eventId);
		User user = null;
		if(event != null){
			user = queueCollection.get(event).removeFirst();
		}
		return user;
	}
	
	public void printCurrentQueue(String eventId){
		Event event = TicketSystem.getEventById(eventId);
		if(event != null){
			System.out.printf("%s - %s%n",event.getId(), event.getName());
			queueCollection.get(event).stream().forEach(System.out::println);
		} else {
			System.out.println("No such event!");
		}
	}
	
	public void printCurrentQueueLength(String eventId){
		Event event = TicketSystem.getEventById(eventId);
		if(event != null){
			System.out.printf("%s - %d%n",event.getId(), queueCollection.get(event).size());
		}
	}

}
