import React from "react";
import Carousel from "react-bootstrap/Carousel";
import img1 from "../images/CarouselImg1.jpg";
import img2 from "../images/CarouselImg2.jpg";
import img3 from "../images/CarouselImg3.jpg";
import img4 from "../images/studentg.jpg";
import { Link } from "react-router-dom";

function Slider() {
  return (
    <Carousel>
      <Carousel.Item interval={2000}>
        <img
          className="d-block w-100 slider-image"
          src={img4}
          alt="First slide"
        />
        <Carousel.Caption className="slider-text">
          <Link to={"/courses"} className="btn btn-success btn-lg mb-1">
            Get Started
          </Link>
          <p>Start your journey with world class mentorship</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item interval={3000}>
        <img
          className="d-block w-100 slider-image"
          src={img2}
          alt="Second slide"
        />
        <Carousel.Caption className="slider-text">
          <Link to={"/services"} className="btn btn-success btn-lg mb-1">
            Our Services
          </Link>
          <p>We add great value to your carrier</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item interval={3000}>
        <img
          className="d-block w-100 slider-image"
          src={img3}
          alt="Third slide"
        />
        <Carousel.Caption className="slider-text">
          <Link to={"/about"} className="btn btn-success btn-lg mb-1">
            About Us
          </Link>
          <p>Know our main moto and our objectives</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item interval={3000}>
        <img
          className="d-block w-100 slider-image"
          src={img1}
          alt="Second slide"
        />
        <Carousel.Caption className="slider-text">
          <Link to={"/courses"} className="btn btn-success btn-lg mb-1">
            Get Started
          </Link>
          <p>Start your journey with world class mentorship</p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default Slider;
