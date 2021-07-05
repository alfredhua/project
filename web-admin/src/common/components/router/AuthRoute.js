import React,{Suspense} from "react";
import { Route } from "react-router-dom";
import Navigate from 'common/components/navigate/Navigate';
import Error403 from 'common/components/error/Error403';
import {isok} from 'common/util/Auth';
import {post} from 'common/util/request';


export default class AuthRoute extends React.Component {

  constructor(props){
    super(props);
    this.state={
      admin_info:{auth_list:[]}
    }
  }

  componentDidMount(){
    this.load_auth();
  }
    
  async load_auth(){
    const result=await post("/auth/get-admin-by-token",{});
    if(result.code==='SUCCESS'){
        this.setState({admin_info:result.data});
      }else{
        this.props._props.history.push('/login');
    }
  }

  render(){
    const {auth_list} =this.state.admin_info;
    let admin=Object.assign({},this.state.admin_info);
    return(
      <Navigate _props={this.props._props} admin_info={this.state.admin_info}>
          <Suspense fallback={<div>loading...</div>}>
           {this.props.routers.map((item,index)=>{
                return(
                  <Route exact key={index} path={item.path}
                     render ={()=>{ 
                       if(!item.auth){
                        if(!isok(auth_list,item.auth_path)){
                          // <Redirect to={{ pathname: "/403"}}/>
                          return  <Error403 />;
                        }else{
                          return <item.component auth_list={auth_list} admin={admin}/>
                        }
                       }else{
                          return <item.component auth_list={auth_list} admin={admin}/>
                       }
                    }}
                   />
                );
            })} 
        </Suspense>
     </Navigate>
    );
  }
}