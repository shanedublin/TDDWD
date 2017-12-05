package com.rusd.tddwd.events;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

	
	static List<Subscriber> list = new ArrayList<>(); 
	
	public static void publish(GameEvent e) {
		
		list.stream().forEach(s -> s.handle(e));
		
	}
	public static void subscribe(Subscriber e) {
		list.add(e);		
	}	
	public static void init() {
		list = new ArrayList<>();
	}
	
}
