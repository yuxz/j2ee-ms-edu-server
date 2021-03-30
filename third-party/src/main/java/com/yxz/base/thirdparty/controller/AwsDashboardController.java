package com.yxz.base.thirdparty.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Controller
@RequestMapping("thirdparty")
public class AwsDashboardController {
   
	@Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;
	
   @Autowired
   AmazonS3Client s3Client;

   private String bucketLocation;

   public void DashboardController(
           @Value("${custom.bucket-name}") String bucketName,
           AmazonS3Client amazonS3Client) {
       this.bucketName = bucketName;
       this.s3Client = amazonS3Client;
   }

   @PostConstruct
   public void postConstruct() {
       this.bucketLocation = String.format("https://%s.s3.%s.amazonaws.com",
               bucketName, this.s3Client.getBucketLocation(bucketName));
   }
   
   @GetMapping("/list")
   public String getDashboardView( Model model ) {
//      ModelAndView modelAndView = new ModelAndView("dashboard");
	  System.out.println("bucket:---"+ bucketName);
	  //bucketName ="yxz-edu";
	  this.bucketLocation = String.format("https://%s.s3.%s.amazonaws.com",
              bucketName, this.s3Client.getBucketLocation(bucketName));
	  
	  model.addAttribute("message", "Spring Boot with AWS");
	  model.addAttribute("bucketName", bucketName);
	  model.addAttribute("bucketLocation", bucketLocation);
	  model.addAttribute("availableFiles", s3Client.listObjects(bucketName).getObjectSummaries());
      return "hello";
   }
   
   @GetMapping("/upload")
   public String uploadFile()  {
       
       String stringObjKeyName = "dddd";
       String fileObjKeyName = "2244.jpg";
       String fileName = "d:\\jj.jpg";

       try {       

//           // Upload a text string as a new object.
           //s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

           // Upload a file as a new object with ContentType and title specified.
           PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
           ObjectMetadata metadata = new ObjectMetadata();
//           metadata.setContentType("plain/text");
           metadata.setContentType("image/jpeg");
           metadata.setHeader("type", "jpg");
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
       return "success";
   }
   
   @GetMapping("/download")
   public String downloadFile()  {
       final String USAGE = "\n" +
               "To run this example, supply the name of an S3 bucket and object to\n" +
               "download from it.\n" +
               "\n" +
               "Ex: GetObject <bucketname> <filename>\n";

 
       String key_name = "2244.jpg";
       String fileName ="d:\\hh.jpg";

//       System.out.format("Downloading %s from S3 bucket %s...\n", key_name, bucket_name);
//       final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
       try {
           S3Object o = s3Client.getObject(bucketName, key_name);
           S3ObjectInputStream s3is = o.getObjectContent();
           FileOutputStream fos = new FileOutputStream(new File(fileName));
           byte[] read_buf = new byte[1024];
           int read_len = 0;
           while ((read_len = s3is.read(read_buf)) > 0) {
               fos.write(read_buf, 0, read_len);
           }
           s3is.close();
           fos.close();
       } catch (AmazonServiceException e) {
           System.err.println(e.getErrorMessage());
           System.exit(1);
       } catch (FileNotFoundException e) {
           System.err.println(e.getMessage());
           System.exit(1);
       } catch (IOException e) {
           System.err.println(e.getMessage());
           System.exit(1);
       }
       System.out.println("Done!");
       return "success";
   }
   
   @GetMapping("/preurl")
   public String PresignedUrlAndUploadObject() throws IOException {
//       Regions clientRegion = Regions.DEFAULT_REGION;
//       String bucketName = "*** Bucket name ***";
       String objectKey = "*** Object key ***";

       try {
//           AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                   .withCredentials(new ProfileCredentialsProvider())
//                   .withRegion(clientRegion)
//                   .build();

           // Set the pre-signed URL to expire after one hour.
           java.util.Date expiration = new java.util.Date();
           long expTimeMillis = expiration.getTime();
           expTimeMillis += 1000 * 60 * 60;
           expiration.setTime(expTimeMillis);

           // Generate the pre-signed URL.
           System.out.println("Generating pre-signed URL.");
           GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                   .withMethod(HttpMethod.PUT)
                   .withExpiration(expiration);
           URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
           System.out.println(url.getAuthority()+ "/" + url.getHost() + " /" );
           
           // Create the connection and use it to upload the new object using the pre-signed URL.
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setDoOutput(true);
           connection.setRequestMethod("PUT");
           OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
           out.write("This text uploaded as an object via presigned URL.");
           out.close();

           // Check the HTTP response code. To complete the upload and make the object available, 
           // you must interact with the connection object in some way.
           connection.getResponseCode();
           System.out.println("HTTP response code: " + connection.getResponseCode());

           // Check to make sure that the object was uploaded successfully.
           S3Object object = s3Client.getObject(bucketName, objectKey);
           System.out.println("Object " + object.getKey() + " created in bucket " + object.getBucketName());
       } catch (AmazonServiceException e) {
           // The call was transmitted successfully, but Amazon S3 couldn't process 
           // it, so it returned an error response.
           e.printStackTrace();
       } catch (SdkClientException e) {
           // Amazon S3 couldn't be contacted for a response, or the client  
           // couldn't parse the response from Amazon S3.
           e.printStackTrace();
       }
       return null;
   }
   

}