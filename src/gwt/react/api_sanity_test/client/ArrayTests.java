package gwt.react.api_sanity_test.client;

import gwt.react.client.utils.JSArray;
import gwt.react.shared.utils.Array;

class ArrayTests {

    public static void run() {
        //TODO convert to independent JUnit tests
        Array<String> a = JSArray.create();

        a.push("Val1");
        a.push("Val2");

        String v = a.get(0);
        assert(v.equals("Val1"));

        a.set(1, "Modified");
        assert(a.get(1).equals("Modified"));

        assert(a.getLength() == 2);

        a.setLength(1);
        assert(a.getLength() == 1);

        /* concat */
        a = JSArray.create("AValue1");
        Array<String> b = JSArray.create("BValue2","BValue3");
        Array<String> c = a.concat(b);
        assert(toString(c).equals("AValue1,BValue2,BValue3"));

        /* concatValue */
        a = JSArray.create("AValue1");
        b = a.concatValue("BValue1");
        assert(toString(b).equals("AValue1,BValue1"));

        /* every */
        a = JSArray.create("AValue","AValue");
        assert(a.every((current) -> current.equals("AValue")));
        assert(a.every((current, index, array) -> current.equals("AValue")));

        /* filter */
        a = JSArray.create("AValue","AValue","NotAValue");

        b = a.filter((current) -> current.equals("NotAValue"));
        assert(toString(b).equals("NotAValue"));

        b = a.filter((current, index, array) -> current.equals("NotAValue"));
        assert(toString(b).equals("NotAValue"));

        /* forEach */
        a = JSArray.create("a","b","c");
        StringBuilder sb = new StringBuilder();
        a.forEach((current, index, array) -> sb.append(current));
        assert(sb.toString().equals("abc"));

        /* indexOf */
        a = JSArray.create("a","b","c","b");
        assert(a.indexOf("b") == 1);
        assert(a.indexOf("b", 2) == 3);

        /* lastIndexOf */
        a = JSArray.create("a","b","c","b","b");
        assert(a.lastIndexOf("b") == 4);
        assert(a.lastIndexOf("b", 3) == 3);

        /* reverse */
        a = JSArray.create("a","b","c");
        b = a.reverse();
        assert(toString(b).equals("c,b,a"));

        /* sort */
        a = JSArray.create("c","b","a");
        b = a.sort();
        assert(toString(b).equals("a,b,c"));

        a = JSArray.create("c","b","a");
        b = a.sort((o1, o2) -> o2.compareTo(o1));
        assert(toString(b).equals("c,b,a"));

        /* slice */
        a = JSArray.create("a","b","c","d");
        b = a.slice(1,3);
        assert(toString(b).equals("b,c"));

        /* some */
        a = JSArray.create("a","b","c","d");
        assert(a.some((current) -> current.equals("b")));

        /* splice */
        a = JSArray.create("a","b","c","d");
        b = a.splice(2);
        assert(toString(a).equals("a,b"));
        assert(toString(b).equals("c,d"));

        a = JSArray.create("a","b","c","d");
        b = a.splice(1, 2);

        assert(toString(a).equals("a,d"));
        assert(toString(b).equals("b,c"));

        a = JSArray.create("a","b","c","d");
        b = a.splice(1, 2,"e","f");
        assert(toString(a).equals("a,e,f,d"));
        assert(toString(b).equals("b,c"));

        /* shift */
        a = JSArray.create("a","b","c");
        assert(a.shift().equals("a"));
        assert(toString(a).equals("b,c"));

        /* unshift */
        a = JSArray.create("a","b","c");
        a.unshift("d");
        assert(toString(a).equals("d,a,b,c"));

        /* map */
        Array<Integer> inta = JSArray.create(1,2,3);

        b = inta.map(Object::toString);
        assert(toString(b).equals("1,2,3"));

        b = inta.map((current, index, array) -> current.toString());
        assert(toString(b).equals("1,2,3"));

        /* reduce */
        inta = JSArray.create(1,2,3);

        double accum = inta.reduce((prev, current) -> prev + current, 0);
        assert(accum == 6);

        accum = inta.reduce((prev, current) -> prev + current, Integer.valueOf(0));
        assert(accum == 6);

        accum = inta.reduce((prev, current, index, array) -> prev + current, 0);
        assert(accum == 6);

        /* reduceRight */
        inta = JSArray.create(1,2,3,4);

        accum = inta.reduceRight((prev, current) -> current - prev, 0);
        assert(accum == -2);

        accum = inta.reduceRight((prev, current) -> current - prev, Integer.valueOf(0));
        assert(accum == -2);

        accum = inta.reduceRight((prev, current, index, array) -> current - prev, 0);
        assert(accum == -2);
    }

    private static String toString(Array<String> a) {
        return a.join(",");
    }
}
