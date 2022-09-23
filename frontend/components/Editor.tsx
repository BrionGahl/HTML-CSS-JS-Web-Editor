import styles from '../styles/Editor.module.css'

export const JsEditor = (props: OtherProps) => {
    return <Editor title={"JS"} value={props.value} mode={styles.js} onChange={props.onChange}/>
}

export const HtmlEditor = (props: OtherProps) => {
    return <Editor title={"HTML"} value={props.value} mode={styles.html} onChange={props.onChange}/>
}

export const CssEditor = (props: OtherProps) => {
    return <Editor title={"CSS"} value={props.value} mode={styles.css} onChange={props.onChange}/>
}

type OtherProps = {
    value: any,
    onChange: any
}

type EditorProps = {
    title: string,
    mode: string,
    value: any,
    onChange: any
}

const Editor = (props: EditorProps) => {

    const handleChange = (event: any) => {
        props.onChange(event.target.value)
    }

    return (
        <div className={styles.editorContainer}>
            <div className={styles.editorTitle}>
                {props.title}
                <div className={styles.editorCode}>
                    <textarea value={props.value} onChange={handleChange} rows={10} className={props.mode}>
                    </textarea>
                </div>
            </div>
        </div>
    )
}
