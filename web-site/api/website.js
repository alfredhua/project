import { post,postString } from './api';

export const list_banner= async(type)=>{
    return await post('/site/website/list-banner-by-type',{type});
}

//设置
export const list_setting= async()=>{
    return await post('/site/website/list-setting',{});
}

export const list_children_setting= async(type)=>{
    return await postString(`/site/website/list-children-setting/${type}`,{});
}

export const get_setting= async(type)=>{
    return await postString(`/site/website/get-setting/${type}`,{});
}

//新闻
export const list_news_home = async()=>{
    return await post('/site/website/list-news-home',{});
}

export const list_news_page = async(page_num,page_size)=>{
    return await post('/site/website/list-news-page',{page_num,page_size});
}


//公告
export const list_active_notice_type = async(page_num,page_size)=>{
    return await post('/site/website/list-active-notice-type',{});
}

export const list_notice = async(page_num,page_size,type)=>{
    return await post('/site/website/list-notice',{page_num,page_size,type});
}

export const get_notice = async(id)=>{
    if(id&&id==''){
      return {data:{title:null,list:[]}};
    }
    return await postString(`/site/website/get-notice/${id}`,{});
}

//伙伴
export const list_all_partner = async()=>{
    return await post('/site/website/list-all-partner',{});
}

//产品
export const list_produce_home = async()=>{
    return await post('/site/website/list-produce-home',{});
}

export const list_all_produce = async()=>{
    return await post('/site/website/list-all-produce',{});
}

export const get_produce = async(id)=>{
    if(!id){
        return {title:null,list:[]};
    }
    return await postString(`/site/website/get-produce/${id}`,{});
}
