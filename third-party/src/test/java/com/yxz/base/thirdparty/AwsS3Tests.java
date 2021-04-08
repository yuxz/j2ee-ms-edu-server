package com.yxz.base.thirdparty;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Hex;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@SpringBootTest
public class AwsS3Tests {
	
	private static final Logger logger = LoggerFactory.getLogger(AwsS3Tests.class);

	@Test
	public void uploadObject() {
		Regions clientRegion = Regions.DEFAULT_REGION;
		String bucketName = "yxz-edu";
		String stringObjKeyName = "*** String object key name ***";
		String fileObjKeyName = "*** File object key name ***";
		String fileName = "*** Path to file to upload ***";

		try {
			// This code expects that you have AWS credentials set up per:
			// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();

			// Upload a text string as a new object.
			s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

			// Upload a file as a new object with ContentType and title specified.
			PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType("plain/text");
			metadata.addUserMetadata("title", "someTitle");
			request.setMetadata(metadata);
			s3Client.putObject(request);
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}

	public byte[] computeHmacSHA256(byte[] key, String data)
			throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		String algorithm = "HmacSHA256";
		String charsetName = "UTF-8";

		Mac sha256_HMAC = Mac.getInstance(algorithm);
		SecretKeySpec secret_key = new SecretKeySpec(key, algorithm);
		sha256_HMAC.init(secret_key);

		return sha256_HMAC.doFinal(data.getBytes(charsetName));
	}

	public byte[] computeHmacSHA256(String key, String data)
			throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		return computeHmacSHA256(key.getBytes(), data);
	}

	public String getSignatureV4(String accessSecretKey, String expiration, String region, String regionService,
			String signing, String stringToSign)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {

		byte[] expirationKey = computeHmacSHA256(accessSecretKey, expiration);
		logger.debug("expirationKey: {}", Hex.encodeToString(expirationKey));

		byte[] dateRegionKey = computeHmacSHA256(expirationKey, region);
		logger.debug("dateRegionKey: {}", Hex.encodeToString(dateRegionKey));

		byte[] dateRegionServiceKey = computeHmacSHA256(dateRegionKey, regionService);
		logger.debug("dateRegionServiceKey: {}", Hex.encodeToString(dateRegionServiceKey));

		byte[] signingKey = computeHmacSHA256(dateRegionServiceKey, signing);
		logger.debug("signingKey: {}", Hex.encodeToString(signingKey));

		byte[] signature = computeHmacSHA256(signingKey, stringToSign);
		logger.debug("signature: {}", Hex.encodeToString(signature));

		return Hex.encodeToString(signature);
	}

	@Test
	public void sign4() throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
		String accessSecretKey = "AWS4" + "h0wv3xJB65ZvPYTdpu/9Qvs+SeDz166qI9o7Stqm";
		String expiration = "20210331";
		String region = "ap-southeast-2";
		String regionService = "s3";
		String signing = "aws4_request";
		String stringToSign = "eyAiZXhwaXJhdGlvbiI6ICIyMDE1LTEyLTMwVDEyOjAwOjAwLjAwMFoiLA0KICAiY29uZGl0aW9ucyI6IFsNCiAgICB7ImJ1Y2tldCI6ICJzaWd2NGV4YW1wbGVidWNrZXQifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRrZXkiLCAidXNlci91c2VyMS8iXSwNCiAgICB7ImFjbCI6ICJwdWJsaWMtcmVhZCJ9LA0KICAgIHsic3VjY2Vzc19hY3Rpb25fcmVkaXJlY3QiOiAiaHR0cDovL3NpZ3Y0ZXhhbXBsZWJ1Y2tldC5zMy5hbWF6b25hd3MuY29tL3N1Y2Nlc3NmdWxfdXBsb2FkLmh0bWwifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRDb250ZW50LVR5cGUiLCAiaW1hZ2UvIl0sDQogICAgeyJ4LWFtei1tZXRhLXV1aWQiOiAiMTQzNjUxMjM2NTEyNzQifSwNCiAgICB7IngtYW16LXNlcnZlci1zaWRlLWVuY3J5cHRpb24iOiAiQUVTMjU2In0sDQogICAgWyJzdGFydHMtd2l0aCIsICIkeC1hbXotbWV0YS10YWciLCAiIl0sDQoNCiAgICB7IngtYW16LWNyZWRlbnRpYWwiOiAiQUtJQUlPU0ZPRE5ON0VYQU1QTEUvMjAxNTEyMjkvdXMtZWFzdC0xL3MzL2F3czRfcmVxdWVzdCJ9LA0KICAgIHsieC1hbXotYWxnb3JpdGhtIjogIkFXUzQtSE1BQy1TSEEyNTYifSwNCiAgICB7IngtYW16LWRhdGUiOiAiMjAxNTEyMjlUMDAwMDAwWiIgfQ0KICBdDQp9";

