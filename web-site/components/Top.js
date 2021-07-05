import React from 'react';
import styles from '@css/components/top.module.css';


const type={
  "PRODUCE":"产品服务",
  "NEWS":"新闻动态",
  "ABOUT_US":"关于我们",
  "NOTICE":"网站公告",
}


const url={
  "HOME":"/website/home",
  "PRODUCE":"/website/produce/list",
  "NEWS":"/website/news",
  "ABOUT_US":"/website/about-us",
  "NOTICE":"/website/notice/list",
}

export default class Top extends React.Component{
  
  state={
    color:""
  }

  componentDidMount(){
    this.set_color();
  }

  handleScroll = (event) => {
    const topH = (event.srcElement ? event.srcElement.documentElement.scrollTop : false) 
        || window.pageYOffset
        || (event.srcElement ? event.srcElement.body.scrollTop : 0);
    let transparentNum=0;
    if(this.props.active==='HOME'){
      transparentNum= (1-(500 - topH)/500).toFixed(2);
    }
    this.setState({color:"rgba(51,51,51,"+transparentNum+")"});
  }

   set_color(){
    if(this.props.active==='HOME'){
      this.setState({color:"rgba(51,51,51,0)"});
      window.addEventListener('scroll', this.handleScroll);
    }else{
      this.setState({color:"rgba(51,51,51,10)"});
    }
  }

  render(){
    const { setting,active }=this.props;  
    const {color} =this.state;
    return (
        <header style={{backgroundColor:color}} className={styles.header}>
             <div className={styles.container}>
                <div className={styles.logo_container}>
                   <a href="/blog/home"> 码农阿华</a> 
                </div>
                <div  className="menu">
                    <ul>
                      <li><a className={active==='HOME'?styles.active:null} href="/website/home">首页</a></li>
                      {setting&&setting.map((item)=>{
                         return  <li key={item.type}><a href={url[item.type]} >{type[item.type]}</a></li>
                      })}
                    </ul> 
                </div>
             </div>
        </header>
    )
  }
};
