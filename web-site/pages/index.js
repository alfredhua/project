import React from 'react';
import Head from 'next/head'
import Top from '@components/navigate/Top'
import { Card } from 'antd';
import styles from '@css/navigate/index.module.scss';
import { Affix, Anchor } from 'antd';
import { list_ngvigate } from '@api/navigate'
const { Link } = Anchor;
import LabelCard from '@components/navigate/LabelCard';
// import Footer from '@components/navigate/Footer';

class Index extends React.Component{
  render(){
    const { tools,techs,relaxs,studys,dev_tools,medias,resources,pics,videos,web_tools}=this.props;
    return (
      <div >
        <Head> 
           <title>首页-导航网</title>
           <meta name="keywords" content={"d"}/>
            <meta name="description" content={"d"} />
        </Head>
        <Top></Top>
        <div className={styles.contains}>
          <div className={styles.left}>
            <Affix offsetTop={80} >
               <Anchor >
                  <Link title={"常用工具"} href="#tool"></Link>
                  <Link title={"技术社区"} href="#techs"></Link>
                  <Link title={"摸鱼专区"} href="#relaxs"></Link>
                  <Link title={"学习平台"} href="#studys"></Link>
                  <Link title={"开发工具"} href="#dev_tools"></Link>
                  <Link title={"头部社区"} href="#medias"></Link>
                  <Link title={"资源推荐"} href="#resources"></Link>
                  <Link title={"图片专区"} href="#pics"></Link>
                  <Link title={"视频专区"} href="#videos"></Link>
                  <Link title={"站长工具"} href="#web_tools"></Link>
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

            <LabelCard id="techs" title={"技术社区"} list={techs}></LabelCard>
            <LabelCard id="relaxs" title={"摸鱼专区"} list={relaxs}></LabelCard>
            <LabelCard id="studys" title={"学习平台"} list={studys}></LabelCard>
            <LabelCard id="dev_tools" title={"开发工具"} list={dev_tools}></LabelCard>
            <LabelCard id="medias" title={"头部社区"} list={medias}></LabelCard>
            <LabelCard id="resources" title={"资源推荐"} list={resources}></LabelCard>
            <LabelCard id="pics" title={"图片专区"} list={pics}></LabelCard>
            <LabelCard id="videos" title={"视频专区"} list={videos}></LabelCard>
            <LabelCard id="web_tools" title={"站长工具"} list={web_tools}></LabelCard>
            
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
  list_ngvigate('INDEX', 'TOOL'),
  list_ngvigate('INDEX', 'TECH'),
  list_ngvigate('INDEX', 'RELAX'),
  list_ngvigate('INDEX', 'STUDY'),
  list_ngvigate('INDEX', 'DEV_TOOL'),
  list_ngvigate('INDEX', 'MEDIA'),
  list_ngvigate('INDEX', 'RESOURCES'),
  list_ngvigate('INDEX','IMAGES'),
  list_ngvigate('INDEX','VIDEO'),
  list_ngvigate('INDEX','WEB_TOOL'),

]).then((result) => {
  return {
    tools: result[0].data,
    techs: result[1].data,
    relaxs: result[2].data,
    studys: result[3].data,
    dev_tools: result[4].data,
    medias: result[5].data,
    resources: result[6].data,
    pics:result[7].data,
    videos:result[8].data,
    web_tools:result[9].data
  };
})

export default Index;