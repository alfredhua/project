import React from 'react';
import Head from 'next/head'
import Top from '@components/navigate/Top'
import { Card } from 'antd';
import styles from '@css/navigate/index.module.scss';
import { Affix, Anchor } from 'antd';
import { list_ngvigate } from '@api/navigate'
const { Link } = Anchor;
import LabelCard from '@components/navigate/LabelCard';

class Index extends React.Component{
  render(){
    const { hots,fronts,javas,pythons,admins,mids }=this.props;
    return (
      <div >
        <Head> 
           <title>手册-导航网</title>
            <meta name="keywords" content={"d"}/>
            <meta name="description" content={"d"} />
        </Head>
        <Top></Top>
        <div className={styles.contains}>
          <div className={styles.left}>
            <Affix offsetTop={80} >
               <Anchor >
                  <Link title={"推荐手册"} href="#hot"></Link>
                  <Link title={"前端技术"} href="#fronts"></Link>
                  <Link title={"JAVA手册"} href="#java"></Link>
                  <Link title={"Python手册"} href="#python"></Link>
                  <Link title={"后端技术"} href="#admin"></Link>
                  <Link title={"中间件"} href="#mid"></Link>
               </Anchor>
            </Affix>
          </div>
        <div style={{clear:'both'}}></div>

          <div className={styles.right}>
            <div className={styles.card} >
                <Card id="tool" tabList={[{key:'1',tab: '常用工具'}]}>
                  <ul>
                  {tools && tools.map(item=>{
                    return <li key={item.id}>
                        <img src={item.icon}/>
                        <a href={item.href} target='_blank'>{item.title}</a>
                    </li>
                  })}
                  </ul>
                </Card>
            </div> 

            <LabelCard id="hot" title={"推荐手册"} list={hots}></LabelCard>
            <LabelCard id="fronts" title={"前端技术"} list={fronts}></LabelCard>
            <LabelCard id="java" title={"JAVA手册"} list={javas}></LabelCard>
            <LabelCard id="python" title={"Python手册"} list={pythons}></LabelCard>
            <LabelCard id="admin" title={"后端技术"} list={admins}></LabelCard>
            <LabelCard id="mid" title={"中间件"} list={mids}></LabelCard>
          </div>
        </div>
      </div>
    )
  }
}

Index.getInitialProps = async (res) => Promise.all([
  list_ngvigate('MANUAL', 'HOT'),
  list_ngvigate('MANUAL', 'FRONT'),
  list_ngvigate('MANUAL', 'JAVA'),
  list_ngvigate('MANUAL', 'PYTHON'),
  list_ngvigate('MANUAL', 'ADMIN'),
  list_ngvigate('MANUAL', 'MID'),
]).then((result) => {
  return {
    hots: result[0].data,
    fronts:result[1].data,
    javas:result[2].data,
    pythons:result[3].data,
    admins:result[4].data,
    mids:result[5].data
  };
})

export default Index;