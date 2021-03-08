//后台访问地址
const tmall_backend_url="http://localhost:8080/";
const product_url=tmall_backend_url+"tmall-protal/";

var instance = axios.create({
    baseURL: tmall_backend_url,
    timeout: 10000,
    headers: {'X-Custom-Header': 'foobar'}
  });