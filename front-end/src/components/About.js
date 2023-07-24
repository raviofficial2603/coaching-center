import React, { useEffect } from "react";

import { Row, Container, Col, Card } from "react-bootstrap";
import Man from "../images/chairman-1.jpg";
import Branch from "../images/branch1.jpg";
import Professor from "../images/professor.jfif";
import cbranch1 from "../images/cbranch1.jpg";
import cbranch2 from "../images/cbranch2.jpg";
import cbranch3 from "../images/cbranch3.jpg";
import cbranch4 from "../images/cbranch4.jpg";
import cprof1 from "../images/cprof1.jpeg";
import cprof2 from "../images/cprof2.jpeg";
import cprof3 from "../images/cprof3.jpeg";
import cprof4 from "../images/cprof4.jpeg";
import cprof5 from "../images/cprof5.jpeg";
import cprof6 from "../images/cprof6.jpeg";
import cprof7 from "../images/cprof7.jpeg";
import cprof8 from "../images/cprof10.jpeg";
import cprof9 from "../images/cprof9.jpeg";
const profs = [
  cprof1,
  cprof2,
  cprof3,
  cprof4,
  cprof5,
  cprof6,
  cprof7,
  cprof8,
  cprof9,
];
const profname = [
  "Jayita Tagore",
  "Manju Sharma",
  "Rekha Meena",
  "Monika Ram",
  "Sumati Datt",
  "Ramana Murthy",
  "Omkarnath",
  "Nageshwara Rao",
  "Jaya Prakash",
];
const branches = [cbranch1, cbranch2, cbranch3, cbranch4];
const branchname = ["Ameerpet", "Gachibowli", "Kondapur", "Madhapur"];
function About() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <Container className="card">
      <Row className="pt-5 card-body">
        <h1>Our beloved Chairman</h1>
        <img
          src={Man}
          style={{ height: "60vh", width: "auto", margin: "auto" }}
          className="rounded-top"
        ></img>
        <p className="fs-3">Dr.Chilasani Gopal Reddy</p>
        <p style={{ textAlign: "justify" }}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam at nisl
          eget ligula sagittis tincidunt. Vivamus ex lectus, auctor at pretium
          eu, aliquam vitae lacus. Vestibulum imperdiet est sit amet ligula
          maximus, vitae tincidunt dolor scelerisque. Sed fringilla ante ut nunc
          dictum, in pellentesque erat fermentum. Duis eget tellus dui.
          Vestibulum sapien nunc, vulputate eget risus pellentesque, bibendum
          congue lorem. Mauris ac est sed enim ullamcorper semper nec ac tortor.
          Fusce ut lectus sem. Pellentesque dapibus malesuada scelerisque.
          Curabitur erat nisl, laoreet sed diam nec, aliquet imperdiet lacus. Ut
          bibendum sit amet orci egestas lobortis. Nulla sit amet tortor id
          nulla placerat feugiat.
        </p>
      </Row>
      <h2>Our Branches</h2>
      <Row xs={1} md={2} className="g-4 mb-5">
        {Array.from({ length: 4 }).map((_, idx) => (
          <Col key={idx + 1}>
            <Card>
              <Card.Img variant="top" src={branches[idx]} />
              <Card.Body>
                <Card.Title> {branchname[idx]}</Card.Title>
                <Card.Text>
                  It is a long established fact that a reader will be distracted
                  by the readable content of a page when looking at its layout.
                  The point of using Lorem Ipsum is that it has a more-or-less
                  normal distribution of letters, as opposed to using 'Content
                  here, content here', making it look like readable English.
                  Many desktop publishing packages and web page editors now use
                  Lorem Ipsum as their default model text.
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>{" "}
      <h2>Our Mentors</h2>
      <Row xs={1} md={3} className="g-3  mb-3">
        {Array.from({ length: 9 }).map((_, idx) => (
          <Col key={idx + 1}>
            <Card>
              <Card.Img
                variant="top"
                className="w-100 object-fit-contain"
                src={profs[idx]}
              />
              <Card.Body>
                <Card.Title>{profname[idx]}</Card.Title>
                <Card.Text>
                  There are many variations of passages of Lorem Ipsum
                  available, but the majority have suffered alteration in some
                  form, by injected humour, or randomised words which don't look
                  even slightly believable. If you are going to use a passage of
                  Lorem Ipsum, you need to be sure there isn't anything
                  embarrassing hidden in the middle of text. All the Lorem Ipsum
                  generators on the Internet tend to repeat predefined chunks as
                  necessary.
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default About;
