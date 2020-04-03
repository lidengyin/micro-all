package cn.hctech2006.micro.microuaa.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * OSS文件上传
 */
public class OSSUtils {
    @Autowired
    //private  static OSSConstant ossConstant;
    static String bucketName = "hcnet2006-file-apk";

    static String accessKeyId = "LTAI4Fn8YhRW2FkbpucSR5AX";

    static String accessKeySecret = "yuYPFquHRK3UHHKq3YlV0MBUaWjLdC";

    static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    //private static OSS ossClient ;
    private  static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public static String upload(File file,String apkName){
         OSS ossClient =  new OSSClientBuilder().build(endpoint,accessKeyId,
                accessKeySecret);

        System.out.println("进入OSS工具");

        if(file == null){
            return null;
        }
        String dateStr = sdf.format(new Date());


        try{
            //容器不存在就创建
            if(!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest cbr = new
                        CreateBucketRequest(bucketName);
                cbr.setCannedACL(
                        CannedAccessControlList.Private
                );
                ossClient.createBucket(cbr);
            }
            //上传文件

            InputStream is = new FileInputStream(file);
            PutObjectResult result = ossClient.putObject(bucketName,apkName,file);
            //System.out.println(result.getRequestId());
           // String uri = result.getResponse().getUri();
            // 设置这个文件地址的有效时间
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);

            String url = ossClient.generatePresignedUrl(bucketName, apkName, expiration).toString();

            System.out.println("object:"+apkName+"存入成功");
            System.out.println("上传路径："+url);
            url = url.substring(0,url.indexOf("?"));
            return url;
        }catch (OSSException oe){
            oe.printStackTrace();
        }catch (ClientException | FileNotFoundException ce){
            ce.printStackTrace();
        }finally{
            ossClient.shutdown();
        }
        return null;
    }

}
