import io.github.cfgametech.beans.NotifyEventRequest;
import org.junit.Test;

import static io.github.cfgametech.beans.NotifyEventRequest.TYPE_GAME_END_EVENT;

public class GetGameServiceListTest {
    @Test
    public void GetGameServiceList() throws Exception {
        NotifyEventRequest request = new NotifyEventRequest.Builder().
                setType(TYPE_GAME_END_EVENT).
                setData("{\n" +
                        "    \"op_user_id\": \"\",\n" +
                        "    \"start_unix_sec\": \"1751862454\",\n" +
                        "    \"end_unix_sec\": \"1751862487\",\n" +
                        "    \"user_results\": {\n" +
                        "        \"888828\": {\n" +
                        "            \"score\": \"0\",\n" +
                        "            \"rank\": 2,\n" +
                        "            \"status\": 2,\n" +
                        "            \"escape\": true,\n" +
                        "            \"trust\": false\n" +
                        "        },\n" +
                        "        \"88888\": {\n" +
                        "            \"score\": \"0\",\n" +
                        "            \"rank\": 1,\n" +
                        "            \"status\": 1,\n" +
                        "            \"escape\": true,\n" +
                        "            \"trust\": false\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"end_type\": 0,\n" +
                        "    \"end_ext\": \"\",\n" +
                        "    \"cost_coins\": \"0\",\n" +
                        "    \"start_ext\": \"\"\n" +
                        "}").
                build();



        System.out.println("END: " + request.parseDataAsEndGameEvent().toString());
    }
}
