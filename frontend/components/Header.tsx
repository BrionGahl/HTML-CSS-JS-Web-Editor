import Link from "next/link";
import styles from '../styles/Header.module.css'


const Header = () => {
    return (
        <div className={styles.navbar}>
            <Link href={`/`}><a className={styles.navlinks} >Home</a></Link>
            <Link href={`/workspace`}><a className={styles.navlinks}>Workspace</a></Link>
            <Link href={`/login`}><a className={styles.navlinks}>Login</a></Link>
        </div>
    )
}

export default Header;