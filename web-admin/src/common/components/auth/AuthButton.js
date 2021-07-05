import React from 'react';
import { Button,Tag,Menu } from 'antd';
import {isok} from '../../util/Auth';

export default class AuthButton extends React.Component{
  
 get_render(auth_type){
   let button=null;
   var _props=Object.assign({},{...this.props});
   const { auth_list,auth_path }=this.props;
   switch(auth_type){
     case 'button':
        button= isok(auth_list,auth_path)?<Button type="primary" {..._props} >{this.props.children}</Button>:null;
        break;
     case 'tag':
       button= isok(auth_list,auth_path)?<Tag color="#1da57a" {..._props}>{this.props.children}</Tag>:null;
       break;
    case 'menu-item':
       button= isok(auth_list,auth_path)?<Menu.Item {..._props}>{this.props.children}</Menu.Item>:null;
       break;
     default:
        break;
   }
   return button;
 }

  render(){
      return(
        <span >
          {this.get_render(this.props.auth_type)}
        </span>
      )
  }
}