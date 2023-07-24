import axios from "axios";
import React, { useContext, useEffect, useState } from "react";
import { Container, Spinner } from "react-bootstrap";
import UserContext from "./UserContext";
import notfound from "../images/notfound.jfif";

function Dashboard() {
  const [enrollments, setEnrollments] = useState([]);
  const context = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    window.scrollTo(0, 0);
    axios
      .get(
        "http://localhost:8087/enrollment/get-enrollment-by-candidateId/" +
          context.userId
      )
      .then((response) => {
        console.log(response.data);
        setEnrollments(response.data);
        setIsLoading(false);
      })
      .catch((error) => console.error(error.response));
  }, []);
  return (
    <Container className="mt-5 overflow-auto" style={{ height: "77vh" }}>
      {enrollments.length ? (
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">Sl.No</th>
              <th scope="col">Course Id</th>
              <th scope="col">Branch Id</th>
              <th scope="col">Batch Id</th>
              <th scope="col">Course Duration(in months)</th>
              <th scope="col">Course Status</th>
              <th scope="col">Enrollment Status</th>
            </tr>
          </thead>
          <tbody>
            {enrollments.map((enrollment, index) => (
              <tr key={index}>
                <td scope="row">{index + 1}</td>
                <td>{enrollment.course.courseName}</td>
                <td>{enrollment.branch.location}</td>
                <td>{enrollment.batch ? enrollment.batch.batchId : "-"}</td>
                <td>{enrollment.course.courseDuration}</td>
                <td>
                  {enrollment.batch
                    ? enrollment.batch.completed
                      ? "Completed"
                      : "On Going"
                    : "-"}
                </td>
                <td>{enrollment.batch ? "Approved" : "Requested"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <Container style={{ height: "70vh", marginTop: "50px" }}>
          {isLoading ? (
            <Spinner animation="border" variant="dark" />
          ) : (
            <>
              <h1>No enrollments found</h1>
              <img src={notfound} />
            </>
          )}
        </Container>
      )}
    </Container>
  );
}

export default Dashboard;
