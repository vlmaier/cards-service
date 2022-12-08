package com.vmaier.marvel.snap.cards.service

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Service
class S3Service {

    @Value("\${s3.endpoint-url}")
    val endpointUrl: String = ""

    @Value("\${s3.region}")
    val region: String = ""

    @Value("\${s3.bucket-name}")
    val bucketName: String = ""

    @Value("\${s3.access-key}")
    val accessKey: String = ""

    @Value("\${s3.secret-key}")
    val secretKey: String = ""

    fun putS3Object(file: MultipartFile): String {
        val s3Client = initAmazonS3Client()
        val objectKey = generateFileName(file)
        s3Client.putObject(PutObjectRequest(bucketName, objectKey, convertMultiPartToFile(file)))
        return s3Client.getUrl(bucketName, objectKey).toString()
    }

    private fun initAmazonS3Client(): AmazonS3 {
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpointUrl, region))
            .withPathStyleAccessEnabled(true)
            .build()
    }

    private fun generateFileName(file: MultipartFile): String {
        return "" + Date().time + "-" + file.originalFilename!!.replace(" ", "-")
    }

    private fun convertMultiPartToFile(file: MultipartFile): File {
        val tempFile = File.createTempFile(file.originalFilename!!, ".tmp")
        val fos = FileOutputStream(tempFile)
        fos.write(file.bytes)
        fos.close()
        return tempFile
    }
}