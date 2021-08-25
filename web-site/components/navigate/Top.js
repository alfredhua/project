import React from 'react';
import classNames  from 'classnames';
import styles from '@css/navigate/top.module.scss';

var icons={
  "google":"google"
}


export default class Top extends React.Component{

  get_search_icon = ()=>{
    return <div></div>
  }

  render(){
    return (
            <div className={styles.container}>
                <div className={styles.logo}>
                  <a href="/"> 导航网</a>
                </div>
                <div className={styles.nav}>
                    <div className={styles.dropdown}>
                      <span><a href="/">首页</a></span>
                    </div>

                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/navigate/mu">手册</a></span>
                    </div>

                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/">书籍</a></span>
                    </div>
                    <div className={classNames(styles.dropdown,styles.settings)}>
                        <span className={styles.menu_name}>精美博文</span>
                        <span className={styles.icon_more}></span>
                        <div className={styles.dropdown_content}>
                          <p>git</p>
                          <p>菜鸟教程</p>
                        </div>
                    </div> 
                    <div className={classNames(styles.dropdown,styles.settings)}>
                      <span className={styles.menu_name}><a href="/blog/other/ms">网站建设</a></span>
                    </div> 
                </div>
                <div className={styles.search}>
                  <div className={styles.input}>
                    {this.get_search_icon()}
                    <input type='text'></input>
                  </div>
                </div>
             </div>
    )
  }
}