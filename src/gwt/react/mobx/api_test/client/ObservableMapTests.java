package gwt.react.mobx.api_test.client;

import gwt.interop.utils.client.JSON;
import gwt.interop.utils.shared.collections.Array;
import gwt.interop.utils.shared.collections.StringMap;
import gwt.mobx.client.MobX;
import gwt.mobx.client.MobX.DisposerFunction;
import gwt.mobx.client.ObservableMap;

public class ObservableMapTests {

    public void run() {
        ObservableMap<String> testMap = MobX.map();

        testMap.set("key1","value1");
        testMap.set("key2","value2");

        assert(testMap.has("key1"));
        assert(!testMap.has("key3"));

        assert(testMap.get("key2").equals("value2"));

        assert(testMap.size() == 2);

        testMap.delete("key1");
        assert(testMap.get("key1") == null);

        testMap.set("key1", "value1.1");

        String keys = toString(testMap.keys());
        assert(keys.equals("key2,key1"));

        String values = toString(testMap.values());
        assert(values.equals("value2,value1.1"));

        testMap.clear();
        assert(testMap.size() == 0);

        testMap.set("k1","v1");
        testMap.set("k2","v2");

        final StringBuilder testValues = new StringBuilder();
        testMap.forEach((v, k, m) -> testValues.append(v));

        assert(testValues.toString().equals("v1v2"));

        final StringBuilder testObserve = new StringBuilder();

        DisposerFunction disposer = testMap.observe((info)-> {
            testObserve.append(info.name);
            testObserve.append(",");
            testObserve.append(info.object == testMap);
            testObserve.append(",");
            testObserve.append(info.oldValue);
            testObserve.append(",");
            testObserve.append(info.type);
            testObserve.append(" ");
        });

        testMap.set("k3","v3");
        testMap.delete("k3");
        testMap.set("k2","v2.1");

        assert(testObserve.toString().equals("k3,true,undefined,add k3,true,v3,delete k2,true,v2,update "));
        disposer.dispose();

        StringMap<String> jsMap = testMap.toJS();
        String s = JSON.stringify(jsMap);
        assert(s.equals("{\"k1\":\"v1\",\"k2\":\"v2.1\"}"));
    }

    private static String toString(Array<String> a) {
        return a.join(",");
    }
}
