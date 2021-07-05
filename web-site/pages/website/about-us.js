import React from 'react';
import { Layout,Menu,Card } from 'antd';
import styles from  '@css/website/aboutus.module.css';
import Top from '/components/Top';
import Footer from '@components/Footer';
import { get_setting ,list_setting,list_children_setting  } from '@api/website';
const { Content, Sider} = Layout;

class aboutUs extends React.Component{

    render(){
      const { auth_list,info,setting } =this.props;
        return(
           <div >
             <Top active={'ABOUT_US'} setting={setting} />

               <div className="contains">
                  <div className={styles.container}  style={{paddingTop:80}}>
                     <Layout>
                            <Sider style={{margin:'10px 0px',minHeight:520}} theme={'light'}>
                                <Menu style={{ width: 200 }} mode="inline" defaultSelectedKeys={['ABOUT_US']}>
                                  <Menu.Item key="ABOUT_US" onClick={()=>{this.get_info('ABOUT_US')}}>关于我们</Menu.Item>
                                  {auth_list.map(item=>{
                                    if(item.status===1){
                                      return <Menu.Item key={item.type} onClick={()=>{this.get_info(item.type)}}>{item.name}</Menu.Item>
                                    }else{
                                      return null;
                                    }
                                  })}
                                </Menu>
                            </Sider>
                            <Layout>
                              <Content style={{ margin: '10px 20px'}}>
                                  <Card style={{  minHeight: 520 }}>
                                       <div dangerouslySetInnerHTML={{ __html:info.content}} /> 
                                  </Card>   
                                </Content>
                            </Layout>
                      </Layout>
                    </div>
                 </div>

             <Footer/>
           </div>
        );
    }
}

aboutUs.getInitialProps = async (res) => {
  return Promise.all([list_setting(),list_children_setting(),get_setting("ABOUT_US")]).then((result) => {
    return {setting:result[0].data,auth_list:result[1].data,info:{...result[2].data}}
  });
}

export default aboutUs;