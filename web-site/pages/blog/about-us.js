import React from 'react';
import Top from '@components/blog/Top';
import Footer from '@components/blog//Footer';
import Charts from '@components/blog/Charts';
import Info from '@components/blog/Info';
import { Layout,Menu,Card } from 'antd';
import HeadMeta from '@components/blog/HeadMeta';
import Type from '@components/blog/Type';
import { list_all_active_type,click_charts } from '@api/blog';
import styles from '@css/blog/aboutus.module.css';


const { Content, Sider} = Layout;

class AboutUs extends React.Component{
   
    render(){
        const { active_type_list,click_list }=this.props;
        return(
            <div>
                <HeadMeta/>
                 <Top />
                 <div className='contains'>
                     <div className='container'>
                        <div className={styles.left}>
                        <Layout>
                            <Sider theme={'light'}>
                                <Menu style={{ width: 200 }} mode="inline" defaultSelectedKeys={['ABOUT_US']}>
                                  <Menu.Item key="ABOUT_US">关于我</Menu.Item>
                                </Menu>
                            </Sider>
                            <Layout>
                              <Content>
                                  <Card style={{  minHeight: 360 }}>
                                      <p><span></span>华</p>
                                      <p><span>QQ：</span>1070242697</p>
                                      <p><span>爱好：</span>象棋，电脑，乒乓球</p>
                                      <p>学到就要教人，赚到就要给人。</p>
                                     
                                  </Card>
                                </Content>
                            </Layout>
                      </Layout>
                      <Type active_type_list={active_type_list} tabList={[{key:'1',tab: '技术栈'}]} style={{marginTop:20}}/>
                        </div>
                        <div className={styles.right}>
                            <Charts click_list={click_list}/>
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

AboutUs.getInitialProps = async (res) => {
    return Promise.all([
            list_all_active_type(),click_charts()
           ]).then((result) => {
         return {active_type_list:result[0].data,click_list:result[1].data}
    });
  }


export default AboutUs;