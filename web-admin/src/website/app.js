import { lazy } from "react";
import { withRouter } from "react-router-dom";
const ArticleTypeList = withRouter(lazy(() => import("./noticeType/NoticeTypeList")));

export const website_routers=[
  {
    path:"/website/list-banner",
    auth_path:"/website/banner/list",
    component:withRouter(lazy(() => import("./banner/BannerList"))),
    comment:"banner列表"
  },{
    path:"/website/create-banner",
    auth_path:"/website/banner/create",
    component:withRouter(lazy(() => import("./banner/BannerEdit"))),
    comment:"banner创建"
  },{
    path:"/website/edit-banner/:id",
    auth_path:"/website/banner/create",
    component:withRouter(lazy(() => import("./banner/BannerEdit"))),
    comment:"banner创建"
  },{
    path:"/website/list-notice",
    auth_path:"/website/notice/list",
    component:withRouter(lazy(() => import("./notice/NoticeList"))),
    comment:"公告列表"
  },{
    path:"/website/list-notice/:search_type",
    auth_path:"/dictionary/notice-type/watch-notice",
    component:withRouter(lazy(() => import("./notice/NoticeList"))),
    comment:"公告列表"
  },{
    path:"/website/create-notice",
    auth_path:"/website/notice/create",
    component:withRouter(lazy(() => import("./notice/NoticeEdit"))),
    comment:"公告创建"
  },{
    path:"/website/edit-notice/:id",
    auth_path:"/website/notice/edit",
    component:withRouter(lazy(() => import("./notice/NoticeEdit"))),
    comment:"公告编辑"
  },{
    path:"/website/list-produce",
    auth_path:"/website/produce/list",
    component:withRouter(lazy(() => import("./produce/ProduceList"))),
    comment:"产品列表"
  },{
    path:"/website/create-produce",
    auth_path:"/website/produce/create",
    component:withRouter(lazy(() => import("./produce/ProduceEdit"))),
    comment:"产品创建"
  },{
    path:"/website/edit-produce/:id",
    auth_path:"/website/produce/edit",
    component:withRouter(lazy(() => import("./produce/ProduceEdit"))),
    comment:"产品编辑"
  },{
    path:"/website/set-produce-detail/:id",
    auth_path:"/website/produce/set-detail",
    component:withRouter(lazy(() => import("./produce/ProduceDetail"))),
    comment:"产品内容编辑"
  },{
    path:"/website/setting",
    auth_path:"/website/setting",
    component:withRouter(lazy(() => import("./setting/Setting"))),
    comment:"网站设置"
  },{
    path:"/website/setting/aboutus",
    auth_path:"/website/setting",
    component:withRouter(lazy(() => import("./setting/AboutUs"))),
    comment:"关于我们设置"
  },{
    path:"/website/setting/company",
    auth_path:"/website/setting",
    component:withRouter(lazy(() => import("./setting/CompanySetting"))),
    comment:"关于我们设置"
  },{
    path:"/website/list-news",
    auth_path:"/website/news/list",
    component: withRouter(lazy(() => import("./news/NewsList"))),
    comment:"新闻管理"
  },{
    path:"/website/create-news",
    auth_path:"/website/news/create",
    component:withRouter(lazy(() => import("./news/NewsEdit"))),
    comment:"新闻创建"
  },{
    path:"/website/edit-news/:id",
    auth_path:"/website/news/edit",
    component:withRouter(lazy(() => import("./news/NewsEdit"))),
    comment:"新闻编辑"
  },{
    path:"/website/list-partner",
    auth_path:"/website/news/list",
    component:withRouter(lazy(() => import("./partner/PartnerList"))),
    comment:"合作伙伴列表"
  },{
    path:"/website/create-partner",
    auth_path:"/website/news/create",
    component:withRouter(lazy(() => import("./partner/PartnerEdit"))),
    comment:"合作伙伴创建"
  },{
    path:"/website/edit-partner/:id",
    auth_path:"/website/news/edit",
    component:withRouter(lazy(() => import("./partner/PartnerEdit"))),
    comment:"合作伙伴编辑"
  }, {
    path:"/website/list-notice-type",
    auth_path:"/website/notice-type/list",
    component:ArticleTypeList,
    comment:"公告字典项"
  }
]

