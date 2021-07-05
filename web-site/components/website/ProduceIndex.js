import React from 'react';
import styles from '@css/website/produce.module.css';
import { Card, } from 'antd';
import classNames from 'classnames';

const { Meta } = Card;

export default class ProduceIndex extends React.Component{
    render(){
      const {produces} =this.props;
        return(
            <Card>
              <div className={classNames(styles.container)} >
                <div className={styles.title}><h1>产品服务</h1></div>
                 <div>
                    {produces&& produces.map(item=>{
                      return (
                        <a key={item.id} href={`/website/produce/${item.id}`}>
                          <Card 
                            hoverable
                            style={{ width: 250,float:'left',margin:20,height:288}}
                            cover={<img alt="example" src={item.cover_image} />} >
                            <Meta title={item.title} description={item.introduce} />
                          </Card>
                        </a>
                      )
                    })}
                  </div>
                  <div className={styles.more}>
                      <a href="/website/produce">了解更多 ></a>
                  </div>
               </div>
        </Card>
        );
    }
}