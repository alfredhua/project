import { message } from 'antd';
import {post} from 'common/util/request';

export const load_auth_data=(async()=>{
    const {code,msg,data}= await post("/auth/list-all-auth-data",{});
    if(code === 'SUCCESS' ){
        return data;
    }
    message.error("请求失败！"+msg);
    return data;
});