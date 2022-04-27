import React from 'react';
import Top from '@components/blog/Top';
import HeadMeta from '@components/blog/HeadMeta';
import Footer from '@components/blog/Footer';
import Charts from '@components/blog/Charts';
import Info from '@components/blog/Info';
import { Card, Pagination,message } from 'antd';
import styles from '@css/blog/aboutus.module.css';
import { withRouter } from 'next/router'
import { list_article,click_charts,get_type } from '@api/blog';

const { Meta } = Card;

const type_enum={
    "produce":"PRODUCE",
    "tool":"TOOL",
    "ms":"MS",
}

class Other extends React.Component{

    render(){
        const {click_list,info,article}=this.props;
        return(
            <div>
                <HeadMeta />
                 <Top />
                 <div className='contains'>
                     <div className='container'>
                        <div className={styles.left}>
                           <Card>
                              {info.introduce}
                           </Card>

                            <Card>
                                {article&&article.list&&article.list.map((item)=>{
                                    return (
                                        <a key={item.id} href={'/blog/detail/'+item.id}>
                                        <Card key={item.id} style={{ width: 200,float:'left',margin:13 }}
                                            cover={<img alt="example" src={item.pic_url}/>}>
                                            <Meta title={item.title} />
                                        </Card>
                                        </a>
                                    );
                                })}
                                <div style={{clear:'both'}}></div>
                                <Pagination style={{float:'right'}} 
                                 onChange={(page)=>{
                                    this.props.router.push("/blog/other/"+type+"?page="+page);
                                 }} 
                                defaultCurrent={article.page_num} total={article.total} />
                            </Card>
                        </div>

                        <div className={styles.right}>
                           <Charts click_list={click_list}/>
                            <Info style={{marginTop:20}}/>
                        </div>
                    </div>
                </div>

                <div style={{clear:'both',height:20}}></div>
                <Footer/>

            </div>
        )
    }
}


Other.getInitialProps = async (res) => {
    const {type,page,page_size} =res.query;
    return Promise.all([
           list_article(type_enum[type],page,page_size),click_charts(),get_type(type)
           ]).then((result) => {
         return {type:type,article:result[0].data,click_list:result[1].data,info:result[2].data}
    });
  }

export default withRouter(Other);
