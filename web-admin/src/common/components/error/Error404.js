import React from 'react';

export default class Error404 extends React.Component{
  constructor(props){
    super(props);
    this.state={
      timer:5
    }
  }

  componentDidMount(){
    this.count();
  }
  count = () => {
    let {timer}=this.state;
    let siv = setInterval(() => {
        this.setState({ timer: (timer--), text: timer }, () => {
            if (timer === 0) {
                clearInterval(siv);
                this.props._props.history.push("/");
            }
        });
    }, 1000);
}

  render(){
    return (
      <div style={{textAlign:'center'}}>
          <h1 style={{fontSize:'1000%'}}>404</h1>
          <h3>{this.state.timer}秒后返回首页</h3>

          <h3><a href="/">立即返回</a></h3>

      </div>
    )
  }
}