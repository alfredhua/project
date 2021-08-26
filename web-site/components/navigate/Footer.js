import React from 'react';
import styles from '@css/navigate/footer.module.css';

export default class Footer extends React.Component{
  render(){
    return(
      <div>
        {/* <div className={styles.hei}></div> */}
        <footer className={styles.navigate_foot}>
          <div className='container'>
              {/* <div className={styles.wx_pub}>
                  <img alt="公众号" style={{width:100}} src={require('@blog/common/foot/img/code.jpg')}/>
                  <span>关注公众号</span>
              </div> */}
              <div className={styles.record}>
                  <div>alfredhua@aliyun.com</div>
                  <span> <a href="http://www.beian.miit.gov.cn/" target="_blank" rel="noopener noreferrer">京ICP备19044364号</a></span>
              </div>
          </div>
        </footer>
    </div>
    )
  }
}