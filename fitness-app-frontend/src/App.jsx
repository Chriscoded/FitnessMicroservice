import { Button } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { createBrowserRouter as Router, Navigate, Route, Routes, useLocation } from "react-router";
import { RouterProvider } from "react-router/dom";
import { setCredentials } from "./store/authSlice";

function Home() {
  const { token, tokenData, logIn } = useContext(AuthContext);

  return (
    <div>
      <h1>Home Page</h1>

      {!token ? (
        <Button
          variant="contained"
          onClick={() => logIn()}
        >
          LOGIN
        </Button>
      ) : (
        <div>
          <pre>{JSON.stringify(tokenData, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}


const router = Router([
  {
    path: "/",
    element: <Home/>
  },
]);

function App() {
  const { token, tokenData, isAuthenticated } = useContext(AuthContext);
  const dispatch = useDispatch();
  const [authReady, setAuthReady] = useState(false);

  useEffect(() => {
    if(token){
      dispatch(setCredentials({token, user: tokenData}));
      setAuthReady(true)
    }
  }, [token, tokenData, dispatch])

  return <RouterProvider router={router} />;
}

export default App;

