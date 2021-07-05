const router = require('koa-router')();

const article=require('@server/blog/router/article');
const type=require('@server/blog/router/type');
router.prefix('/api');
router.use('/blog',article.routes()).use(article.allowedMethods());
router.use('/blog',type.routes()).use(type.allowedMethods());

export default router;