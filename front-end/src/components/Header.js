import React, { useContext } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { faStar, faFacebook } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./Header.css";
import Button from "react-bootstrap/Button";
import { Link, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import SignupModal from "./SignupModal";
import UserContext from "./UserContext";
function Header(props) {
  const navigate = useNavigate();
  const context = useContext(UserContext);
  const [loginModalShow, setLoginModalShow] = React.useState(props.showLogin);
  const [signupModalShow, setSignupModalShow] = React.useState(false);

  return (
    <Navbar bg="dark" variant="dark" expand="lg" sticky="top">
      <Container>
        <Navbar.Brand className="fw-bolder" as={Link} to="/">
          Awitez <FontAwesomeIcon icon={faStar} />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto" variant="underline">
            <Nav.Link as={Link} to="/">
              Home
            </Nav.Link>
            <Nav.Link as={Link} to="/courses">
              Courses
            </Nav.Link>
            <Nav.Link as={Link} to="/services">
              Services
            </Nav.Link>
            <Nav.Link as={Link} to="/about">
              About Us
            </Nav.Link>

            {context.userId === "null" || context.userId == null ? (
              <Button variant="outline-info" onClick={() => navigate("/login")}>
                Login
              </Button>
            ) : (
              <Button
                variant="outline-info"
                onClick={() => {
                  console.log(
                    typeof context.userId,
                    context.userId,
                    "context userId"
                  );
                  context.handleLogout();
                  navigate("/");
                }}
              >
                Logout
              </Button>
            )}
            {/* {(context.userId==null) ?<Button variant="outline-info" onClick={() => setLoginModalShow(true)}>
              Login
            </Button>:<Button variant="outline-info" onClick={() => context.handleLogout()}>
              Logout
            </Button>} */}

            {loginModalShow && (
              <LoginModal
                showRegister={true}
                show={loginModalShow}
                register={() => {
                  setLoginModalShow(false);
                  setSignupModalShow(true);
                }}
                onHide={() => setLoginModalShow(false)}
              />
            )}

            {context.userId === "null" || context.userId == null ? (
              <Button
                variant="outline-warning"
                onClick={() => setSignupModalShow(true)}
              >
                Sign Up
              </Button>
            ) : (
              <Button
                variant="outline-light"
                onClick={() => navigate("/dashboard")}
              >
                Dashboard
              </Button>
            )}
            {signupModalShow && (
              <SignupModal
                show={signupModalShow}
                onHide={() => setSignupModalShow(false)}
              />
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;
