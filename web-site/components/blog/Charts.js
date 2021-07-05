import React from 'react';
import { Card, List, } from 'antd';

export default class Charts extends React.Component{
    render(){
        const {click_list} =this.props;
        return (
            <Card {...this.props}  tabList={[{key:'1',tab: '点击排行榜'}]}>
                <List
                    size="small"
                    dataSource={click_list?click_list:[]}
                    renderItem={item => (
                      <List.Item>
                        <a href={'/blog/detail/'+item.id} >
                        {item.reprint?<div><span style={{color:'#1DA57A'}}>(转载)</span>&nbsp;&nbsp;{item.title}</div>:item.title }
                        </a>
                      </List.Item>
                    )}
                />
            </Card>
        )
    }
}