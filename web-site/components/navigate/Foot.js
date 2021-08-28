import React from "react";
import styles from '@css/navigate/foot.module.scss';

export default class Footer extends React.Component{

    render(){
        return(
            <footer className={styles.foot}>
              <div className={styles.record}>
                <div>ALFRED © alfredhua@aliyun.com</div>
                <span>CopyRight©2021<a href="http://www.beian.miit.gov.cn/" target="_blank" rel="noopener noreferrer">京ICP备19044364号</a></span>
               </div>
            </footer>
        )
    }
}