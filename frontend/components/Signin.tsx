import { Box, Button, Stack, TextField } from '@mui/material';
import axios from 'axios';
import Router from 'next/router';
import { useState } from 'react';
import { API } from '../utils/constants'

type LoginProps = {
    onClick: any
}

const Signin = (props: LoginProps) => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const [isBadPassword, setIsBadPassword] = useState(false)

    const toggle = (event: any) => {
        props.onClick(false)
    }

    const onClick = (event: any) => {
        axios.post(`${API}/auth/login`, {"username": username, "password": password}).then(response => {
            console.log("store user data")
            Router.push("/")
        }).catch(function (error) {
            console.log(error.response.status)
            setIsBadPassword(true)
        })
    }

    return (
        <div>
            <Box component="form" noValidate>
                <Stack spacing={4} direction="column">
                    <TextField required id="username" label="Username" variant="standard" value={username} onChange={e => {setUsername(e.target.value)}}/>
                    <TextField required error={isBadPassword} helperText={isBadPassword ? "Incorrect Password": ""} id="password" label="Password" type="password" variant="standard" value={password} onChange={e => {setPassword(e.target.value)}}/>
                    <Stack spacing={2} direction="row">
                        <Button variant="contained" disabled={!username || !password} onClick={onClick}>Login</Button>
                        <Button variant="text" onClick={toggle}>Create Account</Button>
                    </Stack>
                </Stack>
            </Box>
        </div>
    )
}

export default Signin;