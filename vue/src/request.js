
//request.js
import axios from 'axios';
import JSONbig from 'json-bigint';

const JSONbigToString = JSONbig({ storeAsString: true })
axios.create({
    timeout: 50_000,
    baseURL: '',
    transformResponse: [function (data) {
        try {
            //转换
            return JSONbigToString.parse(data)

        } catch (err) {
            //转换失败就直接按原数据返回
            return data;

        }
    }]
});
