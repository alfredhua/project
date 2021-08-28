import React from 'react';
import { Card } from 'antd';
import classNames from 'classnames';
import styles from '@css/navigate/indexCard.module.scss'

export default class LabelCard extends React.Component{

    get_class =()=>{
      const {type} =this.props;
      if(type === 'book'){
        return classNames(styles.tab,styles.book_card);
      }
     if(type === 'manual'){
        return classNames(styles.tab,styles.manual_card );
      }
      return classNames(styles.tab);
    }

    render(){
        const {list,title,id }=this.props;

        return (<div id={id} className={styles.card} >
                <Card tabList={[{key:'1',tab: title}]}>
                  <div>
                  {list && list.map(item=>{
                    return <div className={this.get_class()} key={item.id} onClick={()=>{window.open(item.href,"_blank")}}>
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
