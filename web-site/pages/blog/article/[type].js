import React from 'react';
import Top from '@components/blog/Top';
import styles from '@css/blog/article.module.css';
import { Card, List } from 'antd';
import Footer from '@components/blog/Footer';
import Charts from '@components/blog/Charts';
import Type from '@components/blog/Type';
import Info from '@components/blog/Info';
import HeadMeta from '@components/blog/HeadMeta';
import { list_article,click_charts,list_all_active_type } from '@api/blog';
import { withRouter } from 'next/router'

class Article extends React.Component{
  
    get_context(item){
        let introduce;
        if(item&&item.introduce&&item.introduce.length>200){
            introduce=item.introduce.substring(0,200);
        }else{
            introduce=item.introduce;
        }
        return <div dangerouslySetInnerHTML={{ __html:introduce}}></div>;
    }

    render(){
        const {article,click_list,type,active_type_list } =this.props;
        return(
            <div>
                <HeadMeta/>
                 <Top />
                 <div className='contains'>
                     <div className='container'>
                        <div className={styles.left}>
                            <Card>
                                 <Type active_type_list={active_type_list}/>
                                <div>
                                <List  dataSource={article?article.list:[]}
                                  pagination={{
                                    onChange: page => {
                                        this.props.router.push("/blog/article/"+type+"?page="+page);
                                    },
                                    pageSize: article.page_size,
                                    total:article.total
                                  }}
                                    renderItem={item => (
                                        <List.Item>
                                            <div>
                                                <div style={{width:220,float:'left'}} >
                                                     <img width={204.8} alt="图片" src={item.pic_url}/>
                                                </div>
                                                <div style={{width:465,float:'left'}} >
                                                    <List.Item.Meta title={<a href={'/blog/detail/'+item.id} target="_blank"  rel="noopener noreferrer">{item.title}</a>}
                                                    description={this.get_context(item)}/>
                                                </div>
                                            </div>
                                        </List.Item>
                                        )}
                                    />
                                </div>
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


Article.getInitialProps = async (res) => {
    const {type,page,page_size} =res.query;
    return Promise.all([
           list_article(type,page,page_size),click_charts(),list_all_active_type()
           ]).then((result) => {
         return {type:type,article:result[0].data,click_list:result[1].data,active_type_list:result[2].data}
    });
  }

export default withRouter(Article);

