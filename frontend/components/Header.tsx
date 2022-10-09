import Link from "next/link";
import styles from '../styles/Header.module.css'
import { deleteCookie } from 'cookies-next'
import Router from 'next/router'


const Header = () => {
    return (
        <div className={styles.navbar}>
            <Link href={`/`}><a className={styles.navlinks} >Home</a></Link>
            <Link href={`/workspace`}><a className={styles.navlinks}>Workspace</a></Link>
            <Link href={`/login`}><a className={styles.navlinks}>Login</a></Link>
            <Link href={`/`}><a className={styles.navlinks} onClick={e => {deleteCookie("user", {"path": "/"}); Router.reload();}}>Logout</a></Link>
        </div>
    )
}

export default Header;