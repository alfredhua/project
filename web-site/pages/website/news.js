import React from 'react';
import { Card,List } from 'antd';
import classNames from 'classnames';
import Top from '@components/Top';
import Footer from '@components/Footer';
import styles from '@css/website/news.module.css';
import { list_setting,list_news_page } from '@api/website';

class news extends React.Component{
    render(){
    const {news,setting} =this.props;
    return(
           <div >
             <Top active={'NEWS'} setting={setting}/>

             <div className='contains'  style={{paddingTop:110}}>
                <Card>
                  <div className={classNames(styles.container,styles.news)}>
                        <List itemLayout="vertical"  size="small"  
                          pagination={{
                            onChange: page => {
                            }, pageSize: 10,
                          }}
                          dataSource={news.list}
                          renderItem={item => (
                            <List.Item
                              key={item.title}
                              extra={ <img alt={item.title} width={272} src={item.pic} /> }>
                              <List.Item.Meta
                                title={<a href={item.source_url}  target="_blank" rel="noopener noreferrer">{item.title}</a>}
                                description={item.introduce}
                              />
                            </List.Item>
                          )}
                        />
                  </div>
                </Card>
                </div>

                <Footer/>
           </div>
        );
    }
}

news.getInitialProps = async (res) => {
  return Promise.all([list_setting(),list_news_page()]).then((result) => {
    return {setting:result[0].data,news:result[1].data}
  });
}

export default news;