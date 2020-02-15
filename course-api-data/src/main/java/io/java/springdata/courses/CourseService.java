package io.java.springdata.courses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;
	
	public void deleteCourse(String id){
		courseRepo.deleteById(id);
	}
	
	public void updateCourse(Course course) {
		courseRepo.save(course);
	}
	
	public void addCourse(Course course) {
		courseRepo.save(course);
	}
	
	public List<Course> getAllCourses(String topicId){
		List<Course> courses = new ArrayList<Course>();
	    courseRepo.findByTopicId(topicId).forEach(courses :: add);
	    return courses;
	}
	
	public Course getCourse(String id) {
		return courseRepo.findById(id).get();
	}
}
