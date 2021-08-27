import React from 'react';
import Top from '@components/blog/Top';
import Footer from '@components/blog/Footer';
import HeadMeta from '@components/blog/HeadMeta';
import { Card, List,Icon } from 'antd';
import Banner from '@components/Banner';
import styles from '@css/blog/home.module.css';
import classNames from 'classnames';
import Charts from '@components/blog/Charts';
import Type from '@components/blog/Type';
import Info from '@components/blog/Info';
import { list_article_home,list_banner,click_charts,list_all_active_type } from '@api/blog';

class home extends React.Component{

  get_context(item){
    let introduce;
    if(item&&item.introduce&&item.introduce.length>200){
      introduce=item.introduce.substring(0,200);
    }else{
      introduce=item.introduce;
    }
    return <div dangerouslySetInnerHTML={{ __html:introduce}}></div>;
  }

  render(){
    const { article_list,banners,click_list,active_type_list} =this.props;
    return(
      <div >
        <HeadMeta/>
        <Top />
        <div className='contains'>
            <div className='container'>
              <div className={styles.banner}>
                  <Banner banners={banners}/>    {/* 2560 *1080  ---> 699 × 295 */}
                 <div className={styles.card}>   {/* 1920x1080 ----> 16:9 */}
                      <a href="/blog/article/all"><div className={styles.imgbox}>
                          <span>技术专区</span>
                          <img alt="ALFRED博客" src={'/static/images/blog/card-2.png'}/>
                      </div></a>
                  </div>
                  <div className={classNames(styles.card,styles.card_info)}>
                      <a href="/blog/other/tool"><div className={styles.imgbox}>
                          <span>工具专区</span>
                          <img alt="ALFRED博客" src={'/static/images/blog/card-1.png'}/>
                      </div></a>
                  </div>
                  <div className={classNames(styles.card,styles.card_info)}>
                       <a href="/blog/other/ms"><div className={styles.imgbox}>
                          <span>面试专区</span>
                         <img alt="ALFRED博客"src={'/static/images/blog/card-3.png'}/>
                      </div></a>
                  </div>
                  <div style={{clear:'both'}}></div>
                  {/* <Card className={styles.article}>
                      广告位
                  </Card>  */}
                  <Card className={styles.article} tabList={[{key:'1',tab: '博文精选'}]} >
                  <List
                      dataSource={article_list}
                      renderItem={item => (
                          <List.Item>
                              <div>
                                  <div style={{width:220,float:'left'}} >
                                        <img  width={204.8} alt="图片" src={item.pic_url}/>
                                  </div>
                                    <List.Item.Meta title={<a rel="noopener noreferrer" target="_blank" href={'/blog/detail/'+item.id}>{item.title}</a>}
                                      description={this.get_context(item)}/>
                              </div>
                          </List.Item>
                        )}
                      />
                  </Card>
             </div>
              <div className={styles.right}>
                <Card className={styles.introduce}>
                    <p className={styles.aboutme}><span>关于我</span></p>
                    <p><span></span>ALFRED</p>
                    <p><span>邮箱：</span>alfredhua@aliyun.com</p>
                    <p><span>职业：</span>全栈工程师</p>
                    <p><span>现居：</span>北京</p>
                    <div className={styles.icon}>
                    <a href="/blog/home" target="_blank">
                        <Icon  style={{fontSize:32}} type="home" />
                    </a>
                     <a  href="http://wpa.qq.com/msgrd?v=3&uin=1070242697&site=qq&menu=yes" target="_blank" rel="noopener noreferrer">
                        <Icon  style={{fontSize:32}} type="qq" />
                      </a>
                      <a  href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=alfredhua@aliyun.com" target="_blank" rel="noopener noreferrer" title="我的邮箱">
                        <Icon  style={{fontSize:32}} type="mail" />
                      </a> 
                      <a href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=alfredhua@aliyun.com" target="_blank" rel="noopener noreferrer" title="我的邮箱">
                         <Icon  style={{fontSize:32}} type="message" />
                      </a>  
                   </div>
                </Card>

                <Card tabList={[{key:'1',tab: '网站建设'}]}  style={{marginTop:20}}>
                   <a  href="/website/home" target="_blank">网站建设demo</a>
                </Card>
              
             {/* 点击排行榜 */}
              <Charts style={{marginTop:20}} click_list={click_list}/> 
              {/* 标签 */}
              <Type active_type_list={active_type_list}  tabList={[{key:'1',tab: '我的标签'}]} style={{marginTop:20}}/>

              <Info style={{marginTop:20}}/>
              </div>
            </div>           
        </div>
        <div style={{clear:'both',height:20}}></div>
        <Footer/>

      </div>
    )
  }
}

home.getInitialProps = async (res) => {
    return Promise.all([
            list_article_home(),list_banner('BLOG'),click_charts(),list_all_active_type()
           ]).then((result) => {
         return {article_list:result[0].data,banners:result[1].data,click_list:result[2].data,active_type_list:result[3].data}
    });
  }

export default  home;