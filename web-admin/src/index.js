import React,{Suspense} from 'react';
import { lazy } from "react";
import ReactDOM from 'react-dom';
import { withRouter } from "react-router-dom";
import {auth_routers} from 'auth/app';
import {website_routers} from 'website/app';
import {blog_routers} from 'blog/app';
import {develop_routers} from 'develop/app';

import { BrowserRouter,Route,Switch,Redirect } from "react-router-dom";
import AuthRoute from "common/components/router/AuthRoute";
const Error404 = withRouter(lazy(() => import("common/components/error/Error404")));
const Error403 = withRouter(lazy(() => import("common/components/error/Error403")));
const Login = withRouter(lazy(() => import("auth/login/Login")));
class Main extends React.Component{
    render(){
        return(
          <Suspense fallback={<div>loading...</div>}>
            <BrowserRouter>
              <Switch>
                   <Route path="/auth" component={props => <AuthRoute _props={props} routers={auth_routers}/>}  />
                   <Route path="/website" component={props => <AuthRoute _props={props} routers={website_routers}/>}  />
                   <Route path="/blog" component={props => <AuthRoute _props={props} routers={blog_routers}/>}  />
                   <Route path="/develop" component={props => <AuthRoute _props={props} routers={develop_routers}/>}  />

                   <Route exact path="/"  component={props =><Redirect to="/login"/>} />
                   <Route path="/login"  component={props =><Login _props={props}/>} />
                   <Route exact path="/403" render={props => <Error403/>} />
                   <Route render={props => <Error404 _props={props}/>} />         
               </Switch> 
          </BrowserRouter>
        </Suspense> 
        );
    }
}
ReactDOM.render(<Main/>,document.getElementById('root'));
// serviceWorker.unregister();
