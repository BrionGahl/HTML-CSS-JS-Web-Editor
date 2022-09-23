import type { NextPage } from 'next'
import { useEffect, useState } from 'react'

import { CssEditor, HtmlEditor, JsEditor } from '../components/Editor'
import EditorContainer from '../components/EditorContainer'
import Header from '../components/Header'
import ResultFrame from '../components/ResultFrame'

const Code: NextPage = () => {
  const [htmlValue, setHtmlValue] = useState("");
  const [cssValue, setCssValue] = useState("");
  const [jsValue, setJsValue] = useState("");
  const [outputValue, setOutputValue] = useState("");

  useEffect(() => {
    const output = `<html>
<style>
${cssValue}
</style>
<body>
${htmlValue}
<script type="text/javascript">
${jsValue}
</script>
</body>
</html>`
    setOutputValue(output)  
  }, [htmlValue, cssValue, jsValue])

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