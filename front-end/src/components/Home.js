import React, { useEffect } from "react";
import Slider from "./Slider";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import img5 from "../images/about.webp";
import Card from "react-bootstrap/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrophy } from "@fortawesome/free-solid-svg-icons";
import CoursesList from "./CoursesList";
import "./Home.css";
import { Link } from "react-router-dom";
import { Form } from "react-bootstrap";
function Home() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  console.log("In home js true");
  return (
    <div>
      <Slider></Slider>

      <Container className="mx-auto mt-5 pt-2 pb-2 mb-2">
        <Row>
          <Col xs={12} lg={6}>
            <img
              src={img5}
              className="w-100"
              style={{ height: "85vh", objectFit: "cover" }}
            />
          </Col>
          <Col xs={12} lg={6} className="mt-1">
            <p
              className="text-success fs-1"
              style={{
                fontFamily: "Signika Negative, sans-serif",
                fontWeight: "600",
              }}
            >
              WELCOME LEARNING
            </p>
            <p className="text-dark fs-2">Actually... Learning welcomes you</p>
            <Card className="mt-2 fs-4 d-flex action">
              <FontAwesomeIcon
                className="fs-4 text-start badge rounded-pill text-dark"
                icon={faTrophy}
              />
              <div>Meet Highly Experience Mentors</div>
            </Card>
            <Card className="mt-2 fs-4 d-flex action">
              <FontAwesomeIcon
                className="fs-4 text-start badge rounded-pill text-dark"
                icon={faTrophy}
              />
              Meet Highly Experience Mentors
            </Card>

            <Card className="mt-2 fs-4 d-flex action">
              <FontAwesomeIcon
                className="fs-4 text-start badge rounded-pill text-dark"
                icon={faTrophy}
              />
              One-stop-solution for Upskilling
            </Card>
            <Card className="mt-2 fs-4 d-flex action">
              <FontAwesomeIcon
                className="fs-4 text-start badge rounded-pill text-dark"
                icon={faTrophy}
              />
              One-stop-solution for Upskilling
            </Card>
            <Card className="mt-2 fs-4 d-flex action">
              <FontAwesomeIcon
                className="fs-4 text-start badge rounded-pill text-dark"
                icon={faTrophy}
              />
              One-stop-solution for Upskilling
            </Card>
          </Col>
        </Row>
      </Container>

      <CoursesList count={true} />
      <Link className="btn-success btn-lg btn" to={"/courses"}>
        More Courses...
      </Link>
    </div>
  );
}

export default Home;