//		logger.info("signature: {}",
		String policy =getSignatureV4(accessSecretKey, expiration, region, regionService, signing, stringToSign);
				
				System.out.println("x0000000000000--------"+ policy);
	}
	
	
//	--https://docs.aws.amazon.com/AmazonS3/latest/API/sigv4-post-example.html
//	--https://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html
	
	private byte[] HmacSHA256(String data, byte[] key) throws Exception {
	    String algorithm="HmacSHA256";
	    Mac mac = Mac.getInstance(algorithm);
	    mac.init(new SecretKeySpec(key, algorithm));
	    return mac.doFinal(data.getBytes("UTF-8"));
	}

	public String getSignatureKey(String key, String dateStamp, String regionName, String serviceName,String stringToSign) throws Exception {
	    byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
	    byte[] kDate = HmacSHA256(dateStamp, kSecret);
	    byte[] kRegion = HmacSHA256(regionName, kDate);
	    byte[] kService = HmacSHA256(serviceName, kRegion);
	    byte[] kSigning = HmacSHA256("aws4_request", kService);
	    byte[] signature = HmacSHA256(stringToSign,kSigning);
	    return Hex.encodeToString(signature);
	}
	
	public void getSignature() throws Exception {
		String key = "h0wv3xJB65ZvPYTdpu/9Qvs+SeDz166qI9o7Stqm"; 
		String dateStamp= Long.toString(System.currentTimeMillis()); 
		String regionName = "ap-southeast-2"; 
		String serviceName ="s3";
		String stringToSign = "eyAiZXhwaXJhdGlvbiI6ICIyMDE1LTEyLTMwVDEyOjAwOjAwLjAwMFoiLA0KICAiY29uZGl0aW9ucyI6IFsNCiAgICB7ImJ1Y2tldCI6ICJzaWd2NGV4YW1wbGVidWNrZXQifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRrZXkiLCAidXNlci91c2VyMS8iXSwNCiAgICB7ImFjbCI6ICJwdWJsaWMtcmVhZCJ9LA0KICAgIHsic3VjY2Vzc19hY3Rpb25fcmVkaXJlY3QiOiAiaHR0cDovL3NpZ3Y0ZXhhbXBsZWJ1Y2tldC5zMy5hbWF6b25hd3MuY29tL3N1Y2Nlc3NmdWxfdXBsb2FkLmh0bWwifSwNCiAgICBbInN0YXJ0cy13aXRoIiwgIiRDb250ZW50LVR5cGUiLCAiaW1hZ2UvIl0sDQogICAgeyJ4LWFtei1tZXRhLXV1aWQiOiAiMTQzNjUxMjM2NTEyNzQifSwNCiAgICB7IngtYW16LXNlcnZlci1zaWRlLWVuY3J5cHRpb24iOiAiQUVTMjU2In0sDQogICAgWyJzdGFydHMtd2l0aCIsICIkeC1hbXotbWV0YS10YWciLCAiIl0sDQoNCiAgICB7IngtYW16LWNyZWRlbnRpYWwiOiAiQUtJQUlPU0ZPRE5ON0VYQU1QTEUvMjAxNTEyMjkvdXMtZWFzdC0xL3MzL2F3czRfcmVxdWVzdCJ9LA0KICAgIHsieC1hbXotYWxnb3JpdGhtIjogIkFXUzQtSE1BQy1TSEEyNTYifSwNCiAgICB7IngtYW16LWRhdGUiOiAiMjAxNTEyMjlUMDAwMDAwWiIgfQ0KICBdDQp9";

		String signature = getSignatureKey(key, dateStamp, regionName, serviceName, stringToSign);
		
	}
	
	@Test
	public String getChinaTime() {
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(timeZone);
		System.out.println("////////////////////////////" +simpleDateFormat.format(new Date()));
		return simpleDateFormat.format(new Date());
	}
	
	

}
