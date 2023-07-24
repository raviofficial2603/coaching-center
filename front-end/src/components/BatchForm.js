import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Form, Modal } from "react-bootstrap";
import notfound from "../images/notfound.jfif";

function BatchForm(props) {
  const [mentors, setMentors] = useState([]);
  const [selectedMentor, setSelectedMentor] = useState();
  const [batchId, setBatchId] = useState();
  const [enrollments, setEnrollments] = useState(
    props.groupedEnrollments.enrollments
  );
  const [batchCreated, setBatchCreated] = useState(false);
  const [selectedEnrollment, setSelectedEnrollment] = useState();
  console.log(props);
  const [validated, setValidated] = useState(false);
  function addToBatch(enrollment) {
    console.log(enrollment.enrollmentId);
    console.log("In addToBatch");
    console.log({
      ...enrollment,
      batchId: batchId,
      status: "Approved",
    });
    axios
      .put(
        "http://localhost:8091/admin/enrollment/update/" +
          enrollment.enrollmentId,
        {
          ...enrollment,
          batchId: batchId,
          status: "Approved",
        }
      )
      .then((response) => {
        console.log(response.data);
        setEnrollments([
          ...enrollments.filter(
            (e) => e.enrollmentId != enrollment.enrollmentId
          ),
        ]);
        props.loadEnrollments();
      })
      .catch((error) => console.error(error.response));
  }
  const createBatch = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8091/admin/batch/create", {
        assignedMentorId: selectedMentor,
        branchId: props.groupedEnrollments.branchId,
        courseId: props.groupedEnrollments.courseId,
      })
      .then((response) => {
        setBatchId(response.data.batchId);
        setBatchCreated(true);
      })
      .catch((error) => console.error(error.response));
  };
  function changeHandler(e) {
    setSelectedMentor(e.target.value);
  }
  useEffect(() => {
    axios
      .get(
        "http://localhost:8091/admin/get-mentors-by-branchid/" +
          props.groupedEnrollments.branchId
      )
      .then((response) => {
        setMentors(response.data);
      })
      .catch((error) => console.error(error.response));
  }, []);
  return (
    <Modal
      {...props}
      size="lg"
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
        <Form onSubmit={createBatch}>
          <Form.Group className="mb-4 fw-bold" controlId="validationCustom04">
            <Form.Label className="text-dark fw-bold">Mentor</Form.Label>
            <Form.Select
              required
              value={selectedMentor}
              onChange={changeHandler}
              aria-label="Default select example"
            >
              <option value="">Select Mentor</option>
              {mentors.map((mentor) => (
                <option value={mentor.mentorId}>
                  {mentor.mentorId}-{mentor.fullName}
                </option>
              ))}
            </Form.Select>
            <Form.Control.Feedback type="valid">
              Looks good
            </Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">
              Please select Mentor.
            </Form.Control.Feedback>
          </Form.Group>

          <Button
            className="mx-auto d-flex"
            type="submit"
            disabled={batchCreated}
          >
            Initiate New Batch
          </Button>
        </Form>
        {mentors.length != 0 ? (
          <table className="table table-hover">
            <thead>
              <tr>
                <th scope="col">Sl.No</th>
                <th scope="col">Enrollment Id</th>
                <th scope="col">Branch Id</th>
                <th scope="col">Candidate Id</th>
                <th scope="col">Course Id</th>
                <th scope="col">Add to Batch</th>
              </tr>
            </thead>
            <tbody>
              {enrollments.map(
                (enrollment, index) =>
                  enrollment.batchId === 0 && (
                    <tr key={index}>
                      <td scope="row">{index + 1}</td>
                      <td>{enrollment.enrollmentId}</td>
                      <td>{enrollment.branchId}</td>
                      <td>{enrollment.candidateId}</td>
                      <td>{enrollment.courseId}</td>
                      <td>
                        <Button
                          onClick={() => {
                            addToBatch(enrollment);
                          }}
                          disabled={!batchCreated}
                        >
                          Add
                        </Button>
                      </td>
                    </tr>
                  )
              )}
            </tbody>
          </table>
        ) : (
          <>
            <h1>No enrollments found</h1>
            <img src={notfound} />
          </>
        )}
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default BatchForm;
