import React from "react";
import CoursesList from "./CoursesList";

function Course() {
  console.log("in course false");
  return <CoursesList count={false} />;
}

export default Course;
