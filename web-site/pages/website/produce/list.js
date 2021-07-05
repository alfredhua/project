import React from 'react';
import { Card } from 'antd';
import Top from '@components/Top';
import Footer from '@components/Footer';
import styles from '@css/website/produce.module.css';
import classNames from 'classnames';
import { list_all_produce,list_setting } from '@api/website';

const { Meta } = Card;

 class produce extends React.Component{
    render(){
        const {produce_list,setting} =this.props;
        return(
           <div >
             <Top active={'PRODUCE'} setting={setting} />
              <div className='contains' style={{paddingTop:110}}>
              <Card>
                 <div className={classNames(styles.container,styles.list)}>
                  {produce_list&&produce_list.map(item=>{
                      return (
                        <a key={item.id} href={`/website/produce/${item.id}`} >
                            <Card
                              hoverable
                              style={{ width: 275,float:'left',margin:'10px 8px' }}
                              cover={<img alt="example" src={item.cover_image} />}>
                              <Meta title={item.title} description=""  />
                            </Card>
                        </a>
                        )
                    })}
                  </div>
              </Card>
              </div>
             <Footer/>
           </div>
        );
    }
}

produce.getInitialProps = async (res) => {
  return Promise.all([list_setting(),list_all_produce()]).then((result) => {
    return {setting:result[0].data,produce_list:result[1].data}
  });
}


export default produce;