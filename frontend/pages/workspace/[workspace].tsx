import type { NextPage } from 'next'
import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'

import { CssEditor, HtmlEditor, JsEditor } from '../../components/Editor'
import Header from '../../components/Header'
import ResultFrame from '../../components/ResultFrame'
import { useDebounce } from '../../utils/debounce'

import styles from '../../styles/EditorContainer.module.css'
import { getCookies } from 'cookies-next'

import { API } from '../../utils/constants';
import axios from 'axios'
import { TextField } from '@mui/material'

type Props = {
  user: string,
  tkn: string
}

const Workspace: NextPage<Props> = ({user, tkn}) => {
  const router = useRouter()
  const {workspace} = router.query

  const [username, setUsername] = useState(user)
  const [token, setToken] = useState(tkn)
  
  const [titleValue, setTitleValue] = useState("")

  const [htmlValue, setHtmlValue] = useState("")
  const [cssValue, setCssValue] = useState("")
  const [jsValue, setJsValue] = useState("")
  const [outputValue, setOutputValue] = useState("")

  var title = ""

  useEffect(() => { 
    let config = {
      headers: {Authorization: `Bearer ${token}`}
    }

    axios.get(`${API}/workspace/${workspace}`, config).then(response => {
      setHtmlValue(response.data.html)
      setCssValue(response.data.css)
      setJsValue(response.data.js)
      setTitleValue(response.data.title)
    })
  }, [])

  const debouncedTitle = useDebounce(titleValue, 1000)
  const debouncedHtml = useDebounce(htmlValue, 1000)
  const debouncedCss = useDebounce(cssValue, 1000)
  const debouncedJs = useDebounce(jsValue, 1000)


  useEffect(() => {
    const output = `<html>
<style>
${debouncedCss}
</style>
<body>
${debouncedHtml}
<script type="text/javascript">
${debouncedJs}
</script>
</body>
</html>`
    setOutputValue(output)

    if (debouncedHtml || debouncedCss || debouncedJs || debouncedTitle) {
      let data = {
        "id": workspace,
        "title": titleValue,
        "html": debouncedHtml,
        "css": debouncedCss,
        "js": debouncedJs,
        "username": username
      }
      let config = {
        headers: {Authorization: `Bearer ${token}`}
      }  
      axios.post(`${API}/workspace/save`, data, config) 
    }
    
  }, [debouncedHtml, debouncedCss, debouncedJs, debouncedTitle])

  console.log(outputValue)

  return (
    <div style={{fontFamily: "tahoma", fontWeight: "bold"}}>
        <Header />
        <TextField id="standard-basic" variant="standard" value={titleValue} onChange={(event: any) => {setTitleValue(event.target.value)}} />
        <div className={styles.pane}>
          <HtmlEditor value={htmlValue} onChange={setHtmlValue}></HtmlEditor>
          <CssEditor value={cssValue} onChange={setCssValue}></CssEditor>
          <JsEditor value={jsValue} onChange={setJsValue}></JsEditor>
        </div>
        <ResultFrame srcDoc={outputValue}></ResultFrame>
    </div>
  )
}

Workspace.getInitialProps = ({req, res}) => {
  let cookieData: any
  if (req) {
    cookieData = getCookies({req, res})
  } else {
    cookieData = getCookies()
  }

  return({
    user: cookieData.user,
    tkn: cookieData.token
  })
}

export default Workspace