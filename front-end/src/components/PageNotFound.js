import React from "react";
import { Container } from "react-bootstrap";
import PageNotFoundImg from "../images/404-Page-Not-Found.png";
function PageNotFound() {
  return (
    <Container className="mt-5 mb-5">
      <img src={PageNotFoundImg} />
    </Container>
  );
}

export default PageNotFound;
