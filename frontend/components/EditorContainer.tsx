import { ReactNode } from "react"

import styles from '../styles/EditorContainer.module.css'

type EditorContainerProps = {
    children: ReactNode
}

const EditorContainer = (props: EditorContainerProps) => {
    return (
        <div className={styles.pane}>
            {props.children}
        </div>
    )
}

export default EditorContainer