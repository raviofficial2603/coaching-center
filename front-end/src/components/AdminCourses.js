import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Spinner } from "react-bootstrap";
import LoginModal from "./LoginModal";
import UpdateCourseModal from "./UpdateCourseModal";
import CourseForm from "./CourseForm";
import notfound from "../images/notfound.jfif";

function AdminCourses() {
  const [courses, setCourses] = useState([]);
  const [addCourseModalShow, setAddCourseModalShow] = React.useState(false);
  const [selectedCourse, setSelectedCourse] = useState({});
  const [updateCourseModalShow, setUpdateCourseModalShow] =
    React.useState(false);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    loadCourses();
    window.scrollTo(0, 0);
  }, []);
  function loadCourses() {
    axios
      .get("http://localhost:8091/admin/course/courses")
      .then((response) => {
        return response.data;
      })
      .then((response) => {
        setCourses(response);
        setIsLoading(false);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  function addCourse(course) {
    axios
      .post(
        "http://localhost:8091/admin/course/create",
        JSON.stringify({
          ...course,
        }),
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      )
      .then((response) => {
        return response.data;
      })
      .then(() => {
        loadCourses();
      })
      .catch((error) => {
        console.log(error);
      });
  }
  function updateCourse(course) {
    axios
      .put(
        "http://localhost:8091/admin/course/update/" + course.courseId,
        JSON.stringify({
          ...course,
        }),
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      )
      .then((response) => {
        return response.data;
      })
      .then(() => {
        loadCourses();
      })
      .catch((error) => {
        console.log(error);
      });
  }
  return (
    <Container className="mt-5">
      <Button
        className="btn-success btn-lg"
        onClick={() => setAddCourseModalShow(true)}
      >
        Add Course
      </Button>

      {addCourseModalShow && (
        <CourseForm
          show={addCourseModalShow}
          onSubmitHandler={addCourse}
          course={{}}
          buttonText={"Add"}
          btnColor={" btn-success "}
          onHide={() => setAddCourseModalShow(false)}
        />
      )}
      {courses.length != 0 ? (
        <table className="table table-hover mt-3">
          <thead>
            <tr>
              <th scope="col">Sl.No</th>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">Description</th>
              <th scope="col">Duration</th>
              <th scope="col">Fee</th>
              <th scope="col">Update</th>
            </tr>
          </thead>
          <tbody>
            {courses.map((course, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{course.courseId}</td>
                <td>{course.courseName}</td>
                <td>{course.courseDescription}</td>
                <td>{course.courseDuration}</td>
                <td>{course.courseFee}</td>
                <td>
                  <Button
                    onClick={() => {
                      setUpdateCourseModalShow(true);
                      setSelectedCourse(course);
                    }}
                  >
                    Update
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <Container style={{ height: "70vh", marginTop: "50px" }}>
          {isLoading ? (
            <Spinner animation="border" variant="dark" />
          ) : (
            <>
              <h1>No courses found</h1>
              <img src={notfound} />
            </>
          )}
        </Container>
      )}
      {updateCourseModalShow && (
        <CourseForm
          buttonText={"Update"}
          onSubmitHandler={updateCourse}
          btnColor={" btn-primary "}
          course={selectedCourse}
          onHide={() => setUpdateCourseModalShow(false)}
        />
      )}
    </Container>
  );
}

export default AdminCourses;
