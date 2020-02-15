package io.java.springdata.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepo;

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
		topicRepo.save(topic);
	}
	
	public List<Topic> getTopics(){
		//return topics;
		List<Topic> topics = new ArrayList<Topic>();
	    topicRepo.findAll().forEach(topics :: add);
	    return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
}
