import BraftEditor from 'braft-editor'
import React from 'react';
import 'braft-editor/dist/index.css';
import { Upload,Icon } from 'antd'
import { ContentUtils } from 'braft-utils'

export default class DefaultBraftEditor extends React.Component{

  state={
    editor_state:null
  }


  upload = ({file,fileList}) => {
      if(file.status==='done'){
        const {onChange,context} =this.props;
        onChange(ContentUtils.insertMedias(BraftEditor.createEditorState(context?context:null), [{
              type: 'IMAGE',
              url: file.response.path
        }]))
      }
    }
      
    render(){
        const extend_controls = [
            {
              key: 'antd-uploader',
              type: 'component',
              component: (
                <Upload
                  accept="image/*"
                  action='/api/common/upload'
                  showUploadList={false}
                  onChange={this.upload}>
                  <button type="button" className="control-item button upload-button" data-title="插入图片">
                    <Icon type="picture" theme="filled" />
                  </button>
                </Upload>
              )
            }
          ]

    const {context} =this.props;
     return(
      <BraftEditor style={{border:'1 auto'}} 
      media={{accepts:{image:false,video:false,audio:false}}}
      value={BraftEditor.createEditorState(context?context:null)}
      extendControls={extend_controls}
      {...this.props}
       />
     )
    }
}