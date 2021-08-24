const path = require('path')

module.exports = {
  sassOptions: {
    includePaths: [path.join(__dirname, 'styles')],
  },
}

// module.exports = {
    // async redirects() {
    //   return [
    //     {
    //       source: '/',
    //       destination: '/website/home',
    //       permanent: false,
    //     },
    //   ]
    // },
  // }