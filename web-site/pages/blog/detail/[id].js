import React from 'react';
import Top from '@components/blog/Top';
import HeadMeta from '@components/blog/HeadMeta';
import styles from '@css/blog/aboutus.module.css';
import Footer from '@components/blog/Footer';
import Charts from '@components/blog/Charts';
import Info from '@components/blog/Info';
import { Card } from 'antd';
import Markdown from 'markdown-to-jsx';
import { get_article,click_charts }  from '@api/blog';

const hljs = require("highlight.js/lib/highlight.js");

hljs.registerLanguage('java', require('highlight.js/lib/languages/java'));
hljs.registerLanguage('sql', require('highlight.js/lib/languages/sql'));
hljs.registerLanguage('haml', require('highlight.js/lib/languages/haml'));
hljs.registerLanguage('python', require('highlight.js/lib/languages/python'));

const CodeHight = ({ children, ...props }) => (
    <div dangerouslySetInnerHTML={{ __html: hljs.highlightAuto(children).value }} />
);

const ChangeImg=({children,...props})=>{
    return  <img style={{width:'100%'}} alt="图片"  src={props.src}></img>
};


class ArticleDetail extends React.Component{

    get_context(item){
        return <div dangerouslySetInnerHTML={{ __html:item.context}}></div>;
    }
  
    render(){
        const { info,click_list } =this.props;
        return(
            <div>
                <HeadMeta/>
                <Top />
                 <div className='contains'>
                     <div className='container'>
                        <div className={styles.left}>
                        <Card title={info.title}>
                            <div style={{fontSize:14}}>
                                {info.create_time} {info.click_count}人已围观<br/>
                            </div>
                            <div style={{marginTop:20,color:'#888888',background:'none repeat 0 0 #F6F6F6',padding:10}}>简介：{info.introduce}</div>
                            <div style={{height:20,width:'100%'}}></div>
                           {info.content_type===1? 
                            <Markdown options={{
                                        overrides: {
                                            code: {
                                                component: CodeHight,
                                            },
                                            img: {
                                                component: ChangeImg,
                                            }
                                        },

                               }}>{info.context}</Markdown>:
                              <div style={{marginTop:20}} dangerouslySetInnerHTML={{ __html:info.context}}></div>
                           }
                           
                        </Card>
                        </div>
                        <div className={styles.right}>
                            <Charts  click_list={click_list}/>
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



ArticleDetail.getInitialProps = async (res) => {
    const {id} =res.query;
    return Promise.all([
            get_article(id),click_charts()
           ]).then((result) => {
         return {info:result[0].data,click_list:result[1].data}
    });
  }

export default ArticleDetail;