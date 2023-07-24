package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.awitez.course.entity.Course;
import com.awitez.course.payload.Branch;
import com.awitez.course.payload.CourseAvailedBranch;
import com.awitez.course.repository.CourseAvailedBranchRepository;
import com.awitez.course.repository.CourseRepository;
import com.awitez.course.serviceimpl.CourseServiceImpl;




@SpringBootTest(classes= {CourseServiceTest.class})
class CourseServiceTest {

	@Mock
	private CourseRepository courseRepository;
	@Mock
	private CourseAvailedBranchRepository courseAvailedBranchRepository;
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private CourseServiceImpl courseService;
	@BeforeEach
	void Listup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("list of courses")
	void getAllCourses() {
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());
		when(courseRepository.findAll()).thenReturn(List.of(new Course(),new Course()));
		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(courseAvailedBranchRepository.findAllByCourseId(anyLong())).thenReturn(Optional.of(List.of()));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+1,Branch.class)).thenReturn(new Branch());
		List<Course> courses = courseService.getAllCourses();
		assertThat(courses).isNotNull();
		assertThat(2).isEqualTo(courses.size());
		
	}
	@Test
	@DisplayName("get course by id")
	void getCourse() {
//		CourseAvailedBranch courseAvailedBranch=new CourseAvailedBranch();
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());

		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(courseAvailedBranchRepository.findAllByCourseId(anyLong())).thenReturn(Optional.of(List.of()));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+1,Branch.class)).thenReturn(new Branch());
		assertThat(course).isEqualTo(courseService.getCourse(0l));
	}
	@Test
	@DisplayName("add course")
	void createCourse() {
		Course course=new Course();
		when(courseRepository.save(course)).thenReturn(course);
		assertThat(course).isEqualTo(courseRepository.save(course));
	}
	@Test
	@DisplayName("update course")
	void updateCourse() {
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());

		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(courseRepository.save(course)).thenReturn(course);
		assertThat(course).isEqualTo(courseService.updateCourse(course,1l));

	}
	@Test
	@DisplayName("delete course")
	void deleteCourse() {
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());

		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
//		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+1l,Boolean.class)).thenReturn(true);
		
		when(courseAvailedBranchRepository.findByCourseId(anyLong())).thenReturn(Optional.of(new CourseAvailedBranch()));
		when(courseAvailedBranchRepository.findAllByCourseId(anyLong())).thenReturn(Optional.of(List.of()));
		
		courseService.deleteCourse(0l);
		verify(courseRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("check course by id")
	void isPresent() {
		when( courseRepository.findById(any())).thenReturn(Optional.of(new Course()));
		assertThat(true).isEqualTo(courseService.isPresent(0l));
	
	}
	@Test
	@DisplayName("add course to branch")
	void addCourListoBranch() {
		CourseAvailedBranch courseAvailedBranch=new CourseAvailedBranch();
		when(courseAvailedBranchRepository.isAlreadyExists(anyLong(), anyLong())).thenReturn(false);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+1l,Boolean.class)).thenReturn(true);
//		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+1l,Boolean.class)).thenReturn(true);
		when(courseAvailedBranchRepository.save(courseAvailedBranch)).thenReturn(courseAvailedBranch);
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());

		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(courseAvailedBranchRepository.findAllByCourseId(anyLong())).thenReturn(Optional.of(List.of()));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+1l,Branch.class)).thenReturn(new Branch());
		assertThat(course).isEqualTo(courseService.addCourseToBranch(1l,1l));
	}
	@Test
	@DisplayName("delete course - branch")
	void deleteCourseBranch() {
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());
		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
//		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+1l,Boolean.class)).thenReturn(true);
		when(courseAvailedBranchRepository.isAlreadyExists(anyLong(),anyLong())).thenReturn(true);
		CourseAvailedBranch courseAvailedBranch = new CourseAvailedBranch();
		when(courseAvailedBranchRepository.findByCourseIdAndBranchId(anyLong(),anyLong())).thenReturn(courseAvailedBranch);
		courseService.deleteCourseBranch(0l, 0l);
		verify(courseAvailedBranchRepository,times(1)).delete(courseAvailedBranch);
		
	}
//	
	@Test
	@DisplayName("delete branches of course")
	void deleteCourseBranches() {
		Course course=new Course(1, "asdfas", "asdfa", 6f, 999f,List.of());
		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
//		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+any(),Boolean.class)).thenReturn(true);
		when(courseAvailedBranchRepository.findByCourseId(anyLong())).thenReturn(Optional.of(new CourseAvailedBranch()));
		CourseAvailedBranch courseAvailedBranch = new CourseAvailedBranch();
		when(courseAvailedBranchRepository.findAllByCourseId(anyLong())).thenReturn(Optional.of(List.of(courseAvailedBranch)));
		courseService.deleteCourseBranches(0l);
		verify(courseAvailedBranchRepository,times(1)).deleteAll(List.of(courseAvailedBranch));
	}
	@Test
	@DisplayName("delete courses of branch")
	void deleteBranchCourses() {
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(courseAvailedBranchRepository.findByBranchId(anyLong())).thenReturn(Optional.of(new CourseAvailedBranch()));
		when(courseAvailedBranchRepository.findAllByBranchId(anyLong())).thenReturn(Optional.of(List.of()));
		courseService.deleteBranchCourses(0l);
		verify(courseAvailedBranchRepository,times(1)).deleteAll(List.of());
		}
}
