import React from 'react';
import { Card,message,Tag } from 'antd';

const allcolor=['magenta','red','volcano','cyan','orange','gold','purple','green','lime','geekblue']

export default class Charts extends React.Component{
 
    change_type(item){
        const {get_type}=this.props;
        if(get_type){
          get_type(item.type);
        }
    }

    render(){
        const {active_type_list}=this.props;
        return (
            <Card {...this.props}>
                {active_type_list&&active_type_list.map((item,index)=>{
                   return  <Tag onClick={()=>{this.change_type(item)}}key={item.type} color={allcolor[index%10]} style={{marginTop:10}}>{item.name}</Tag>
                })}
            </Card>
        )
    }
}