import React, { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function BranchForm(props) {
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);

  const [branch, setBranch] = useState(props.branch);

  useEffect(() => {
    setBranch(props.branch);
    setValidated(false);
  }, [props.branch]);

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
      // navigate('/admin/branchs')
      props.onSubmitHandler(branch);
      props.onHide();
    }
  };
  function changeHandler(e) {
    setBranch((prevState) => ({
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
          <Form.Group
            md="4"
            className="mb-4 fw-bold"
            controlId="validationCustom01"
          >
            <Form.Label>Location</Form.Label>
            <Form.Control
              required
              type="text"
              placeholder="Enter Location"
              minLength={3}
              onChange={changeHandler}
              name="location"
              value={branch.location}
              onkeydown={"return /[a-z]/i.test(event.key)"}
            />

            <Form.Control.Feedback type="valid">
              Looks good
            </Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">
              Please check Location.
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group
            md="4"
            className="mb-4 fw-bold"
            controlId="validationCustom02"
          >
            <Form.Label>Mobile Number</Form.Label>
            <Form.Control
              className="form-control"
              required
              type="number"
              placeholder="Enter Mobile Number"
              onChange={changeHandler}
              name="mobileNumber"
              value={branch.mobileNumber}
            />
            <Form.Control.Feedback type="valid">
              Looks good
            </Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">
              Please check Mobile number.
            </Form.Control.Feedback>
          </Form.Group>

          <p className="text-danger mx-auto text-center fw-bold">{message}</p>
          <Button
            className={props.btnColor + " btn-lg d-flex mx-auto mb-0"}
            type="submit"
          >
            {props.buttonText} branch
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

export default BranchForm;
