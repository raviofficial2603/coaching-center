import React, { useContext, useState } from "react";
import { Button, ListGroup, Modal } from "react-bootstrap";
import axios from "axios";
import UserContext from "./UserContext";
function EnrollmentModal(props) {
  const [branch, setBranch] = useState(1);
  const context = useContext(UserContext);
  const [isEnrolled, setIsEnrolled] = useState(false);
  console.log(context.userId);
  function enrollmentHandler(e) {
    e.preventDefault();
    console.log(branch);
    axios
      .post("http://localhost:8087/enrollment/create", {
        candidateId: context.userId,
        courseId: props.course.courseId,
        branchId: branch,
        rating: 3,
        enrollmentStatus: "requested",
      })
      .then((response) => {
        console.log(response.data);
        console.log(context.userId);
        setIsEnrolled(true);
      })
      .catch((error) => console.error(error.response));
  }
  function branchHandler(e) {
    setBranch(e.target.value);
    console.log(branch);
  }
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">Enrollment</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h5>Details</h5>
        <form onSubmit={enrollmentHandler}>
          <table class="table">
            <tbody>
              <tr>
                <th scope="row">Course Name</th>
                <td>{props.course.courseName}</td>
              </tr>
              <tr>
                <th scope="row">Course Description</th>
                <td>{props.course.courseDescription}</td>
              </tr>
              <tr>
                <th scope="row">Course Duration</th>
                <td>{props.course.courseDuration} Months</td>
              </tr>
              <tr>
                <th scope="row">Course Fee</th>
                <td>{props.course.courseFee}/-</td>
              </tr>
              <tr>
                <th scope="row">Branches</th>
                <td>
                  <p>
                    {props.course.branches.map(
                      (branch) => branch.location + " "
                    )}
                  </p>
                </td>
              </tr>
              <tr>
                <th scope="row">
                  <label for="branch">Choose a Branch:</label>
                </th>
                <td>
                  <select
                    name="branch"
                    id="branch"
                    onChange={branchHandler}
                    value={branch}
                  >
                    {props.course.branches.map((branch) => (
                      <option key={branch.branchId} value={branch.branchId}>
                        {branch.location}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
          <Button
            type="submit"
            className="mx-auto d-flex btn-success"
            disabled={isEnrolled}
          >
            {isEnrolled ? "Requested" : "Request Enrollment"}
          </Button>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="dark" onClick={props.onHide}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default EnrollmentModal;
