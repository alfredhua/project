const router = require('koa-router')();

const banner=require('@server/website/router/banner');
const notice=require('@server/website/router/notice');
const produce=require('@server/website/router/produce');
const setting=require('@server/website/router/setting');
const news=require('@server/website/router/news');
const partner=require('@server/website/router/partner');
const noticeType=require('@server/website/router/noticeType');

router.prefix('/api');

router.use('/website',noticeType.routes()).use(noticeType.allowedMethods());
router.use('/website',banner.routes()).use(banner.allowedMethods());
router.use('/website',notice.routes()).use(notice.allowedMethods());
router.use('/website',produce.routes()).use(produce.allowedMethods());
router.use('/website',setting.routes()).use(setting.allowedMethods());
router.use('/website',news.routes()).use(news.allowedMethods());
router.use('/website',partner.routes()).use(partner.allowedMethods());

export default router;