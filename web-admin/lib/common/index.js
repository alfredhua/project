const router = require('koa-router')();

const common=require('@server/common/router/common');
router.prefix('/api');
router.use('/common',common.routes()).use(common.allowedMethods());

export default router;