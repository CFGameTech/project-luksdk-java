import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.GetChannelTokenRequest;
import io.github.cfgametech.beans.GetChannelUserInfoRequest;
import io.github.cfgametech.exceptions.Exceptions;
import org.junit.Test;

public class SignUtilsTest {
    @Test
    public void Signature() throws IllegalAccessException, Exceptions.SDKError {
        SDK sdk = new SDK("fa7ad21fdbe10218024f88538a86");
        GetChannelUserInfoRequest request = new GetChannelUserInfoRequest();
        
        request.setChannelId(1010997);
        request.setUserId("1234");
        request.setToken("my-token");
        request.setTimestamp(1730258525);
        request.setSign("6930C0F6AA9C1BDA722C389EBEDD1D1E");
        
        sdk.verifySignature(request.getSign(), request);
    }
}
