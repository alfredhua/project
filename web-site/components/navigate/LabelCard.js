import React from 'react';
import classNames  from 'classnames'
import { Card } from 'antd';
import styles from '@css/navigate/index.module.scss'

export default class LabelCard extends React.Component{

    render(){
        const {list,title,id}=this.props;
        return (<div id={id} className={styles.card} >
                <Card  tabList={[{key:'1',tab: title}]}>
                  <div>
                  {list && list.map(item=>{
                    return <div className={styles.tab} key={item.id} onClick={()=>{window.open(item.href,"_blank")}}>
                        <div className={styles.images}>
                          <img  src={item.icon}/>
                        </div>
                        <div className={styles.title_instroduce}>
                          <div className={styles.title}>{item.title}</div>
                          <div className={styles.introduce}><p>{item.introduce}</p></div>
                        </div>
                    </div>
                  })}
                  </div>
                </Card>
                 <div style={{clear:'both'}}></div>
            </div>);
    }
} 
