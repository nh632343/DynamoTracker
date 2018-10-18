package com.jinke.dynamodb.usertracker.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;


@ConfigurationProperties(prefix = "conf")
public class AppConfig {

    @Value("${spring.profiles.active}")
    private String active;


    @Value("${local.server.version}")
    private String serverVersion;

    @Value("${dynamo.end.point}")
    private String endPoint;

    @Value("${dynamo.region}")
    private String region;

    @Value("${aws.credentials.path}")
    private String credentialsPath;

    @Value("${aws.credentials.profile}")
    private String credentialsProfile;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCredentialsPath() {
        return credentialsPath;
    }

    public void setCredentialsPath(String credentialsPath) {
        this.credentialsPath = credentialsPath;
    }

    public String getCredentialsProfile() {
        return credentialsProfile;
    }

    public void setCredentialsProfile(String credentialsProfile) {
        this.credentialsProfile = credentialsProfile;
    }


}
