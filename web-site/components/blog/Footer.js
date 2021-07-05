import React from 'react';
import styles from '@css/blog/footer.module.css';

export default class Footer extends React.Component{
  render(){
    return(
      <div>
       {/* <div style={{height: 130}}>
        </div> */}
        <div className={styles.hei}></div>
      <footer className={styles.footer}>
        <div className='container'>
             {/* <div className={styles.wx_pub}>
                <img alt="公众号" style={{width:100}} src={require('@blog/common/foot/img/code.jpg')}/>
                <span>关注公众号</span>
             </div> */}
             <div className={styles.record}>
                <div>码农阿华的博客 © alfredhua@aliyun.com</div>
                <span>CopyRight©2019 版权所有 码农阿华 <a href="http://www.beian.miit.gov.cn/" target="_blank" rel="noopener noreferrer">京ICP备19044364号</a></span>
             </div>
        </div>
    </footer>
    </div>
    )
  }
}