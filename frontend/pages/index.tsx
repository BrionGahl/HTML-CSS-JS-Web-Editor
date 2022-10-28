import type { NextPage } from 'next'
import { useState } from 'react'
import Header from '../components/Header'
import { parseCookies } from '../utils/cookie'
import { getCookie, getCookies, hasCookie } from 'cookies-next'

type Props = {
  user?: string,
  tkn?: string
}

const Home: NextPage<Props> = ({user, tkn}) => {
  const [username, setUsername] = useState(user)
  const [token, setToken] = useState(tkn)

  return (
    <div>
        <Header />
        <h3>{username ? `Welcome ${username}!` : `Please Login.`}</h3>
        <h1>CS435 Project</h1>
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
