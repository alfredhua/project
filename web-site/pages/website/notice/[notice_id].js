import React from 'react';
import styles from '@css/website/notice.module.css';
import { Card } from 'antd';
import Top from '@components/Top';
import Footer from '@components/Footer';
import { get_notice,list_setting } from '@api/website';


 class noticeDetail extends React.Component{

  render(){
     const { notice,setting } =this.props;
      return(
           <div >
             <Top active={'NOTICE'} setting={setting}/>

             <Card className={'contains'} style={{paddingTop:110}}>
                <div className={styles.container_detail}>
                    <div className={styles.title}><h1>{notice&&notice.title?notice.title:null}</h1></div>
                    <div dangerouslySetInnerHTML={{ __html:notice&&notice.context?notice.context:null}} /> 
                </div>
              </Card>
              
             <Footer/>
           </div>
        );
    }
}


noticeDetail.getInitialProps = async (res) => {
  const {notice_id} =res.query;
  console.log(notice_id,"--------------")
  return Promise.all([list_setting(),get_notice(notice_id)]).then((result) => {
    return {setting:result[0].data,notice:result[1].data};
  });
}


export default noticeDetail;