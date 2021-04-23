package com.yxz.base.thirdparty.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class AwsS3Component {
	
	@Value("${cloud.aws.region.static}")
    private String regionName;
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKeyID;
	@Value("${cloud.aws.credentials.secret-key}")
    private String secretAccessKey;
	
	@Value("${cloud.aws.s3.service-name}")
    private String serviceName;
	
	@Value("${cloud.aws.s3.bucket-name}")
	private String bucketName;	
	
	private String zmzDate; 
	private String dateStamp;
	
	private String stringToSign = "eyAiZXhwaXJhdGlvbiI6ICIyMDE1LTEyLTMwVDEyOjAwOjAwLjAwMFoiLA0KICAiY29uZGl0aW9ucyI6IFsNCiAgICB7ImJ1Y2tldCI6ICJzaWd2NGV4YW1wbGVidWNrZXQifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRrZXkiLCAidXNlci91c2VyMS8iXSwNCiAgICB7ImFjbCI6ICJwdWJsaWMtcmVhZCJ9LA0KICAgIHsic3VjY2Vzc19hY3Rpb25fcmVkaXJlY3QiOiAiaHR0cDovL3NpZ3Y0ZXhhbXBsZWJ1Y2tldC5zMy5hbWF6b25hd3MuY29tL3N1Y2Nlc3NmdWxfdXBsb2FkLmh0bWwifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRDb250ZW50LVR5cGUiLCAiaW1hZ2UvIl0sDQogICAgeyJ4LWFtei1tZXRhLXV1aWQiOiAiMTQzNjUxMjM2NTEyNzQifSwNCiAgICB7IngtYW16LXNlcnZlci1zaWRlLWVuY3J5cHRpb24iOiAiQUVTMjU2In0sDQogICAgWyJzdGFydHMtd2l0aCIsICIkeC1hbXotbWV0YS10YWciLCAiIl0sDQoNCiAgICB7IngtYW16LWNyZWRlbnRpYWwiOiAiQUtJQUlPU0ZPRE5ON0VYQU1QTEUvMjAxNTEyMjkvdXMtZWFzdC0xL3MzL2F3czRfcmVxdWVzdCJ9LA0KICAgIHsieC1hbXotYWxnb3JpdGhtIjogIkFXUzQtSE1BQy1TSEEyNTYifSwNCiAgICB7IngtYW16LWRhdGUiOiAiMjAxNTEyMjlUMDAwMDAwWiIgfQ0KICBdDQp9";

    
	
	 private String getTimeStamp() {
	       DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
	       dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+11"));//server timezone
	       return dateFormat.format(new Date());     
	   }
	   private String getDate() {
	       DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	       dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+11"));//server timezone
	       return dateFormat.format(new Date());       
	   }
	   
	   private byte[] HmacSHA256(String data, byte[] key) throws Exception {
		    String algorithm="HmacSHA256";
		    Mac mac = Mac.getInstance(algorithm);
		    mac.init(new SecretKeySpec(key, algorithm));
		    return mac.doFinal(data.getBytes("UTF-8"));
		}

		private String getSignatureKey(String key, String dateStamp, String regionName, String serviceName,String stringToSign) throws Exception {
		    byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
		    byte[] kDate = HmacSHA256(dateStamp, kSecret);
		    byte[] kRegion = HmacSHA256(regionName, kDate);
		    byte[] kService = HmacSHA256(serviceName, kRegion);
		    byte[] kSigning = HmacSHA256("aws4_request", kService);
		    byte[] signature = HmacSHA256(stringToSign,kSigning);
		    return Hex.encodeToString(signature);
		}
		
 	
 	public String getSignature() throws Exception {
 		
 		zmzDate = getTimeStamp(); 
		dateStamp = getDate(); //"20151229";
		
		
		String signature = getSignatureKey(secretAccessKey, dateStamp, regionName, serviceName, stringToSign);
//		HashMap<String, String> hashMap = new HashMap<>();
//		hashMap.put("signature",signature);		
////		hashMap.put("zmzDate", zmzDate);
////		hashMap.put("dateStamp", dateStamp);
//		hashMap.put("signature", signature);
		
		return signature;			
 	}
}
