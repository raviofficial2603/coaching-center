import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, InputGroup, Modal, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function CourseForm(props) {
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);

  const [course, setCourse] = useState(props.course);

  useEffect(() => {
    setCourse(props.course);
    setValidated(false);
  }, [props.course]);

  const handleSubmit = (event) => {
    setValidated(true);
    const form = event.currentTarget;
    event.preventDefault();
    if (form.checkValidity() === false) {
      event.stopPropagation();
      setMessage("Please Check All Fields");
    } else {
      setValidated(false);
      setMessage(" ");
      // navigate('/admin/courses')
      props.onSubmitHandler(course);
      props.onHide();
    }
  };
  function changeHandler(e) {
    setCourse((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  }
  const [message, setMessage] = useState("      ");
  return (
    <Modal
      {...props}
      show={true}
      size="md"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton className="m-0 border-0"></Modal.Header>
      <Modal.Title
        id="contained-modal-title-vcenter"
        className="d-block mx-auto"
      >
        <h1>Awitez</h1>
      </Modal.Title>
      <Modal.Body>
        <Form
          noValidate
          validated={validated}
          onSubmit={handleSubmit}
          className="mx-auto"
          method="post"
        >
          <div style={{ height: "53vh" }} className="overflow-auto mb-3">
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom01"
            >
              <Form.Label>Course Name</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="Course Name"
                minLength={3}
                onChange={changeHandler}
                name="courseName"
                value={course.courseName}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom02"
            >
              <Form.Label>Course Description</Form.Label>
              <Form.Control
                className="form-control"
                required
                type="text"
                placeholder="Description"
                minLength={5}
                onChange={changeHandler}
                name="courseDescription"
                value={course.courseDescription}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Description.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom03"
            >
              <Form.Label>Course Duration</Form.Label>
              <Form.Control
                required
                type="number"
                placeholder="Duration (in months)"
                min={1}
                max={200}
                onChange={changeHandler}
                name="courseDuration"
                value={course.courseDuration}
              />

              <Form.Control.Feedback type="invalid">
                Please enter valid duration.
              </Form.Control.Feedback>
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom04"
            >
              <Form.Label>Course Fee</Form.Label>
              <Form.Control
                required
                type="number"
                placeholder="Amount(in rupees)"
                min={0}
                max={2000000}
                onChange={changeHandler}
                name="courseFee"
                value={course.courseFee}
              />
              <Form.Control.Feedback type="invalid">
                Please enter valid amount.
              </Form.Control.Feedback>
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
            </Form.Group>
          </div>

          <p className="text-danger mx-auto text-center fw-bold">{message}</p>
          <Button
            className={props.btnColor + " btn-lg d-flex mx-auto mb-0"}
            type="submit"
          >
            {props.buttonText} Course
          </Button>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="dark" onClick={props.onHide}>
          Cancel
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default CourseForm;
