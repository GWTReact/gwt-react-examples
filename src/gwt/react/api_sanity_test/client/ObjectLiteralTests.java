package gwt.react.api_sanity_test.client;

import gwt.react.client.utils.ObjLiteral;

import static gwt.react.client.utils.ObjLiteral.$;

public class ObjectLiteralTests {
    public static void run() {

        ObjLiteral o1 = $(new ObjLiteral(), "a", 1, "b", 10, "c", 20);

        assert(o1.toString().equals("{\"a\":1,\"b\":10,\"c\":20}"));

        ObjLiteral o2 = new ObjLiteral();
        o2.set("b", 2);
        o2.set("d", 3);

        assert(o2.toString().equals("{\"b\":2,\"d\":3}"));

        ObjLiteral o3 = o1.merge(o2);

        assert(o3.toString().equals("{\"a\":1,\"b\":2,\"c\":20,\"d\":3}"));

        ObjLiteral o4 = o3.except("a","c");
        assert(o4.toString().equals("{\"b\":2,\"d\":3}"));
    }
}
