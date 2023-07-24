import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, InputGroup, Modal, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function MentorForm(props) {
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);

  const [mentor, setMentor] = useState(props.mentor);

  useEffect(() => {
    setMentor(props.mentor);
    setValidated(false);
  }, [props.mentor]);

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
      // navigate('/admin/mentors')
      props.onSubmitHandler(mentor);
      props.onHide();
    }
  };
  function changeHandler(e) {
    setMentor((prevState) => ({
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
              <Form.Label>First Name</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="First Name"
                minLength={3}
                onChange={changeHandler}
                name="firstName"
                value={mentor.firstName}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check First Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom02"
            >
              <Form.Label>Last Name</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="Last Name"
                minLength={3}
                onChange={changeHandler}
                name="lastName"
                value={mentor.lastName}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Last Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom03"
            >
              <Form.Label>Full Name</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="Full Name"
                minLength={3}
                onChange={changeHandler}
                name="fullName"
                value={mentor.fullName}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Full Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-4 fw-bold" controlId="validationCustom04">
              <Form.Label className="text-dark fw-bold">Gender</Form.Label>
              <Form.Select
                required
                value={mentor.gender}
                onChange={changeHandler}
                name="gender"
                aria-label="Default select example"
              >
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </Form.Select>
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Gender.
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group lassName="mb-4 fw-bold" controlId="validationCustom05">
              <Form.Label className="text-dark fw-bold">Email</Form.Label>
              <Form.Control
                required
                type="email"
                value={mentor.email}
                name="email"
                placeholder="Email or Username"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Email.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group lassName="mb-4 fw-bold" controlId="validationCustom06">
              <Form.Label className="text-dark fw-bold">Mobile</Form.Label>
              <Form.Control
                required
                type="number"
                value={mentor.mobileNumber}
                name="mobileNumber"
                placeholder="Mobile"
                onChange={changeHandler}
              />
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom07"
            >
              <Form.Label>Qualification</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="Qualification"
                minLength={3}
                onChange={changeHandler}
                name="qualification"
                value={mentor.qualification}
                onkeydown="return /[a-z]/i.test(event.key)"
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Qualification.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom08"
            >
              <Form.Label>Skills</Form.Label>
              <Form.Control
                required
                type="text"
                placeholder="Skills"
                minLength={3}
                onChange={changeHandler}
                name="skills"
                value={mentor.skills}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check skills.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group
              md="4"
              className="mb-4 fw-bold"
              controlId="validationCustom09"
            >
              <Form.Label>Experience</Form.Label>
              <Form.Control
                required
                type="number"
                placeholder="Experience"
                min={0}
                max={90}
                onChange={changeHandler}
                name="experience"
                value={mentor.experience}
              />

              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Experience.
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-4 fw-bold" controlId="validationCustom10">
              <Form.Label className="text-dark fw-bold">Branch</Form.Label>
              <Form.Select
                required
                value={mentor.branchId}
                onChange={changeHandler}
                name="branchId"
                aria-label="Default select example"
              >
                <option value="">Select Branch</option>
                {props.branches.map((branch) => (
                  <option value={branch.branchId}>{branch.location}</option>
                ))}
              </Form.Select>
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please select Branch.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-4 fw-bold" controlId="validationCustom11">
              <Form.Label className="text-dark fw-bold">Address</Form.Label>
              <Form.Control
                required
                as="textarea"
                value={mentor.address}
                name="address"
                rows={3}
                placeholder="Address"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Address.
              </Form.Control.Feedback>
            </Form.Group>
          </div>

          <p className="text-danger mx-auto text-center fw-bold">{message}</p>
          <Button
            className={props.btnColor + " btn-lg d-flex mx-auto mb-0"}
            type="submit"
          >
            {props.buttonText} Mentor
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

export default MentorForm;
