import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.CreateChannelOrderRequest;
import io.github.cfgametech.beans.GetChannelTokenRequest;
import io.github.cfgametech.beans.GetChannelUserInfoRequest;
import io.github.cfgametech.exceptions.Exceptions;
import org.junit.Test;

public class SignUtilsTest {
    @Test
    public void Signature() throws IllegalAccessException, Exceptions.SDKError {
        SDK sdk = new SDK("123456");
        CreateChannelOrderRequest request = new CreateChannelOrderRequest();
        
        request.setTimestamp(123456);
        request.setNonce("123456");
        request.setSign("D0B90F2AEF1406DED6733B1F59B66749");
        
        sdk.verifySignature(request.getSign(), request);
    }
}
