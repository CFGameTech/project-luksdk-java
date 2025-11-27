package io.github.cfgametech.luksdk;

import io.github.cfgametech.Response;
import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.GetChannelTokenRequest;
import io.github.cfgametech.beans.GetChannelTokenResponse;

public class TestOld {

    public static void main(String[] args) throws Exception {
        SDK sdk = new SDK("5d362b9014b62318e09de7e64eb6", "http://api.luk.live");

        GetChannelTokenRequest request = new GetChannelTokenRequest();
        request.setChannelId(1019426);
        request.setUserId("110011020");
        request.setCode("110011020");
        request.setTimestamp(1764213654);
        request.setSign("F92D0283767AAC12229747D5919E4B62");


        Response<GetChannelTokenResponse> response = sdk.getChannelToken(request, req -> new GetChannelTokenResponse.Builder().
                setToken("110011020_16bb3d30b674ff99bf57e63bf5cb79eb").
                setLeftTime(86400).
                build());

        System.out.println(request.toString());
        System.out.println(response.toString());
    }
}
