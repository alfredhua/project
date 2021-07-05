const router = require('koa-router')();

const deploy=require('@server/develop/router/deploy');
router.prefix('/api');
router.use('/deploy',deploy.routes()).use(deploy.allowedMethods());

export default router;