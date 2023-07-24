import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Container, Spinner } from "react-bootstrap";
import BranchForm from "./BranchForm";
import { toast } from "react-toastify";
import notfound from "../images/notfound.jfif";

function AdminBranches() {
  const [branches, setBranches] = useState([]);
  const [addBranchModalShow, setAddBranchModalShow] = React.useState(false);
  const [selectedBranch, setSelectedBranch] = useState({});
  const [updateBranchModalShow, setUpdateBranchModalShow] =
    React.useState(false);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    loadBranches();
    window.scrollTo(0, 0);
  }, []);
  function loadBranches() {
    axios
      .get("http://localhost:8091/admin/branch/branches")
      .then((response) => {
        setBranches(response.data);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error(error.response);
        toast.error("Something went wrong");
      });
  }
  function addBranch(branch) {
    axios
      .post("http://localhost:8091/admin/branch/create", {
        ...branch,
      })
      .then((response) => {
        console.log(response.data);
        loadBranches();
      })
      .catch((error) => {
        console.error(error.response);
        toast.error("Something went wrong");
      });
  }
  function updateBranch(branch) {
    axios
      .put("http://localhost:8091/admin/branch/update/" + branch.branchId, {
        ...branch,
      })
      .then((response) => {
        // console.log(response.data);
        loadBranches();
      })
      .catch((error) => {
        console.error(error.response);
        toast.error("Something went wrong");
      });
  }
  return (
    <Container className="mt-5">
      <Button
        className="btn-success btn-lg"
        onClick={() => setAddBranchModalShow(true)}
      >
        Add Branch
      </Button>

      {addBranchModalShow && (
        <BranchForm
          show={addBranchModalShow}
          onSubmitHandler={addBranch}
          branch={{}}
          buttonText={"Add"}
          btnColor={" btn-success "}
          onHide={() => setAddBranchModalShow(false)}
        />
      )}
      {branches.length != 0 ? (
        <table className="table table-hover mt-3">
          <thead>
            <tr>
              <th scope="col">Sl.No</th>
              <th scope="col">Id</th>
              <th scope="col">Location</th>
              <th scope="col">Mobile Number</th>
            </tr>
          </thead>
          <tbody>
            {branches.map((branch, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{branch.branchId}</td>
                <td>{branch.location}</td>
                <td>{branch.mobileNumber}</td>

                <td>
                  <Button
                    onClick={() => {
                      setUpdateBranchModalShow(true);
                      setSelectedBranch(branch);
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
              <h1>No branches found</h1>
              <img src={notfound} />
            </>
          )}
        </Container>
      )}
      {updateBranchModalShow && (
        <BranchForm
          buttonText={"Update"}
          onSubmitHandler={updateBranch}
          show={updateBranchModalShow}
          btnColor={" btn-primary "}
          branch={selectedBranch}
          onHide={() => setUpdateBranchModalShow(false)}
        />
      )}
    </Container>
  );
}

export default AdminBranches;
