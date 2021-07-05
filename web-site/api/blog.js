import { post } from './api';


export const list_article_home= async()=>{
    return await post('/site/blog/list-article-home',{});
}


export const list_article= async(type,page_num,page_size)=>{
    return await post('/site/blog/list-article',{type,page_num,page_size});
}


export const get_article= async(id)=>{
    return await post('/site/blog/get-article',{id});
}

export const click_charts= async(type)=>{
    return await post('/site/blog/click-charts',{type:type?type:null});
}


export const list_all_active_type= async()=>{
    return await post('/site/blog/list-all-active-type',{});
}

export const get_type= async(type)=>{
    return await post('/site/blog/get-type',{type});
}


export const list_banner= async(type)=>{
    return await post('/site/website/list-banner-by-type',{type});
}

