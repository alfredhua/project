import React from 'react';
import { Input } from 'antd';

export default class LongInput extends React.Component{

    render(){
      const { label }=this.props;
      return(
        <div style={{marginTop:'20px'}}>
        <div style={{float:'left',marginTop:'5px',width:'200px',height:'20px',textAlign:'right'}}>
          <label style={{textAlign:'right'}}> {label}：</label>
        </div>
         <Input {...this.props} style={{width:'300px'}} placeholder={label}  />
      </div>
      );
    }
}

