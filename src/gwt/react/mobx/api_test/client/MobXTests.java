package gwt.react.mobx.api_test.client;

import gwt.mobx.client.MobX;
import gwt.mobx.client.MobX.*;
import gwt.mobx.client.ObservableArray;
import gwt.react.client.utils.JSArray;
import gwt.react.client.utils.ObjLiteral;
import gwt.react.shared.utils.Array;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class MobXTests {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    static class DataObject extends ObjLiteral {
        public int intField;
        public String stringField;
        public boolean booleanField;
        public Array<String> arrayField;

        @JsOverlay
        public static DataObject make(int intField, String stringField, boolean booleanField, Array<String> arrayField) {
            DataObject o = new DataObject();
            o.intField = intField;
            o.stringField = stringField;
            o.booleanField = booleanField;
            o.arrayField = arrayField;

            return o;
        }
    }

    ObservableBooleanValue observedBoolean = MobX.observableValue(true);
    ObservableIntValue observedInt = MobX.observableValue(1);
    ObservableDoubleValue observedDouble = MobX.observableValue(10F);
    ObservableValue<String> observedFirstName = MobX.observableValue("Paul");
    ObservableValue<String> observedLastName = MobX.observableValue("Stockley");
    ObservableArray<String> observedArray = MobX.observable(makeArray(3));
    DataObject observedObj = MobX.observable(DataObject.make(1,"Value10", true, makeArray(2)));

    ComputedValue<String> computedFullName = MobX.computed(() -> observedFirstName.get() + " " + observedLastName.get());

    ComputedBooleanValue computedBooleanValue = MobX.computed(() -> !observedBoolean.get());

    ComputedIntValue computedIntValue = MobX.computed(() -> observedInt.get() + 1000);

    ComputedDoubleValue computedDoubleValue = MobX.computed(() -> observedDouble.get() + 1000F);

    StringBuilder logBuffer = new StringBuilder();

    public void run() {
        testObservedBoolean();
        testObservedInt();
        testObservedDouble();
        testObservedString();
        testObservedArray();
        testObservedPlainObject();

        //Test computed values
        testComputedString();
        testComputedBoolean();
        testComputedInt();
        testComputedDouble();

        testWhen();
    }

    private void testWhen() {
        DisposerFunction whenDisposer = MobX.when(() -> observedInt.get() == 1234, () -> log("observedInt=1234"));

        clearLogBuffer();
        observedInt.set(123);
        observedInt.set(1234);
        assertLogBuffer("observedInt=1234");
        whenDisposer.dispose();
    }

    private void testObservedArray() {
        String arrayVal = toString(observedArray);
        assert(arrayVal.equals("Value0,Value1,Value2"));

        observedArray.push("Value3");
        arrayVal = toString(observedArray);
        assert(arrayVal.equals("Value0,Value1,Value2,Value3"));
    }

    private void testObservedBoolean() {
        assert(observedBoolean.get()) == true;

        clearLogBuffer();
        DisposerFunction disposer = observedBoolean.observe((newValue, oldValue) -> log("new=" + newValue + ",old=" + oldValue));

        observedBoolean.set(false);

        assertLogBuffer("new=false,old=true");

        //Unregister observe callback
        disposer.dispose();
        //Make sure it isn't called when the value is mutated
        clearLogBuffer();
        observedBoolean.set(true);
        assertLogBuffer("");
    }

    private void testObservedInt() {
        assert(observedInt.get() == 1);
    }

    private void testObservedDouble() {
        assert(observedDouble.get() == 10F);
    }

    private void testObservedString() {
        assert(observedFirstName.get().equals("Paul"));
        assert(observedLastName.get().equals("Stockley"));
    }

    private void testObservedPlainObject() {
        String obj = observedObj.toString();
        assert obj.equals("{\"intField\":1,\"stringField\":\"Value10\",\"booleanField\":true,\"arrayField\":[\"Value0\",\"Value1\"]}");

        DisposerFunction autoRunCallback = MobX.autorun(() -> {
            log("stringField=" + observedObj.stringField);
            log("intField=" + observedObj.intField);
        });

        clearLogBuffer();
        //Each mutation of a referenced field will result in the autorun callback being run
        observedObj.stringField="Modified";
        observedObj.intField=99;
        observedObj.booleanField = false; //Shouldn't result in the callback being run because this field isn't referenced by the autorun

        assertLogBuffer("stringField=Modified,intField=1,stringField=Modified,intField=99");
        clearLogBuffer();

        //Use a transaction to batch up mutations, resulting in the autorun callback only being triggered once all fields are changed
        MobX.transaction(() -> {
            observedObj.stringField="ModifiedAgain";
            observedObj.intField=11;
        });

        assertLogBuffer("stringField=ModifiedAgain,intField=11");
        autoRunCallback.dispose();
    }

    private void testComputedString() {
        assert(computedFullName.get().equals("Paul Stockley"));
        observedFirstName.set("Joe");
        assert(computedFullName.get().equals("Joe Stockley"));
    }

    private void testComputedInt() {
        assert(computedIntValue.get() == 1001);
        observedInt.set(2);
        assert(computedIntValue.get() == 1002);
    }

    private void testComputedDouble() {
        assert(computedDoubleValue.get() == 1010F);
        observedDouble.set(20);
        assert(computedDoubleValue.get() == 1020F);
    }

    private void testComputedBoolean() {
        assert(computedBooleanValue.get() == false);
        observedBoolean.set(false);
        assert(computedBooleanValue.get() == true);
    }

    private void clearLogBuffer() {
        logBuffer = new StringBuilder();
    }


    private void log(String msg) {
        if (logBuffer.length() > 0) {
            logBuffer.append(",");
        }

        logBuffer.append(msg);
    }

    private void assertLogBuffer(String value) {
        String logvalue = logBuffer.toString();
        assert(logvalue.equals(value));
    }

    private Array<String> makeArray(int len) {
        Array<String> o = JSArray.create();

        for(int i = 0; i < len; i++) {
            o.push("Value" + i);
        }
        return o;
    }

    private String toString(Array<String> a) {
        return a.join(",");
    }
}
