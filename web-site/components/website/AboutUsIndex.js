import React from 'react';
import styles from  '@css/website/aboutus.module.css';
import { Card } from 'antd';
import { Player } from 'video-react';
import "video-react/dist/video-react.css"; // import css

export default class AboutUsIndex extends React.Component{

    render(){
        const {about_us}=this.props;
        return(
            <Card>
               <div className={styles.container}>
                     <div className={styles.info}>
                        <h1>关于我们</h1>
                        <div>
                            <p>{about_us.introduce}</p>
                            <a href="/website/about-us">了解更多 ></a>
                        </div>
                     </div>
                     <div className={styles.publicize}>
                         <Player  autoPlay={true} src={about_us.url} /> 
                     </div>
                </div>
             </Card>
        );
    }
}