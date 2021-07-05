import React from 'react';
import { Card,Carousel } from 'antd';
import styles from '@css/website/partner.module.css';

export default class PartnerIndex extends React.Component{

    render(){
      const {partners} =this.props;
        return(
            <Card>
             <div className={styles.container}>
                 <div className={styles.title}><h1>合作伙伴</h1></div>
                 <div className={styles.partner}>
                  <Carousel effect="fade" dots={false} autoplay={true}>
                  {partners&&partners.map((item,index)=>{
                      return <div key={index}>
                        {item&&item.map((children)=>{
                            return <a key={children.id} style={{float:'left',marginLeft:20}} href={children.href}><img alt={children.title} src={children.pic_url} /></a> 
                        })}
                      </div>
                  })} 
                </Carousel>
                </div>
             </div>
           </Card>
        );
    }
}