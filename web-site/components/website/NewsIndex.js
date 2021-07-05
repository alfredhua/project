import React from 'react';
import { Card, List } from 'antd';
import styles from "@css/website/news.module.css";

export default class NewsIndex extends React.Component{
  render(){
        const {news} = this.props;
        return(
           <Card >
            <div className={styles.container}>
                <div className={styles.title}><h1>新闻动态</h1></div>
                <div>
                    <List
                        itemLayout="horizontal"
                        dataSource={news}
                        grid={{gutter: 1, column: 2}}
                        renderItem={item => (
                          <List.Item>
                              <div style={{marginBottom:20}}>
                                  <div style={{marginLeft:20,width:200,float:'left'}} >
                                        <img  width={200} alt="图片" src={item.pic}/>
                                  </div>
                                  <div style={{float:'left',textAlign:'left',marginLeft:20,width:310,height:100}} >
                                    <List.Item.Meta
                                        title={<a href={item.source_url}  target="_blank" rel="noopener noreferrer">{item.title}</a>}
                                        description={item.introduce}/>
                                  </div>
                              </div>
                          </List.Item>
                        )}
                      />
                 </div>
                <div style={{clear:'both'}}></div>
                 <div className={styles.more}>
                     <a href="/website/news">了解更多 ></a>
                 </div>
                 
            </div>
         </Card>
        );
    }
}