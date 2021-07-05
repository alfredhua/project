import Koa from 'koa';
const next = require('next');
const dev = process.env.NODE_ENV !== "prod";

const app = next({ dev });
const handle = app.getRequestHandler();

app.prepare().then(() => {
    const server = new Koa();

    server.use(require('koa-logger')());
    server.use(require('koa-body')({ multipart: true }));
    server.use(require('koa-session')({key: 'sessionId'}, server));

    //中间件的使用
    server.use(async (context, next) => {
        await handle(context.req, context.res);
        context.respond = false;
    });
    
    server.listen(5000, () => {
        console.log(process.env.NODE_ENV, " site server start port 5000 .... ")
    })


});
