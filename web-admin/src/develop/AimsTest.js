import React from 'react';

import {render as renderAmis} from 'amis';
import {ToastComponent, AlertComponent, alert, confirm, toast} from 'amis-ui';

import test from './json/test.json';


export default class AimsTest extends React.Component{


  render(){
    let amisScoped;
    let theme = 'cxd';
    let locale = 'zh-CN';

    return(
    <div>
        <p>通过 amis 渲染页面</p>
        <ToastComponent
          theme={theme}
          key="toast"
          position={'top-right'}
          locale={locale}
        />
        <AlertComponent theme={theme} key="alert" locale={locale} />
        {renderAmis(
          test,
        )

        }

      </div>
    )
  }

}
