import { lazy } from "react";
import { withRouter } from "react-router-dom";

export const develop_routers=[
  {
    path:"/develop/list-sql",
    auth_path:"/develop/sql/list",
    component:withRouter(lazy(() => import("./Druid"))),
    comment:"SQL监控"
  },{
    path:"/develop/list-swagger",
    auth_path:"/develop/swagger/list",
    component:withRouter(lazy(() => import("./Swagger"))),
    comment:"API文档"
  },{
    path:"/develop/list-deploy",
    auth_path:"/develop/deploy/list",
    component:withRouter(lazy(() => import("./Deploy"))),
    comment:"配置中心"
  },{
    path:"/develop/test",
    auth_path:"/develop/deploy/list",
    component:withRouter(lazy(() => import("./AimsTest"))),
    comment:"aim测试"
  }
]

