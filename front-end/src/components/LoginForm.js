import React, { useContext, useEffect, useState } from "react";
import { Button, Container, Form, InputGroup, Spinner } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import UserContext from "./UserContext";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
function LoginForm() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  const context = useContext(UserContext);
  const navigate = useNavigate();
  const [validated, setValidated] = useState(false);
  const [user, setUser] = useState({
    username: "",
    password: "",
  });
  function changeHandler(event) {
    setUser((prevUser) => ({
      ...prevUser,
      [event.target.name]: event.target.value,
    }));
    console.log(user);
    setShowLogin(true);
  }
  const [showLogin, setShowLogin] = useState(true);
  const handleSubmit = (event) => {
    const form = event.currentTarget;
    setShowLogin(false);
    event.preventDefault();
    if (form.checkValidity() === false) {
      event.stopPropagation();
      setMessage("Please Check All Fields");
      setShowLogin(true);
    } else {
      axios
        .post("http://localhost:8086/candidate/auth-user", {
          ...user,
          user: user.username.trim().toLowerCase(),
        })
        .then((response) => {
          setShowLogin(true);
          console.log(response.data);
          if (response.data == null)
            setMessage("No user found. Please register");
          else if (response.data === "failed") {
            toast.error("Login failed");
            setMessage("Invalid credentials");
          } else {
            toast.success("Loggedin successfully");
            context.handleLogin(response.data);
            navigate("/dashboard");
          }
        })
        .catch((error) => console.error(error.response));
    }

    // setValidated(true);
  };
  const [message, setMessage] = useState("      ");
  return (
    <div className="align-self-center mt-5" style={{ height: "77vh" }}>
      <Container className="w-50 mt-5 card align-self-center">
        <h1 className="mt-5">Awitez</h1>
        <Form
          noValidate
          validated={validated}
          onSubmit={handleSubmit}
          className="mx-auto p-5 align-self-center"
        >
          {/* <Form.Group  md="4" controlId="validationCustomUsername">
          <Form.Label>Username</Form.Label>
          
            
            <Form.Control
              type="email"
              placeholder="Username"
              aria-describedby="inputGroupPrepend"
              required
            />
            
          
        </Form.Group> */}
          <Form.Group
            className="mb-3 w-100 "
            controlId="exampleForm.ControlInput1"
          >
            <InputGroup hasValidation className="w-100">
              <Form.Control
                required
                type="email"
                name="username"
                placeholder="User email"
                value={user.username}
                onChange={changeHandler}
              />
              <Form.Control.Feedback type="valid">
                Looks good
              </Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">
                Please check Email.
              </Form.Control.Feedback>
            </InputGroup>
          </Form.Group>
          <Form.Group className="mb-3 " controlId="exampleForm.ControlInput2">
            <Form.Control
              required
              type="password"
              value={user.password}
              onChange={changeHandler}
              name="password"
              placeholder="Password"
            />
          </Form.Group>
          <Form.Group
            className="mb-3 mx-auto"
            controlId="exampleForm.ControlInput3"
          >
            <p className="text-danger fs-6 text-center fw-bold">{message}</p>
            <Button
              className="btn w-100"
              type="submit"
              disabled={!showLogin}
              variant="dark"
            >
              {showLogin ? "Login" : "Please wait..."}
              {!showLogin && (
                <Spinner animation="border" size="sm" variant="warning" />
              )}
            </Button>
          </Form.Group>
        </Form>
      </Container>
    </div>
  );
}

export default LoginForm;
