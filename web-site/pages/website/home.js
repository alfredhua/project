import React from 'react';
import Top from '@components/Top';
import Footer from '@components/Footer';
import ProduceIndex from '@components/website/ProduceIndex';
import NewsIndex from '@components/website/NewsIndex';
import AboutUsIndex from '@components/website/AboutUsIndex';
import PartnerIndex from '@components/website/PartnerIndex';
import styles from  '@css/website/home.module.css';
import Banner from '@components/Banner';
import { list_setting,list_banner,list_produce_home,list_news_home,get_setting,list_all_partner } from '@api/website';


class home extends React.Component{

  render(){
    const {setting,banners,produces,news,about_us,partners}=this.props;
    console.log(setting)
    return(
      <div >
         <Top active={'HOME'} setting={setting} />
          <div className={styles.banner}>
            <Banner banners={banners} />
          </div>
         {setting&& setting.map((item)=>{
            if(item.type=="PRODUCE"){
              return <div key={item.id}><ProduceIndex produces={produces}/> </div>;
            }
            if(item.type=="NEWS"){
              return <div key={item.id}><NewsIndex news={news}/> </div>;
            }
            if(item.type=="ABOUT_US"){
              return <div key={item.id}><AboutUsIndex about_us={about_us}/> </div>;
            }
            if(item.type=="PARTNER"){
              return <div key={item.id}><PartnerIndex partners={partners}/> </div>;
            }
          })} 
        <Footer />  
      </div>
    )
  }
}

home.getInitialProps = async (res) => {
  return Promise.all([list_setting(), list_banner('PC'), list_produce_home(),
                list_news_home(),get_setting("ABOUT_US"),list_all_partner()]
                ).then((result) => {
    return {setting:result[0].data,banners:result[1].data,produces:result[2].data,news:result[3].data,about_us:result[4].data,partners:result[5].data}
  });
}

export default  home;