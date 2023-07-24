import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import React, { useEffect, useState } from "react";
import CourseItem from "./CourseItem";
import { image1, image2, image3 } from "../images";
import { Button, Spinner } from "react-bootstrap";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link } from "react-router-dom";
import axios from "axios";
import notfound from "../images/notfound.jfif";
const CoursesList = (props) => {
  const [courses, setCourses] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  // const [courses,setCourses] =useState( [
  //   {
  //     coursename: "C Programming",
  //     src: image1,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "C Programming",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image2,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "C++",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60",
  //   },
  //   {
  //     coursename: "C Programming",
  //     src: image3,
  //     text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
  //     label: "Java",
  //     path: "/courses",
  //     duration: "60"
  //   },
  // ]);

  useEffect(() => {
    axios
      .get("http://localhost:8085/course/courses")
      .then((response) => {
        setCourses(response.data);
        setIsLoading(false);
      })
      .catch((error) => console.error(error.response));
  }, []);
  if (props.count) {
    var courses1 = courses.slice(0, 3);
  } else {
    var courses1 = courses;
  }
  return (
    <>
      {courses1.length != 0 ? (
        <Container className="mt-3">
          <h1 className="mb-3">Start Your Journey</h1>
          <Row>
            {courses1.map((course, index) => (
              <Col key={index} md={4}>
                <CourseItem course={course} index={index} />
              </Col>
            ))}
          </Row>
        </Container>
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
    </>
  );
};

export default CoursesList;
