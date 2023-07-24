import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Spinner } from "react-bootstrap";

import notfound from "../images/notfound.jfif";

import MentorForm from "./MentorForm";

function AdminMentors() {
  const [mentors, setMentors] = useState([]);
  const [addMentorModalShow, setAddMentorModalShow] = React.useState(false);
  const [selectedMentor, setSelectedMentor] = useState({});
  const [updateMentorModalShow, setUpdateMentorModalShow] =
    React.useState(false);
  const [branches, setBranches] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => (loadMentors(), loadBranches(), window.scrollTo(0, 0)), []);
  function loadBranches() {
    axios
      .get("http://localhost:8091/admin/branch/branches")
      .then((response) => {
        return response.data;
      })
      .then((response) => {
        setBranches(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  function loadMentors() {
    axios
      .get("http://localhost:8091/admin/mentor/mentors")
      .then((response) => {
        return response.data;
      })
      .then((response) => {
        setMentors(response);
        setIsLoading(false);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  function addMentor(mentor) {
    axios
      .post(
        "http://localhost:8091/admin/mentor/create",
        JSON.stringify({
          ...mentor,
        }),
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      )
      .then((response) => {
        return response.data;
      })
      .then(() => {
        loadMentors();
      })
      .catch((error) => {
        debugger;
        console.log(error);
      });
  }
  function updateMentor(mentor) {
    axios
      .put(
        "http://localhost:8091/admin/mentor/update/" + mentor.mentorId,
        JSON.stringify({
          ...mentor,
        }),
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      )
      .then((response) => {
        return response.data;
      })
      .then(() => {
        loadMentors();
      })
      .catch((error) => {
        console.log(error);
      });
  }
  return (
    <Container className="mt-5">
      <Button
        className="btn-success btn-lg"
        onClick={() => setAddMentorModalShow(true)}
      >
        Add Mentor
      </Button>

      {addMentorModalShow && (
        <MentorForm
          show={addMentorModalShow}
          onSubmitHandler={addMentor}
          mentor={{}}
          buttonText={"Add"}
          btnColor={" btn-success "}
          branches={branches}
          onHide={() => setAddMentorModalShow(false)}
        />
      )}
      {mentors.length != 0 ? (
        <table className="table table-hover mt-3">
          <thead>
            <tr>
              <th scope="col">Sl.No</th>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">Gender</th>
              <th scope="col">Email</th>
              <th scope="col">Mobile Number</th>
              <th scope="col">Qualification</th>
              <th scope="col">Skills</th>
              <th scope="col">Experience</th>
              <th scope="col">Branch Id</th>

              <th scope="col">Update</th>
            </tr>
          </thead>
          <tbody>
            {mentors.map((mentor, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{mentor.mentorId}</td>
                <td>{mentor.fullName}</td>
                <td>{mentor.gender}</td>
                <td>{mentor.email}</td>
                <td>{mentor.mobileNumber}</td>
                <td>{mentor.qualification}</td>
                <td>{mentor.skills}</td>
                <td>{mentor.experience}</td>
                <td>{mentor.branchId}</td>

                <td>
                  <Button
                    onClick={() => {
                      setUpdateMentorModalShow(true);
                      setSelectedMentor(mentor);
                    }}
                  >
                    Update
                  </Button>
                </td>
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
              <h1>No mentors found</h1>
              <img src={notfound} />
            </>
          )}
        </Container>
      )}
      {updateMentorModalShow && (
        <MentorForm
          buttonText={"Update"}
          onSubmitHandler={updateMentor}
          branches={branches}
          btnColor={" btn-primary "}
          mentor={selectedMentor}
          onHide={() => setUpdateMentorModalShow(false)}
        />
      )}
    </Container>
  );
}

export default AdminMentors;
