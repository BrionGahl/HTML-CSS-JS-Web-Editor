import type { NextPage } from 'next'
import { useEffect, useState } from 'react'
import Header from '../components/Header'
import { getCookie, getCookies, hasCookie } from 'cookies-next'
import Card from '@mui/material/Card'
import { Button, CardActionArea, CardActions, CardContent, CardMedia, Typography } from '@mui/material'
import axios from 'axios'
import { API } from '../utils/constants'
import Router from 'next/router';



type Props = {
  user?: string,
  tkn?: string
}

const Home: NextPage<Props> = ({user, tkn}) => {
  const [username, setUsername] = useState(user)
  const [token, setToken] = useState(tkn)

  const [data, setData] = useState([])

  useEffect(() => {
    if (!username)
      return
    
    let config = {
      headers: {Authorization: `Bearer ${token}`}
    }

    axios.get(`${API}/workspace/user/admin`, config).then(response => {
      setData(response.data)
    })
  }, [])

  const createOnClick = () => {
    let config = {
      headers: {Authorization: `Bearer ${token}`}
    }
    let data = {
      "html": "",
      "css": "",
      "js": "",
      "username": username
    }

    axios.post(`${API}/workspace/save`, data, config).then(resp => {
      Router.push(`/workspace/${resp.data.id}`)
    })
  }

  const listWorkspaces = data.map((workspace, index) =>
    <Card key={index} sx={{maxWidth: 150}} style={{borderStyle: "solid", borderColor: "#B0B0B0", borderWidth:"1px"}}>
      <CardActionArea>
        <CardMedia component="img" height="100" image="/htmlcssjs.png" alt="html css js"/>
        <CardContent>
          <Typography gutterBottom component="div">
            Workspace {index}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" variant="contained" color="primary" onClick={() => {Router.push(`/workspace/${data[index]["id"]}`)}}>
          Goto
        </Button>
        <Button size="small" variant="outlined" color="primary" onClick={() => {
          let config = {
            headers: {Authorization: `Bearer ${token}`}
          }
          axios.post(`${API}/workspace/${data[index]["id"]}`, {}, config)
          Router.reload()
        }}>
          Delete
        </Button>
      </CardActions>
    </Card> 
  )

  return (
    <div style={{fontFamily: "tahoma", fontWeight: "bold"}}>
      <h1 style={{textAlign: "center"}}>Editor Application</h1>
      <Header />
      <h3>{username ? `Welcome ${username}!` : `Please Login.`}</h3>
      <Button variant="contained" onClick={createOnClick} style={{marginBottom: "1%"}}>Create</Button>
      <div className="horizontal-container">
        {listWorkspaces}
      </div>
    </div>
  )
}

Home.getInitialProps = ({req, res}) => {
  let data: any
  if (req) {
    data = getCookies({req, res})
  } else {
    data = getCookies()
  }
  return({
    user: data.user,
    tkn: data.token
  })
}


export default Home
