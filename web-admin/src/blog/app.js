import { lazy } from "react";
import { withRouter } from "react-router-dom";

export const blog_routers=[
  {
    path:"/blog/list-article",
    auth_path:"/blog/article/list",
    component:withRouter(lazy(() => import("./article/ArticleList"))),
    comment:"博文列表"
  },{
    path:"/blog/create-article",
    auth_path:"/blog/article/create",
    component:withRouter(lazy(() => import("./article/ArticleEdit"))),
    comment:"博文创建"
  },{
    path:"/blog/edit-article/:id",
    auth_path:"/blog/article/edit",
    component:withRouter(lazy(() => import("./article/ArticleEdit"))),
    comment:"博文编辑"
  },{
    path:"/blog/list-type",
    auth_path:"/blog/type/list",
    component:withRouter(lazy(() => import("./type/TypeList"))),
    comment:"类型列表"
  }
]

