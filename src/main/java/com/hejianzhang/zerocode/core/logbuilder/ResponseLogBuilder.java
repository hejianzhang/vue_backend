package com.hejianzhang.zerocode.core.logbuilder;

import java.time.LocalDateTime;

public class ResponseLogBuilder {
    String relationshipId;
    LocalDateTime responseTimeStamp;
    String response;
    String exceptionMsg;
    String assertion = "{Oops! Not decided. Possibly due to non JSON content was encountered. See log for details}";


    public com.hejianzhang.zerocode.core.logbuilder.ResponseLogBuilder relationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
        return this;
    }

    public com.hejianzhang.zerocode.core.logbuilder.ResponseLogBuilder responseTimeStamp(LocalDateTime responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
        return this;
    }

    public com.hejianzhang.zerocode.core.logbuilder.ResponseLogBuilder response(String response) {
        this.response = response;
        return this;
    }

    public com.hejianzhang.zerocode.core.logbuilder.ResponseLogBuilder exceptionMessage(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
        return this;
    }

    public LocalDateTime getResponseTimeStamp() {
        return responseTimeStamp;
    }
    public String getResponse() {
		return response;
	}

    @Override
    public String toString() {
        return relationshipId +
                "\nResponse:\n" + response +
                "\n*responseTimeStamp:" + responseTimeStamp;
                //"\n\n---------> Assertion: <----------\n" + assertion;
    }

    public com.hejianzhang.zerocode.core.logbuilder.ResponseLogBuilder assertionSection(String assertionJson) {
        this.assertion = assertionJson;
        return this;
    }

    public String getAssertion() {
        return assertion;
    }

}
