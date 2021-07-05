import React from 'react';
import styles from '@css/blog/top.module.css';

export default class Top extends React.Component{
  render(){
    return (
        <div className={styles.header}>
            <div className={styles.container}>
                <div className={styles.logo}>
                  <a href="/blog/home">码农阿华  |  <span>玩玩技术，说说产品</span></a>
                </div>
                <div className={styles.nav}>
                    <div className={styles.dropdown}>
                      <span><a href="/blog/home">网站首页</a></span>
                    </div>
                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/blog/article/all">玩玩技术</a></span>
                    </div>
                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/blog/other/produce">说说产品</a></span>
                    </div>
                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/blog/other/tool">搞搞工具</a></span>
                    </div>
                    {/* <div className={styles.dropdown}>
                      <span className={styles.menu_name}>搞搞工具</span><span className={styles.icon_more}></span>
                        <div className={styles.dropdown_content}>
                          <p>git</p>
                          <p>菜鸟教程</p>
                        </div>
                    </div> */}
                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/blog/other/ms">谈谈面试</a></span>
                    </div>

                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/blog/about-us">关于我</a></span>
                    </div>
                </div>
             </div>
        </div>
    )
  }
}