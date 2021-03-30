package com.yxz.base.thirdparty;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

	public String getSignatureV4(String accessSecretKey, String date, String region, String regionService,
			String signing, String stringToSign)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {

		byte[] dateKey = computeHmacSHA256(accessSecretKey, date);
		logger.debug("dateKey: {}", Hex.encodeToString(dateKey));

		byte[] dateRegionKey = computeHmacSHA256(dateKey, region);
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
		String accessSecretKey = "AWS4" + "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
		String date = "20130806";
		String region = "us-east-1";
		String regionService = "s3";
		String signing = "aws4_request";
		String stringToSign = "eyAiZXhwaXJhdGlvbiI6ICIyMDEzLTA4LTA3VDEyOjAwOjAwLjAwMFoiLA0KICAiY29uZGl0aW9ucyI6IFsNCiAgICB7ImJ1Y2tldCI6ICJleGFtcGxlYnVja2V0In0sDQogICAgWyJzdGFydHMtd2l0aCIsICIka2V5IiwgInVzZXIvdXNlcjEvIl0sDQogICAgeyJhY2wiOiAicHVibGljLXJlYWQifSwNCiAgICB7InN1Y2Nlc3NfYWN0aW9uX3JlZGlyZWN0IjogImh0dHA6Ly9leGFtcGxlYnVja2V0LnMzLmFtYXpvbmF3cy5jb20vc3VjY2Vzc2Z1bF91cGxvYWQuaHRtbCJ9LA0KICAgIFsic3RhcnRzLXdpdGgiLCAiJENvbnRlbnQtVHlwZSIsICJpbWFnZS8iXSwNCiAgICB7IngtYW16LW1ldGEtdXVpZCI6ICIxNDM2NTEyMzY1MTI3NCJ9LA0KICAgIFsic3RhcnRzLXdpdGgiLCAiJHgtYW16LW1ldGEtdGFnIiwgIiJdLA0KDQogICAgeyJ4LWFtei1jcmVkZW50aWFsIjogIkFLSUFJT1NGT0ROTjdFWEFNUExFLzIwMTMwODA2L3VzLWVhc3QtMS9zMy9hd3M0X3JlcXVlc3QifSwNCiAgICB7IngtYW16LWFsZ29yaXRobSI6ICJBV1M0LUhNQUMtU0hBMjU2In0sDQogICAgeyJ4LWFtei1kYXRlIjogIjIwMTMwODA2VDAwMDAwMFoiIH0NCiAgXQ0KfQ==";

//		logger.info("signature: {}",
				
				System.out.println("x0000000000000--------"+ getSignatureV4(accessSecretKey, date, region, regionService, signing, stringToSign));
	}

}
