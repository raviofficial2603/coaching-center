import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Spinner } from "react-bootstrap";
import { toast } from "react-toastify";
import notfound from "../images/notfound.jfif";

function AdminBatches() {
  const [batches, setBatches] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  function completeBatch(batch) {
    console.log("in complete bath");
    axios
      .put("http://localhost:8091/admin/batch/update/" + batch.id, {
        batchId: batch.id,
        assignedMentorId: batch.mentor.mentorId,
        branchId: batch.branch.branchId,
        courseId: batch.course.courseId,
        completed: true,
      })
      .then((response) => {
        console.log("on response.data");
        console.log(response.data);
        loadBatches();
        toast.success("Batch marked as completed");
      })
      .catch((error) => {
        console.error(error.response);
        toast.error("Something went wrong");
      });
  }
  function loadBatches() {
    axios
      .get("http://localhost:8091/admin/batch/batches")
      .then((response) => {
        setBatches(response.data);
        console.log(response.data);
        setIsLoading(false);
      })
      .catch((error) => {
        console.log(error);
        toast.error("Something went wrong");
      });
  }
  useEffect(() => {
    loadBatches();
    window.scrollTo(0, 0);
  }, []);
  return batches.length !== 0 ? (
    <Container className="mt-5">
      <table className="table table-hover mt-3">
        <thead>
          <tr>
            <th scope="col">Sl.No</th>
            <th scope="col">Id</th>
            <th scope="col">Branch Id</th>
            <th scope="col">Branch Location</th>
            <th scope="col">Course Id</th>
            <th scope="col">Course Name</th>
            <th scope="col">Mentor Id</th>
            <th scope="col">Mentor Name</th>
            <th scope="col">Completion Status</th>
          </tr>
        </thead>
        <tbody>
          {batches.map((batch, index) => (
            <tr key={index}>
              <th scope="row">{index + 1}</th>
              <td>{batch.id}</td>
              <td>{batch.branch.branchId}</td>
              <td>{batch.branch.location}</td>
              <td>{batch.course.courseId}</td>
              <td>{batch.course.courseName}</td>
              <td>{batch.mentor.mentorId}</td>
              <td>{batch.mentor.fullName}</td>
              <td>{batch.completed ? "Completed" : "On going"}</td>
              <td>
                <Button
                  disabled={batch.completed}
                  onClick={() => {
                    completeBatch(batch);
                  }}
                >
                  Mark As Complete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  ) : (
    <Container style={{ height: "70vh", marginTop: "50px" }}>
      {isLoading ? (
        <Spinner animation="border" variant="dark" />
      ) : (
        <>
          <h1>No batches found</h1>
          <img src={notfound} />
        </>
      )}
    </Container>
  );
}

export default AdminBatches;
