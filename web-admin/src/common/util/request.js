import axios from 'axios';

export async function post(url, data){
    const result=await axios.post("/api"+url, data);
    return result.data;
}

export async function get(url){
  const result=await axios.get("/api"+url);
  return result.data;
}
