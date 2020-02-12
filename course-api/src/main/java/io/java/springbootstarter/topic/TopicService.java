package io.java.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {

	List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("spring","sp","spr"),	
			new Topic("hibernate","hi","hiber"),	
			new Topic("java","ja","jav"),
			new Topic("aaa","aa","aaa")
				));
	
	public void deleteTopic(String id){
		for(int i=0; i < topics.size(); i++) {
			Topic t = topics.get(i);
			if(t.getId().equals(id)) {
			 topics.remove(i);
			 return;
			}
		}
	}
	
	public void updateTopic(Topic topic, String id) {
		for(int i=0; i < topics.size(); i++) {
			Topic t = topics.get(i);
			if(t.getId().equals(id)) {
			 topics.set(i, topic);
			 return;
			}
		}
	}
	
	public void addTopic(Topic topic) {
		System.out.println("adding topic------ > ");
		topics.add(topic);
	}
	
	public List<Topic> getTopics(){
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
}
