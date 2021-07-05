import { lazy } from "react";
import { withRouter } from "react-router-dom";

//首页相关
const Home = withRouter(lazy(() => import("auth/home/Home")));
const home=[{
  path:"/auth/home",
  component:Home,
  auth:true,
  comment:"首页"
}];

// 人员相关
const AdminList = withRouter(lazy(() => import("auth/admin/AdminList")));
const AdminEdit = withRouter(lazy(() => import("auth/admin/AdminEdit")));
const AdminPasswordEdit = withRouter(lazy(() => import("auth/admin/AdminPasswordEdit")));
const AdminInfo = withRouter(lazy(() => import("auth/admin/AdminInfo")));
const admin=[ {
  path:"/auth/list-admin",
  auth_path:"/auth/admin/list",
  component:AdminList,
  comment:"管理员列表"
}, {
  path:"/auth/create-admin",
  auth_path:"/auth/admin/create",
  component:AdminEdit,
  comment:"管理员创建"
}, {
  path:"/auth/edit-admin/:id",
  auth_path:"/auth/admin/edit",
  component:AdminEdit,
  comment:"管理员编辑"
},{
  path:"/auth/update-password",
  auth_path:"/",
  component:AdminPasswordEdit,
  comment:"密码修改"
}, {
  path:"/auth/update-info",
  auth_path:"/",
  component:AdminInfo,
  comment:"密码修改"
}];

//角色相关
const RoleEdit = withRouter(lazy(() => import("auth/role/RoleEdit")));
const RoleList = withRouter(lazy(() => import("auth/role/RoleList")));
const role=[{
  path:"/auth/create-role",
  auth_path:"/auth/role/create",
  component:RoleEdit,
  comment:"角色创建"
}, {
  path:"/auth/edit-role/:id",
  auth_path:"/auth/role/edit",
  component:RoleEdit,
  comment:"角色编辑"
}, {
  path:"/auth/list-role",
  auth_path:"/auth/role/list",
  component:RoleList,
  comment:"角色列表"
}];


//数据权限相关
const DataList = withRouter(lazy(() => import("auth/data/DataList")));
const data=[ {
  path:"/auth/list-data",
  auth_path:"/auth/data/list",
  component:DataList,
  comment:"数据权限列表"
}];

export  const auth_routers=home.concat(admin).concat(role).concat(data);
