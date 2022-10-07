import type { NextPage } from 'next'
import { useEffect, useState } from 'react'

import { CssEditor, HtmlEditor, JsEditor } from '../components/Editor'
import EditorContainer from '../components/EditorContainer'
import Header from '../components/Header'
import ResultFrame from '../components/ResultFrame'
import { useDebounce } from '../utils/debounce'

const Code: NextPage = () => {
  const [htmlValue, setHtmlValue] = useState("")
  const [cssValue, setCssValue] = useState("")
  const [jsValue, setJsValue] = useState("")
  const [outputValue, setOutputValue] = useState("")

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
  }, [debouncedHtml, debouncedCss, debouncedJs])

  console.log(htmlValue)
  console.log(outputValue)

  return (
    <div>
        <Header />
        <h1>Editor</h1>
        <EditorContainer>
          <HtmlEditor value={htmlValue} onChange={setHtmlValue}></HtmlEditor>
          <CssEditor value={cssValue} onChange={setCssValue}></CssEditor>
          <JsEditor value={jsValue} onChange={setJsValue}></JsEditor>
        </EditorContainer>
        <ResultFrame srcDoc={outputValue}></ResultFrame>
    </div>
  )
}

export default Code