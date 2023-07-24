import { faStar } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useContext } from "react";
import { Button, Container, Nav, Navbar } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import UserContext from "./UserContext";

function AdminNav(props) {
  const navigate = useNavigate();
  const [loginModalShow, setLoginModalShow] = React.useState(false);
  const context = useContext(UserContext);
  console.log(typeof context.isAdmin);
  const local = JSON.parse(localStorage.getItem("isAdmin"));
  return (
    <Navbar bg="dark" data-bs-theme="dark" sticky="top">
      <Container>
        <Navbar.Brand href="/admin">
          Awitez <FontAwesomeIcon icon={faStar} />
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto" variant="underline">
            <Nav.Link as={Link} to="/admin">
              Home
            </Nav.Link>
            <Nav.Link as={Link} to="/admin/courses">
              Courses
            </Nav.Link>
            <Nav.Link as={Link} to="/admin/enrollments">
              Enrollments
            </Nav.Link>
            <Nav.Link as={Link} to="/admin/mentors">
              Mentors
            </Nav.Link>
            <Nav.Link as={Link} to="/admin/branches">
              Branches
            </Nav.Link>
            <Nav.Link as={Link} to="/admin/batches">
              Batches
            </Nav.Link>

            {!local && (
              <Button
                variant="outline-warning"
                onClick={() => {
                  navigate("/admin");
                }}
              >
                Admin Login
              </Button>
            )}
            {local && (
              <Button
                variant="outline-warning"
                onClick={() => {
                  context.handleAdminLogout();
                  navigate("/admin");
                }}
              >
                Admin Logout
              </Button>
            )}

            <LoginModal
              show={loginModalShow}
              showRegister={false}
              onHide={() => setLoginModalShow(false)}
            />
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default AdminNav;
