import { Box, Button, Stack, TextField } from '@mui/material'
import axios from 'axios'
import { useState } from 'react'
import Router from 'next/router'
import { setCookie } from 'cookies-next'


import { API } from '../utils/constants';

type RegisterProps = {
    onClick: any
}

const Register = (props: RegisterProps) => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [passwordRetype, setPasswordRetype] = useState("")

    const [isBadUsername, setIsBadUsername] = useState(false)

    const toggle = (event: any) => {
        props.onClick(true)
    }

    function passwordsMatch(){
        return password == passwordRetype;
    }

    const onClick = (event: any) => {
        axios.post(`${API}/auth/register`, {"username": username, "password": password, "role": "user"}).then(registerResponse => {
            console.log(registerResponse.status)
                axios.post(`${API}/auth/login`, {"username": username, "password": password}).then(loginResponse => {
                    if (loginResponse.status == 200) {
                        setCookie("user", username, {
                            path: "/",
                            maxAge: 3600,
                            sameSite: true,
                            secure: true,
                            httpOnly: false //want this to be true...
                        })
                        setCookie("token", loginResponse.data.token, {
                            path: "/",
                            maxAge: 3600,
                            sameSite: true,
                            secure: true,
                            httpOnly: false //want this to be true...
                        })
                        Router.push("/")
                    }
                }).catch(function (error) {
                    console.log(error.response.status)
                    //should be nothing
                })
        }).catch(function (error) {
            console.log(error.response.status)
            setIsBadUsername(true)
        })
    }

    return (
        <div>
            <Box component="form" noValidate>
                <Stack spacing={4} direction="column">
                    <TextField error={isBadUsername} helperText={isBadUsername? "Username already exists" : ""} required id="username" label="Username" variant="standard" value={username} onChange={e => {setUsername(e.target.value)}}/>
                    <TextField required id="password" label="Password" type="password" variant="standard" value={password} onChange={e => {setPassword(e.target.value)}}/>
                    <TextField error={!passwordsMatch()} helperText={!!passwordRetype && !passwordsMatch()? "Passwords do not match" : ""} required id="passwordRetype" label="Retype Password" variant="standard" value={passwordRetype} onChange={e => {setPasswordRetype(e.target.value)}}/>
                    <Stack spacing={2} direction="row">
                        <Button variant="contained" disabled={!username || !password || !passwordsMatch()} onClick={onClick}>Create Account</Button>
                        <Button variant="text" onClick={toggle}>Sign in instead</Button>
                    </Stack>
                </Stack>
            </Box>
        </div>
    )
}

export default Register;