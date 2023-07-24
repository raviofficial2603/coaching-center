import axios from "axios";
import React, { useEffect, useState } from "react";
import { Container, Spinner } from "react-bootstrap";
import Table from "react-bootstrap/Table";

function AdminHome() {
  const [courseCount, setCourseCount] = useState(
    <Spinner animation="border" variant="info" />
  );
  const [mentorCount, setMentorCount] = useState(
    <Spinner animation="border" variant="info" />
  );
  const [branchCount, setBranchCount] = useState(
    <Spinner animation="border" variant="info" />
  );
  const [batchCount, setBatchCount] = useState(
    <Spinner animation="border" variant="info" />
  );
  useEffect(() => {
    window.scrollTo(0, 0);
    axios
      .get("http://localhost:8091/admin/batch/batches")
      .then((response) => {
        setBatchCount(response.data.length);
        console.log(response.data);
      })
      .catch((error) => console.error(error.response));
    axios
      .get("http://localhost:8091/admin/branch/branches")
      .then((response) => {
        setBranchCount(response.data.length);
      })
      .catch((error) => console.error(error.response));
    axios
      .get("http://localhost:8091/admin/course/courses")
      .then((response) => {
        setCourseCount(response.data.length);
      })
      .catch((error) => console.error(error.response));
    axios
      .get("http://localhost:8091/admin/mentor/mentors")
      .then((response) => {
        setMentorCount(response.data.length);
      })
      .catch((error) => console.error(error.response));
  }, []);
  return (
    <Container className="mt-5">
      <h1>Hello, Admin</h1>
      <Table className="mx-auto w-50 mt-5" striped bordered hover>
        <tbody>
          <tr>
            <td>Available Courses</td>
            <td>{courseCount}</td>
          </tr>
          <tr>
            <td>Availble Mentors</td>
            <td>{mentorCount}</td>
          </tr>
          <tr>
            <td>Availble Branches</td>
            <td>{branchCount}</td>
          </tr>
          <tr>
            <td>Availble Batches</td>
            <td>{batchCount}</td>
          </tr>
        </tbody>
      </Table>
    </Container>
  );
}

export default AdminHome;
