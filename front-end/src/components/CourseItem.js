import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import "./CourseItem.css";
import { image1, image2, image3 } from "../images";
import CourseModal from "./CourseModal";
import { useContext, useState } from "react";
import EnrollmentModal from "./EnrollmentModal";
import UserContext from "./UserContext";
import { useNavigate } from "react-router-dom";
import cimage1 from "../images/cimage1.jpg";
import cimage2 from "../images/cimage2.jpg";
import cimage3 from "../images/cimage3.jpg";
import cimage4 from "../images/cimage4.jpg";
import cimage5 from "../images/cimage5.jpg";
import cimage6 from "../images/cimage6.jpg";
import cimage7 from "../images/cimage7.jpg";
import cimage8 from "../images/cimage8.jpg";
import cimage9 from "../images/cimage9.jpg";
import cimage10 from "../images/cimage10.jpg";
// import {cimage1,cimage2,cimage3,cimage4,cimage5,cimage6,cimage7,cimage8,cimage9,cimage10} from '../images'
const images = [
  cimage1,
  cimage2,
  cimage3,
  cimage4,
  cimage5,
  cimage6,
  cimage7,
  cimage8,
  cimage9,
  cimage10,
];

// const images=[cimage1,cimage2,cimage3,cimage4,cimage5,cimage6,cimage7,cimage8,cimage9,cimage10]

function CourseItem(props) {
  const context = useContext(UserContext);
  const navigate = useNavigate();
  const [courseModalShow, setCourseModalShow] = useState(false);
  const [enrollmentModalShow, setEnrollmentModalShow] = useState(false);
  console.log(props);
  return (
    <>
      <div className="card course-card mb-3 overflow">
        <div className="overflow">
          <img
            src={images[props.index % 10]}
            className="card-img-top "
            alt="..."
          />
        </div>
        <div className="card-body">
          <h5 className="card-title">{props.course.courseName}</h5>
          <h6 className="card-title">
            Duration: {props.course.courseDuration} Months
          </h6>
          <p className="card-text">
            {props.course.courseDescription} lorem Ipsum is simply dummy text of
            the printing and typesetting industry. Lorem Ipsum has been the
            industry's standard dummy text ever since the 1500s, when an unknown
            printer took a galley of type and scrambled it to make a type
            specimen book. It has survived not only five centuries, but also the
            leap into electronic typesetting, remaining essentially unchanged.
          </p>
          <Button variant="primary" onClick={() => setCourseModalShow(true)}>
            Know More
          </Button>

          <CourseModal
            course={props.course}
            show={courseModalShow}
            onHide={() => setCourseModalShow(false)}
          />
          <Button
            variant="success ms-2"
            onClick={() => {
              context.userId == null || context.userId === "null"
                ? navigate("/login")
                : setEnrollmentModalShow(true);
            }}
          >
            Enroll Now
          </Button>
          <EnrollmentModal
            course={props.course}
            show={enrollmentModalShow}
            onHide={() => setEnrollmentModalShow(false)}
          />
        </div>
      </div>
    </>
  );
}

export default CourseItem;
