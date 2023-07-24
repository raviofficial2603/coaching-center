import React from "react";
import { Button, ListGroup, Modal } from "react-bootstrap";

function CourseModal(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          {props.course.courseName}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h5>Description</h5>

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
                  {props.course.branches.map((branch) => branch.location + " ")}{" "}
                </p>
              </td>
            </tr>
          </tbody>
        </table>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default CourseModal;
