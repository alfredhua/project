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
    default_icon:'baidu',
    value:''
  }

  componentDidMount(){
    var search=localStorage.getItem('search');
    if(search && icons[search]){
      this.setState({
        default_icon:search
      })
    }
    document.addEventListener('click', this.hide_search);
  }

  hide_search = () => {
    this.setState({
      icon_down: false
    })
}

  pre_changed=(e)=>{
    const {icon_down} =this.state;
    this.setState({icon_down:!icon_down})
    e.nativeEvent.stopImmediatePropagation();
  }

  change=(item)=>{
    localStorage.setItem('search',item);
    this.setState({default_icon:item})
  }
  
  search=(e)=>{
    const {default_icon} =this.state;
    var value=e.target.value;
    if(e.nativeEvent.keyCode === 13){
      this.setState({value:''})
      if(default_icon==='baidu'){
        window.open('https://baidu.com/s?wd='+value,'_blank');
        return;
      }
      if( default_icon === 'google' ){
         window.open('https://www.google.com.hk/search?q='+value,'_blank');
        return;
      }
      if( default_icon === 'stack' ){
        window.open('https://stackoverflow.com/search?q='+value,'_blank');
        return;
      }
      if( default_icon === 'github' ){
        window.open('https://github.com/search?q='+value,'_blank');
        return;
      }
      if( default_icon === 'bing' ){
        window.open('https://www.bing.com/search?q='+value,'_blank');
        return;
      }
    }
  }

  render(){

    const { icon_down,default_icon,display } =this.state;

    return (
            <div className={styles.container}>
                {/* logo */}
                <div className={styles.logo}>
                  <a href="/"> ALFRED</a>
                </div>
                {/* 导航 */}
                <div className={styles.nav}>
                    <div className={styles.dropdown}>
                      <span><a href="/">首页</a></span>
                    </div>

                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/navigate/manual">手册</a></span>
                    </div>

                    <div className={styles.dropdown}>
                      <span className={styles.menu_name}><a href="/navigate/book">书籍</a></span>
                    </div>
                    <div className={classNames(styles.dropdown,styles.settings)}>
                        <span className={styles.menu_name}>博文推荐</span>
                        <span className={styles.icon_more}></span>
                        <div className={styles.dropdown_content}>
                          <p><a href="/blog/article/all" target="_blank">玩玩技术</a></p>
                          <p><a href="/blog/other/produce" target="_blank">说说产品</a></p>
                          <p><a href="/blog/other/tool" target="_blank">搞搞工具</a></p>
                          <p><a href="/blog/other/ms" target="_blank">谈谈面试</a></p>
                        </div>
                    </div> 
                    <div className={classNames(styles.dropdown,styles.settings)}>
                      <span className={styles.menu_name}><a href="/blog/other/ms">网站建设</a></span>
                      <span className={styles.icon_more}></span>
                        <div className={styles.dropdown_content}>
                          <p><a href="/website/home" target="_blank">首页</a></p>
                          <p><a href="/website/produce/list" target="_blank">产品服务</a></p>
                          <p><a href="/website/news" target="_blank">新闻动态</a></p>
                          <p><a href="/website/notice/list" target="_blank">网站公告</a></p>
                        </div>
                    </div> 
                </div>
                {/* 搜索 */}
                <div className={styles.search}>
                  <div className={styles.input}>

                    <div className={styles.images} onClick={(e)=>{this.pre_changed(e)}}>
                      <img src={icons[this.state.default_icon]}></img>
                      <div className={styles.icons}>{icon_down?<CaretUpOutlined/>:<CaretDownOutlined />}</div>
                    </div>

                   <div className={icon_down?classNames(styles.dropdown_content,styles.display):classNames(styles.dropdown_content)}>
                     {search_infos.map(item=>{
                       return <div className={styles.click_icons} key={item} onClick={()=>{this.change(item)}}> 
                         <img src={icons[item]}></img>
                         <div className={styles.check}>
                           {item==default_icon? <CheckCircleTwoTone twoToneColor="#1890ff" />: null}
                         </div>
                       </div>
                     })}
                    </div> 
                      <input type='text' value={this.state.value} onKeyPress={e=>{this.search(e)}} onChange={(e)=>{this.setState({value:e.target.value})}} ></input>
                  </div>
                </div>
             </div>
    )
  }
}