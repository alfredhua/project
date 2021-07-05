import React from "react";
import { Carousel } from 'antd';
import '@css/components/banner.module.css';

export default class Banner extends React.Component {
  render () {
    const {banners} =this.props;
    return (
        <Carousel autoplay>
            {banners &&banners.map((item)=>{
                return ( 
                <div key={item.id}>
                      <a target={'_blank'} href={item.href&&item.href!==''&&item.href!=null?item.href:'#'}>
                        <img  style={{width: "100%"}}alt={item.name} src={item.url} />
                      </a> 
                  </div>
                )
            })}
        </Carousel>
    );
  }
};