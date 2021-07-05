import React from 'react';
import { Table} from 'antd';


export default class DefaultTable extends React.Component{
   render(){
     const {data}=this.props;
     const pagination = {
                pageSize: data.page_size, total: data.total,
                defaultPageSize: 10,
                showQuickJumper: true, showSizeChanger: true
        };
     return (
             <Table bordered rowKey='id'
              style={{marginTop:30}}
              size={'small'} 
              {...this.props} 
              pagination={pagination}
              dataSource={data.list} 
             />
     );
   }

}