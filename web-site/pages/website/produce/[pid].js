import React from 'react';
import styles from '@css/website/produce.module.css';
import { Card } from 'antd';
import Top from '@components/Top';
import Footer from '@components/Footer';
import { Carousel } from 'antd';
import { get_produce,list_setting } from '@api/website';

class produceDetail extends React.Component{

  render(){
     const {produce,file_list,setting} =this.props;
      return(
           <div >
             <Top active={'PRODUCE'} setting={setting}/>
             <Card className={'contains'} style={{paddingTop:110}}>
                <div className={styles.container}>
                    <div><h1>{produce&&produce.title?produce.title:null}</h1></div>
                    <div style={{float:'left',width:'50%'}}>
                      {file_list&&file_list.length>0?
                        <Carousel autoplay className={styles.produce_file} dotPosition={'Left'}>
                          {file_list.map((item,index)=>{
                                return (
                                  <div key={index}>
                                    <img alt={item.title} src={item.url} />
                                  </div>
                                )
                            })} 
                        </Carousel>:null}
                    </div>
                    <div dangerouslySetInnerHTML={{ __html:produce&&produce.context?produce.context:null}} /> 
                </div>
              </Card>
              
             <Footer/>
           </div>
        );
    }
}

produceDetail.getInitialProps = async (res) => {
  const {pid} =res.query;
  return Promise.all([get_produce(pid),list_setting()]).then((result) => {
    return {produce:result[0].data,file_list:[],setting:result[1].data}
  });
}

export default produceDetail;