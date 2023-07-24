import React from "react";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import "./Footer.css";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
function Footer() {
  return (
    <Container fluid className="bg-dark mt-5 p-5">
      <Row>
        <p className="footer-subscription-heading">
          It May Look Exaggeration But The Fact of Truth Cannot Be Hidden, We
          Are The First Choice To Many of The Students And Corporate
          Professionals in Software Training.
        </p>
      </Row>
      <Row>
        <p className="footer-subscription-text">
          Every goal requires constant learning
        </p>
      </Row>
      <Row className="mb-3">
        <Col className="input-areas">
          <h1 className="text-light">Mailus: Awitez@cognizant.com</h1>
        </Col>
      </Row>
      <Row className="d-flex justify-content-around text-evenly ">
        <Col className="d-flex footer-link-items mt-3 justify-content-center">
          <h2>About</h2>
          <Link to="/sign-up">How it works</Link>
          <Link to="/">Testimonials</Link>
          <Link to="/">Careers</Link>
          <Link to="/">Investors</Link>
        </Col>
        <Col className=" footer-link-items">
          <h2>Contact</h2>
          <Link to="/">Contact</Link>
          <Link to="/">Support</Link>
          <Link to="/">Destinations</Link>
          <Link to="/">Sponsorships</Link>
        </Col>
        <Col className="footer-link-items">
          <h2>Videos</h2>
          <Link to="/">Submit Video</Link>
          <Link to="/">Ambassadors</Link>
          <Link to="/">Agency</Link>
          <Link to="/">Influencer</Link>
        </Col>
        <Col className=" footer-link-items">
          <h2>Media</h2>
          <Link to="/">Instagram</Link>
          <Link to="/">Facebook</Link>
          <Link to="/">Youtube</Link>
          <Link to="/">Twitter</Link>
        </Col>
      </Row>
      <Row className="mt-5">
        <p className="text-light text-center fw-bold">
          &copy; 2023 Copyright: All rights reserved
        </p>
      </Row>
    </Container>

    // <div className='footer-container mt-3'>

    //   <section className='footer-subscription'>
    //     <p className='footer-subscription-heading'>
    //     It May Look Exaggeration But The Fact of Truth Cannot Be Hidden, We Are The First Choice To Many of The Students And Corporate Professionals in Software Training.
    //     </p>
    // <p className='footer-subscription-text'>
    //     Every goal requires constant learning
    // </p>
    // <div className='input-areas'>
    //   <form>
    //     <input
    //       className='footer-input'
    //       name='email'
    //       type='email'
    //       placeholder='Your Email'
    //     />
    //     <Button className=' btn-light btn-lg btn-block'>Subscribe</Button>
    //   </form>
    //     </div>
    //   </section>
    //   <div className=' container footer-links'>
    //     <div className='row footer-link-wrapper'>
    //       <div className='col footer-link-items'>
    //         <h2>About Us</h2>
    //         <Link to='/sign-up'>How it works</Link>
    //         <Link to='/'>Testimonials</Link>
    //         <Link to='/'>Careers</Link>
    //         <Link to='/'>Investors</Link>
    //         <Link to='/'>Terms of Service</Link>
    //       </div>
    //       <div className='col footer-link-items'>
    //         <h2>Contact Us</h2>
    //         <Link to='/'>Contact</Link>
    //         <Link to='/'>Support</Link>
    //         <Link to='/'>Destinations</Link>
    //         <Link to='/'>Sponsorships</Link>
    //       </div>

    //       <div className='col footer-link-items'>
    //         <h2>Videos</h2>
    //         <Link to='/'>Submit Video</Link>
    //         <Link to='/'>Ambassadors</Link>
    //         <Link to='/'>Agency</Link>
    //         <Link to='/'>Influencer</Link>
    //       </div>
    //       <div className='col footer-link-items'>
    //         <h2>Social Media</h2>
    //         <Link to='/'>Instagram</Link>
    //         <Link to='/'>Facebook</Link>
    //         <Link to='/'>Youtube</Link>
    //         <Link to='/'>Twitter</Link>
    //       </div>
    //     </div>
    //   </div>

    // </div>
  );
}

export default Footer;
