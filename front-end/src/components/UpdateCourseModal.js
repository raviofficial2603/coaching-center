import React, { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function UpdateCourseModal(props) {
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
          Update
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

export default UpdateCourseModal;
