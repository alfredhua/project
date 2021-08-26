import { post } from './api';


export const list_ngvigate= async(one_type,two_type)=>{
    return await post('/site/navigate/list',{one_type,two_type});
}
