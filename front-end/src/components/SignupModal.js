import React, { useContext } from "react";
import { useState } from "react";
import {
  Button,
  Form,
  Modal,
  Row,
  Col,
  InputGroup,
  Spinner,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import UserContext from "./UserContext";

import { toast } from "react-toastify";
function SignupModal(props) {
  const navigate = useNavigate();
  const context = useContext(UserContext);
  const [message, setMessage] = useState("      ");
  const [validated, setValidated] = useState(false);
  const [candidate, setCandidate] = useState({});
  const [showRegister, setShowRegister] = useState(true);
  const handleSubmit = (event) => {
    const form = event.currentTarget;
    event.preventDefault();
    setShowRegister(false);
    if (form.checkValidity() === false) {
      setShowRegister(true);

      setMessage("Check All Fields");
      toast.error("Check All Fields");
      event.stopPropagation();
    } else {
      axios
        .get("http://localhost:8086/candidate/email-exist/" + candidate.email)
        .then((response) => {
          console.log(response.data);
          console.log("response.data");
          setShowRegister(true)
          if (response.data) {
            setMessage("Email already exists.");
            toast.error("Email already exists. Please login");
          } else {
            createCandidate(candidate);
          }
        })
        .catch((error) => {
          console.error(error.response);
          toast.error("Something went wrong");
          setShowRegister(true)
        });
    }

    setValidated(true);
  };
  function changeHandler(event) {
    setShowRegister(true);
    setCandidate((prevCandidate) => ({
      ...prevCandidate,
      [event.target.name]: event.target.value,
    }));
  }
  function createCandidate(candidate) {
    console.log(candidate);
    axios
      .post("http://localhost:8086/candidate/create", {
        ...candidate,
        [candidate.email]: candidate.email.trim().toLowerCase(),
      })
      .then((response) => {
        setShowRegister(true);
        console.log(response.data);
        context.handleLogin(response.data.candidateId);
        props.onHide();
        toast.success("Registered successfully");
        navigate("/courses");
      })
      .catch((error) => {
        console.error(error.response);
        toast.error("Something went wrong");
      });
  }
  function alphaOnly(event) {
    var key = event.keyCode;
    return key >= 65 && key <= 90;
  }
  return (
    <Modal
      {...props}
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
          className="mx-auto p-1"
        >
          <div style={{ height: "50vh" }} className="overflow-auto mb-3">
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput1">
              <Form.Label className="text-dark fw-bold">Firstname</Form.Label>
              <Form.Control
                minLength={3}
                required
                type="text"
                name="firstName"
                value={candidate.firstName}
                onChange={changeHandler}
                placeholder="Firstname"
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check First Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput2">
              <Form.Label className="text-dark fw-bold">Lastname</Form.Label>
              <Form.Control
                required
                minLength={3}
                value={candidate.lastName}
                name="lastName"
                type="text"
                placeholder="Lastname"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Last Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput3">
              <Form.Label className="text-dark fw-bold">Fullname</Form.Label>
              <Form.Control
                required
                minLength={3}
                value={candidate.fullName}
                name="fullName"
                type="text"
                placeholder="Fullname"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Full Name.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput7">
              <Form.Label className="text-dark fw-bold">Gender</Form.Label>
              <Form.Select
                required
                value={candidate.gender}
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
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput4">
              <Form.Label className="text-dark fw-bold">Email</Form.Label>
              <Form.Control
                required
                type="email"
                value={candidate.email}
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
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput5">
              <Form.Label className="text-dark fw-bold">Mobile</Form.Label>
              <Form.Control
                minLength={10}
                maxLength={10}
                required
                type="number"
                value={candidate.mobileNumber}
                name="mobileNumber"
                placeholder="Mobile"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check MobileNumber.
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput6">
              <Form.Label className="text-dark fw-bold">Address</Form.Label>
              <Form.Control
                required
                as="textarea"
                value={candidate.address}
                name="address"
                rows={3}
                placeholder="Your address"
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Address.
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput8">
              <Form.Label className="text-dark fw-bold">
                Set Password
              </Form.Label>
              <Form.Control
                required
                type="password"
                minLength={4}
                onChange={changeHandler}
                name="password"
                value={candidate.password}
                placeholder="Set Password"
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                <p>
                  {" "}
                  Please check Password(password should atleast 4 characters).{" "}
                </p>
              </Form.Control.Feedback>
            </Form.Group>
          </div>
          <Form.Group className="mx-auto" controlId="exampleForm.ControlInput9">
            <p className="text-danger fs-6 text-center fw-bold">{message}</p>
            <Button
              type="submit"
              disabled={!showRegister}
              className="btn-lg w-100 text-black"
              variant="warning"
            >
              {showRegister ? "Register" : "Please wait..."}
              {!showRegister && <Spinner animation="border" variant="dark" />}
            </Button>
          </Form.Group>
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

export default SignupModal;
