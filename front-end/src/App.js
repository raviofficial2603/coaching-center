import React, { useState } from "react";

import "./App.css";

import Header from "./components/Header";

import Slider from "./components/Slider";

import Home from "./components/Home";

import About from "./components/About";

import Course from "./components/Course";

import Footer from "./components/Footer";

import {
  BrowserRouter as Router,
  Routes,
  Route,
  useNavigate,
  Navigate,
} from "react-router-dom";

import Services from "./components/Services";

import Dashboard from "./components/Dashboard";

import AdminNav from "./components/AdminNav";

import AdminCourses from "./components/AdminCourses";

import AdminEnrollments from "./components/AdminEnrollments";

import AdminMentors from "./components/AdminMentors";

import AdminBranches from "./components/AdminBranches";

import AdminBatches from "./components/AdminBatches";

import AdminHome from "./components/AdminHome";

import LoginForm from "./components/LoginForm";

import PageNotFound from "./components/PageNotFound";

import UserContext from "./components/UserContext";

import AdminLoginForm from "./components/AdminLoginForm";
import { ToastContainer } from "react-toastify";

const USER_TYPES = {
  PUBLIC: "PUBLIC",

  USER: "USER",

  ADMIN: "ADMIN",
};

var CURRENT_USER_TYPE = USER_TYPES.PUBLIC;

function App() {
  const initialIsAdmin = JSON.parse(localStorage.getItem("isAdmin"));

  const initialUserId = localStorage.getItem("userId");

  const [userId, setUserId] = useState(initialUserId);

  const [isAdmin, setIsAdmin] = useState(initialIsAdmin);

  if (userId != null) {
    if (userId != "null") CURRENT_USER_TYPE = USER_TYPES.USER;
  }
  if (isAdmin) {
    CURRENT_USER_TYPE = USER_TYPES.ADMIN;
  }
  if ((userId === "null" || userId == null) && !isAdmin) {
    CURRENT_USER_TYPE = USER_TYPES.PUBLIC;
  }

  console.log(CURRENT_USER_TYPE);

  function handleAdminLogin() {
    setIsAdmin(true);

    localStorage.setItem("isAdmin", true);

    setUserId(null);

    localStorage.setItem("userId", null);

    CURRENT_USER_TYPE = USER_TYPES.ADMIN;
  }

  function handleAdminLogout() {
    localStorage.setItem("isAdmin", false);

    setIsAdmin(false);

    CURRENT_USER_TYPE = USER_TYPES.PUBLIC;
  }

  function handleLogout() {
    setUserId(null);

    localStorage.setItem("userId", null);

    CURRENT_USER_TYPE = USER_TYPES.PUBLIC;
  }

  function handleLogin(id) {
    setUserId(id);

    localStorage.setItem("userId", id);

    setIsAdmin(false);

    CURRENT_USER_TYPE = USER_TYPES.USER;

    console.log(CURRENT_USER_TYPE);
  }
  return (
    <div className="App">
      <UserContext.Provider
        value={{
          userId,
          handleLogout,
          handleLogin,
          isAdmin,
          handleAdminLogin,
          handleAdminLogout,
        }}
      >
        <Routes>
          <Route
            exact
            path="/admin"
            element={
              <AdminElement>
                <AdminNav />
                <AdminHome />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/admin-login"
            element={
              <PublicElement>
                <AdminNav />
                <AdminLoginForm />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/admin/courses"
            element={
              <AdminElement>
                <AdminNav />
                <AdminCourses />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/admin/enrollments"
            element={
              <AdminElement>
                <AdminNav />
                <AdminEnrollments />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/admin/mentors"
            element={
              <AdminElement>
                <AdminNav />
                <AdminMentors />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/admin/branches"
            element={
              <AdminElement>
                <AdminNav />
                <AdminBranches />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/admin/batches"
            element={
              <AdminElement>
                <AdminNav />
                <AdminBatches />
              </AdminElement>
            }
          />

          <Route
            exact
            path="/"
            element={
              <PublicElement>
                <Header />
                <Home />
                <Footer />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/services"
            element={
              <PublicElement>
                <Header />
                <Services />
                <Footer />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/about"
            element={
              <PublicElement>
                <Header />
                <About />
                <Footer />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/courses"
            element={
              <PublicElement>
                <Header />
                <Course />
                <Footer />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/login"
            element={
              <PublicElement>
                <Header />
                <LoginForm />
                <Footer />
              </PublicElement>
            }
          />

          <Route
            exact
            path="/dashboard"
            element={
              <UserElement>
                <Header />
                <Dashboard />
                <Footer />
              </UserElement>
            }
          />

          <Route path="*" element={<PageNotFound />} />
        </Routes>
        <ToastContainer />
      </UserContext.Provider>
    </div>
  );
}

export default App;

function PublicElement({ children }) {
  return <>{children}</>;
}

function UserElement({ children }) {
  console.log(CURRENT_USER_TYPE);

  if (CURRENT_USER_TYPE === USER_TYPES.USER) return <>{children}</>;
  else return <Navigate to="/login" />;
}

function AdminElement({ children }) {
  console.log(CURRENT_USER_TYPE, "current user type");
  if (CURRENT_USER_TYPE === USER_TYPES.ADMIN) return <>{children}</>;
  else return <Navigate to="/admin-login" />;
}
