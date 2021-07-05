import React from 'react';
import { Input,Form } from 'antd';

export default class DefaultInput extends React.Component{

    render(){
      const {label,error}=this.props;
      return(
          <div style={{marginLeft:'80px',marginTop:'20px',float:'left',width:'450px'}}>
            <div style={{float:'left',width:'60px',height:'30px',marginTop:'5px' }}>{label}</div>
             <Input {...this.props} style={{ width:'300px',marginLeft:'10px' }} />
          </div>
      );
    }
}

