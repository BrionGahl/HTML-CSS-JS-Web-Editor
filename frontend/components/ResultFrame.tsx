import styles from '../styles/ResultFrame.module.css'

type ResultFrameProps = {
    srcDoc: any
}

const ResultFrame = (props: ResultFrameProps) => {
    return (
        <div>
            <h4>Preview</h4>
            <iframe className={styles.resultFrame} srcDoc={props.srcDoc}></iframe>
        </div>
    )
}

export default ResultFrame