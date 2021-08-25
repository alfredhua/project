import React from 'react';
import classNames  from 'classnames';
import styles from '@css/navigate/top.module.scss';
import { CaretDownOutlined,CaretUpOutlined,CheckCircleTwoTone } from '@ant-design/icons';
var search_infos=["baidu","google","stack","github","bing"]

var icons={
  "baidu":"/static/images/navigate/baidu.png",
  "google":"/static/images/navigate/google.png",
  "stack":"/static/images/navigate/stack.png",
  "github":"/static/images/navigate/github.png",
  "bing":"/static/images/navigate/bing.png"
}

export default class Top extends React.Component{

  state={
    icon_down:false,
    default_icon:'baidu'
  }

  componentDidMount(){
    var search=localStorage.getItem('search');
    if(search && icons[search]){
      this.setState({
        default_icon:search
      })
    }
  }
  get_search_icon = ()=>{
    // console.log(this.state)
    return <img src={icons[this.state.default_icon]}></img>
  }

  change_search=()=>{
    const {icon_down} =this.state;
    this.setState({icon_down:!icon_down})
  }

  render(){

    const { icon_down,default_icon } =this.state;

    return (
            <div className={styles.container}>
                {/* logo */}
                <div className={styles.logo}>
                  <a href="/"> 导航网</a>
                </div>
                {/* 导航 */}
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
                {/* 搜索 */}
                <div className={styles.search}>
                  <div className={styles.input}>

                    <div className={styles.images} onClick={()=>{this.change_search()}}>
                      {this.get_search_icon()}
                      <div className={styles.icons}>{icon_down?<CaretUpOutlined/>:<CaretDownOutlined />}</div>
                    </div>

                   <div className={styles.dropdown_content}>
                     {search_infos.map(item=>{
                       return <div className={styles.click_icons} key={item}> 
                         <img src={icons[item]}></img>
                         <div className={styles.check}>
                           {item==default_icon? <CheckCircleTwoTone twoToneColor="#1890ff" />: null}
                           
                         </div>
                       </div>
                     })}
                    </div> 

                    <input type='text'></input>

                  </div>
                </div>
             </div>
    )
  }
}