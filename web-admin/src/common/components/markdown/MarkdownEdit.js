import React from 'react';
// 引入编辑器样式
import { Input } from 'antd';
import Markdown from 'markdown-to-jsx';
const hljs = require("highlight.js/lib/highlight.js");
hljs.registerLanguage('java', require('highlight.js/lib/languages/java'));
hljs.registerLanguage('sql', require('highlight.js/lib/languages/sql'));
hljs.registerLanguage('haml', require('highlight.js/lib/languages/haml'));
hljs.registerLanguage('python', require('highlight.js/lib/languages/python'));
const TextArea=Input.TextArea;

 const CodeHight = ({ children, ...props }) => (
    <div dangerouslySetInnerHTML={{ __html: hljs.highlightAuto(children).value }} />
);

const ChangeImg=({children,...props})=>{
    return  <img style={{width:'100%'}} alt="" src={props.src}></img>
};
export default class MarkdownEdit extends React.Component{

  change(value){
      let {onChange} =this.props;
      onChange(value);
  }

  render(){
      const {text} =this.props;
    return(
        <div>
            <div style={{width:'50%',float:'left'}} >
                <TextArea rows={50}  value={text} onChange={(e)=>{this.change(e.target.value)}}> </TextArea>
            </div>
            <div  style={{float:'left',width:'45%',marginLeft:'10px',minHeight:"32px",maxHeight:'1050px',overflowX:"auto" }}>
              <Markdown
               options={{
               overrides: {
                code: {
                    component: CodeHight,
                    props: {
                        className: 'foo',
                    },
                },
                img: {
                    component: ChangeImg,
                }
               },
             }}>{text}</Markdown>
            </div>
            <div style={{clear:'both'}}></div>
        </div>
    );
  }

}