const { override, fixBabelImports,addWebpackAlias,addLessLoader } = require('customize-cra');
const path = require('path');

const rewiredMap = () => config => {
  return config;
};

module.exports = override(
  rewiredMap(),
  fixBabelImports('import', {
   libraryName: 'antd',
   libraryDirectory: 'es',
   style: true,
 }),
  addLessLoader({
     javascriptEnabled: true,
     modifyVars: { '@primary-color': '#1DA57A' },
   }),
 addWebpackAlias({
   ["@server"]: path.resolve(__dirname, './lib'),
   ["common"]: path.resolve(__dirname, './src/common'),
   ["auth"]: path.resolve(__dirname, './src/auth'),
   ["dictionary"]: path.resolve(__dirname, './src/dictionary'),
   ["website"]: path.resolve(__dirname, './src/website'),
   ["blog"]: path.resolve(__dirname, './src/blog'),
   ["develop"]: path.resolve(__dirname, './src/develop')
 })
);