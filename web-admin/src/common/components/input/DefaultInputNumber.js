import React from 'react';
import { InputNumber } from 'antd';

export default class DefaultInputNumber extends React.Component{

    render(){
      const {label}=this.props;
      return(
        <div style={{marginTop:'20px'}}>
        <div style={{float:'left',marginTop:'5px',width:'200px',height:'20px',textAlign:'right'}}>
          <label style={{textAlign:'right'}}> {label}：</label>
        </div>
         <InputNumber {...this.props} style={{width:'750px'}} placeholder={label}  />
      </div>
      );
    }
}

