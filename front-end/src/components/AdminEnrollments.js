import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Spinner, Tab, Tabs } from "react-bootstrap";
import BatchForm from "./BatchForm";
import notfound from "../images/notfound.jfif";

const _ = require("lodash");
function AdminEnrollments() {
  const [selectedGroup, setSelectedGroup] = useState({});
  const [batchModalShow, setBatchModalShow] = React.useState(false);
  const [enrollments, setEnrollments] = useState([]);
  const [groupedEnrollments, setGroupedEnrollments] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  // console.log(groupedEnrollments)
  console.log(selectedGroup);
  console.log("selectedGroup");
  useEffect(() => {
    loadEnrollments();
    window.scrollTo(0, 0);
  }, []);
  function loadEnrollments() {
    axios
      .get("http://localhost:8091/admin/enrollment/enrollments")
      .then((response) => {
        setEnrollments(
          response.data.filter((enrollment) => enrollment.batchId == 0)
        );
        setGroupedEnrollments(
          _.groupBy(
            response.data.filter((enrollment) => enrollment.batchId == 0),
            (enrollment) => enrollment.branchId + "-" + enrollment.courseId
          )
        );
        // console.log(response.data)
        console.log(enrollments);
        setIsLoading(false);
      })
      .catch((error) => console.error(error.response));
  }
  return enrollments.length != 0 ? (
    <Container className="mt-3">
      <h1>Pending Enrollments</h1>
      <Tabs
        defaultActiveKey="All"
        id="justify-tab-example"
        className="mb-3 mt-3"
        justify
      >
        <Tab eventKey="All" title="All Enrollments">
          <table className="table table-hover">
            <thead>
              <tr>
                <th scope="col">Sl.No</th>
                <th scope="col">Enrollment Id</th>
                <th scope="col">Branch Id</th>
                <th scope="col">Candidate Id</th>
                <th scope="col">Course Id</th>
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
                    </tr>
                  )
              )}
            </tbody>
          </table>
        </Tab>
        <Tab eventKey="dd" title="Grouped Enrollments">
          {groupedEnrollments.length != 0 ? (
            <table className="table table-hover">
              <thead>
                <tr>
                  <th scope="col">Branch Id</th>
                  <th scope="col">Course Id</th>
                  <th scope="col">No:of Enrollments</th>
                  <th scope="col">Create Batch</th>
                </tr>
              </thead>
              <tbody>
                <>
                  {Object.keys(groupedEnrollments).map((group, i) => (
                    <tr key={i}>
                      <td>{group.split("-")[0]}</td>
                      <td>{group.split("-")[1]}</td>
                      <td>{groupedEnrollments[group].length}</td>
                      <td>
                        <Button
                          variant="success"
                          onClick={() => {
                            setBatchModalShow(true);
                            setSelectedGroup({
                              branchId: group.split("-")[0],
                              courseId: group.split("-")[1],
                              enrollments: [...groupedEnrollments[group]],
                            });
                          }}
                        >
                          Create Batch
                        </Button>
                      </td>
                    </tr>
                  ))}
                </>
              </tbody>
            </table>
          ) : (
            <div style={{ height: "77vh" }}> No Enrollments Found</div>
          )}
          {batchModalShow && (
            <BatchForm
              loadEnrollments={loadEnrollments}
              show={batchModalShow}
              groupedEnrollments={selectedGroup}
              onHide={() => setBatchModalShow(false)}
            />
          )}
        </Tab>
      </Tabs>
    </Container>
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
  );
}

export default AdminEnrollments;
