import axios from "axios";
 
export default axios.create({
  baseURL: "http://localhost:8081/api",
  // use this base URL for production
  //baseURL: window.location.protocol + "//" + window.location.host + "/api",
  headers: {
    "Content-type": "application/json",
  }
});