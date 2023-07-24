import React, { useState } from "react";
import { Button, Form, InputGroup, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function LoginModal(props) {
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    event.preventDefault();
    if (form.checkValidity() === false) {
      event.stopPropagation();
      setMessage("Please Check All Fields");
    } else {
      props.onHide();
      navigate("/dashboard");
    }

    setValidated(true);
  };
  const [message, setMessage] = useState("      ");
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
          className="mx-auto p-3"
        >
          {/* <Form.Group  md="4" controlId="validationCustomUsername">
          <Form.Label>Username</Form.Label>
          
            
            <Form.Control
              type="email"
              placeholder="Username"
              aria-describedby="inputGroupPrepend"
              required
            />
            
          
        </Form.Group> */}
          <Form.Group
            className="mb-3 w-100 "
            controlId="exampleForm.ControlInput1"
          >
            <Form.Label className="text-dark fw-bold">Username</Form.Label>
            <InputGroup hasValidation className="w-100">
              <Form.Control
                required
                type="email"
                placeholder="Email or Username"
              />
            </InputGroup>
          </Form.Group>
          <Form.Group className="mb-5 " controlId="exampleForm.ControlInput2">
            <Form.Label className="text-dark fw-bold">Password</Form.Label>
            <Form.Control required type="password" placeholder="Password" />
          </Form.Group>
          <Form.Group
            className="mb-3 mx-auto"
            controlId="exampleForm.ControlInput3"
          >
            <p className="text-danger fs-6 text-center fw-bold">{message}</p>
            <Button className="btn-lg w-100" type="submit" variant="dark">
              Login
            </Button>
          </Form.Group>
          <Form.Group
            className="mb-3 mx-auto"
            controlId="exampleForm.ControlInput2"
          >
            {props.Register && (
              <Button
                onClick={props.register}
                className="btn-lg w-100 text-black"
                variant="warning"
              >
                Register
              </Button>
            )}
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

export default LoginModal;
