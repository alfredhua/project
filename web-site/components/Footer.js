import React from 'react';
import styles from '@css/components/footer.module.css';

export default class Footer extends React.Component{
  render(){
    return(
      <footer className={styles.footer}>
        <ul>
            <li><a href="/">联系我们</a></li>
            <li><a href="/">关于我们</a></li>
            <li><a href="/">人才招聘</a></li>
            <li><a href="/">友情链接</a></li>
            <li><a href="/">公司地址</a></li>
            <li><a href="/">关注我们</a></li>
        </ul>
        <p>CopyRight©2019 版权所有 ALFRED 京ICP备19044364号</p>
    </footer>
    )
  }
}