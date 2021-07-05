import React from 'react';
import { Card, Tabs,List  } from 'antd';
import Top from '@components/Top';
import Footer from '@components/Footer';
import styles from  '@css/website/notice.module.css';
const { TabPane } = Tabs;

import { list_setting,list_active_notice_type,list_notice } from '@api/website';

 class Notice extends React.Component{

  state={
    list:[]
  }

  componentDidMount(){
    this.get_notice(1,10,"HELP");
  }

  async change_tab(key){
    this.get_notice(1,10,key);
  }

  async get_notice(page_num,page_size,type){
    const result= await list_notice(page_num,page_size,type);
    this.setState({list:result.data.list});
  }

  render(){
    const {type_list,setting} =this.props;
    const { list } =this.state;
    return(
           <div >
             <Top active={'NOTICE'} setting={setting} />

             <div className='contains'  style={{paddingTop:110}}>
                <Card>
                  <div className={styles.container} >
                  <Tabs tabPosition={'left'} tabBarStyle={{width:300}} onChange={(key)=>{this.change_tab(key)}}>
                    {type_list&&type_list.map((item)=>{
                      return <TabPane tab={item.name} key={item.type}>
                             <List
                                pagination={{
                                  onChange: page => {
                                  }, pageSize: 10,
                                }}
                                style={{height:500}}
                                itemLayout="horizontal"
                                dataSource={list}
                                renderItem={item => (
                                  <List.Item>
                                    <List.Item.Meta
                                      title={<a href={"/website/notice/"+item.id}>{item.title}</a>}
                                    />
                                  </List.Item>
                                )}
                              />
                        
                      </TabPane>
                    })}
                  </Tabs>
                  </div>
               </Card>
             </div>

              <Footer/>
           </div>
        );
    }
}


Notice.getInitialProps = async (res) => {
  return Promise.all([list_setting(),list_active_notice_type()]).then((result) => {
    return {setting:result[0].data,type_list:result[1].data};
  });
}


export default Notice;