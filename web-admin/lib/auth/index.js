const router = require('koa-router')();
const admin = require('@server/auth/router/admin');
const role=require('@server/auth/router/role');
const login=require('@server/auth/router/login');
const loadAuthDara =require('@server/auth/router/loadAuthData');
const authData =require('@server/auth/router/authData');

router.prefix('/api');
router.use(login.routes()).use(login.allowedMethods());
router.use('/auth',admin.routes()).use(admin.allowedMethods());
router.use('/auth',role.routes()).use(role.allowedMethods());
router.use('/auth',loadAuthDara.routes()).use(loadAuthDara.allowedMethods());
router.use('/auth',authData.routes()).use(authData.allowedMethods());

export default router;