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
	
	public void deleteTopic(String id){
		topicRepo.deleteById(id);
	}
	
	public void updateTopic(Topic topic, String id) {
		topicRepo.save(topic);
	}
	
	public void addTopic(Topic topic) {
		topicRepo.save(topic);
	}
	
	public List<Topic> getTopics(){
		List<Topic> topics = new ArrayList<Topic>();
	    topicRepo.findAll().forEach(topics :: add);
	    return topics;
	}
	
	public Topic getTopic(String id) {
		return topicRepo.findById(id).get();
	}
}
