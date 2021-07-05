import Koa from 'koa';
const app = new Koa();

import authapp from '@server/auth/';
import commonapp from '@server/common/';
import websiteapp from '@server/website/';
import blogapp from '@server/blog/';
import developapp from '@server/develop/';

app.use(require('koa-logger')());
app.use(require('koa-body')({ multipart: true }));
app.use(require('koa-session')({key: 'sessionId'}, app));

app.use(authapp.routes())
app.use(commonapp.routes())
app.use(websiteapp.routes())
app.use(blogapp.routes())
app.use(developapp.routes())


console.log("admin server start 4100")

app.listen(4100);