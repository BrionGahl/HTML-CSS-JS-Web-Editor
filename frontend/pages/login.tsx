import { Button, Stack, TextField } from "@mui/material";
import { NextPage } from "next";
import { useState } from "react";
import Header from "../components/Header";
import Register from "../components/Register";
import Signin from "../components/Signin";

import styles from "../styles/Login.module.css"

const Login: NextPage = () => {
    const [toggleLogin, setToggleLogin] = useState(true)


    const toggle = (event: any) => {
        setToggleLogin(false)
    }

    return (
        <div>
            <Header />
            <div className={styles.center}>
                {toggleLogin ? <Signin onClick={setToggleLogin}/> : <Register onClick={setToggleLogin}/>}
            </div>
        </div>
    )
}

export default Login