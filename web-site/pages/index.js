import React from 'react';
import Head from 'next/head'
import Top from '@components/navigate/Top'
import { Card } from 'antd';
import styles from '@css/navigate/index.module.scss';
import { Affix, Button } from 'antd';

class Index extends React.Component{
  render(){
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
            <Affix>
              <Button type="primary">
                Affix top
              </Button>
            </Affix>
          </div>

          <div className={styles.right}>
            {/* <div className={styles.card} >
                <Card tabList={[{key:'1',tab: '常用工具'}]}>
                  <p>Card content</p>
                  <p>Card content</p>
                  <p>Card content</p>
                </Card>
            </div> */}
          </div>
        </div>
      </div>
    )
  }
}

Index.getInitialProps = async (res) => {
  // return Promise.all().then((result) => {
  //   return {}
  // });
  return {};
}

export default Index;