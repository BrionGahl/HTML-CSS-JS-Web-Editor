import Link from "next/link";
import styles from '../styles/Header.module.css'


const Header = () => {
    return (
        <div className={styles.navbar}>
            <Link href={`/`}><a className={styles.navlinks} >Home</a></Link>
            <Link href={`/code_editor`}><a className={styles.navlinks}>Code</a></Link>
        </div>
    )
}

export default Header;