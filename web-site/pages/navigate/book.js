import React from 'react';
import Head from 'next/head'
import Top from '@components/navigate/Top'
import { Card } from 'antd';
import styles from '@css/navigate/index.module.scss';
import { Affix, Anchor } from 'antd';
import { list_ngvigate } from '@api/navigate'
import LabelCard from '@components/navigate/LabelCard';
const { Link } = Anchor;

class Index extends React.Component{
  render(){
    const { bases,javas,pythons,fronts,admins,others}=this.props;
    return (
      <div >
        <Head> 
           <title>书籍-导航网</title>
           <meta name="keywords" content={"d"}/>
            <meta name="description" content={"d"} />
        </Head>
        <Top></Top>
        <div className={styles.contains}>
          <div className={styles.left}>
            <Affix offsetTop={80} >
               <Anchor >
                  <Link title={"计算机基础"} href="#base"></Link>
                  <Link title={"JAVA书籍"} href="#java"></Link>
                  <Link title={"Python书籍"} href="#python"></Link>
                  <Link title={"前端书籍"} href="#front"></Link>
                  <Link title={"后端数据"} href="#admin"></Link>
                  <Link title={"其它书籍"} href="#other"></Link>
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

            <LabelCard id="base" title={"计算机基础"} list={bases}></LabelCard>
            <LabelCard id="java" title={"JAVA书籍"} list={javas}></LabelCard>
            <LabelCard id="python" title={"Python书籍"} list={pythons}></LabelCard>
            <LabelCard id="front" title={"前端书籍"} list={fronts}></LabelCard>
            <LabelCard id="admin" title={"后端数据"} list={admins}></LabelCard>
            <LabelCard id="other" title={"其它书籍"} list={others}></LabelCard>
          </div>
        </div>
        <div style={{clear:'both'}}></div>
        <div className={styles.foot}>
            <Card></Card>
        </div>
      </div>
    )
  }
}

Index.getInitialProps = async (res) => Promise.all([
  list_ngvigate('BOOK', 'BASE'),
  list_ngvigate('BOOK', 'JAVA'),
  list_ngvigate('BOOK', 'PYTHON'),
  list_ngvigate('BOOK', 'FRONT'),
  list_ngvigate('BOOK', 'ADMIN'),
  list_ngvigate('BOOK', 'OTHER')
]).then((result) => {
  return {
    bases: result[0].data,
    javas: result[1].data,
    pythons: result[2].data,
    fronts: result[3].data,
    admins: result[4].data,
    others: result[5].data
  };
})

export default Index;