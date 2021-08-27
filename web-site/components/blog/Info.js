import React from 'react';
import { Card } from 'antd';

export default class info extends React.Component{
    render(){
        return (
            <Card {...this.props} tabList={[{key:'1',tab: '公众号'}]}>
            <img alt="ALFRED" style={{width:220}} src={'/static/images/blog/code.jpg'}/>
         </Card>
        )
    }
}