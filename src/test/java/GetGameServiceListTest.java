import io.github.cfgametech.Response;
import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.*;
import io.github.cfgametech.exceptions.Exceptions;
import org.junit.Test;

public class GetGameServiceListTest {
    @Test
    public void GetGameServiceList() throws Exception {
        SDK sdk = new SDK("fa7ad21fdbe10218024f88538a86", "https://api.luk.live");

        Response<GetGameServiceListResponse> response = sdk.GetGameServiceList(new GetGameServiceListRequest.Builder().
                setChannelId(1010997).
                setTimestamp(System.currentTimeMillis()).
                build()
        );
        
        System.out.println(response.toString());
    }
    
    @Test
    public void IssuanceProps() throws Exception {
        SDK sdk = new SDK("fa7ad21fdbe10218024f88538a86", "https://api.luk.live");

        Response<IssuancePropsResponse> response = sdk.IssuanceProps(new IssuancePropsRequest.Builder().
                setChannelId(1010997).
                setGameId(82).
                setTimestamp(System.currentTimeMillis()).
                setData(new IssuancePropsRequest.DataBuilder().
                        add(new IssuancePropsRequestEntry.Builder().
                                setUserId("test").
                                setPropId("test").
                                setNum(1).
                                setExpire(0).
                                build()).
                        build()).
                build());
        
        System.out.println(response.toString());
    }
}
