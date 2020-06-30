package com.itheima;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import com.qiniu.storage.Configuration;


public class QiNiuTest {
    @Test
    public void test1(){
        //构造一个带指定 Region 对象的配置类
        /*Configuration cfg = new Configuration(Zone.zone0());*/
        Configuration cfg= new Configuration(Zone.zone0());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "KqJTgKJxjIjY3p4Oroi0-rjy2OK2CrUUeYwpavHs";
        String secretKey = "lN-STKLqY-Xneekm4L9c7SkhuWPVcW0Npmm1w609";
        String bucket = "health-lgy";

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String uploadBytes="H:\\01-Java学习盘\\00h.jpg";

        String key = null;


        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}




