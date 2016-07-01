package gwt.react.mobx.todo.client.state;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * To be automatically observable, we need to define the Todo data object as a
 * plain javascript object. If we don't do this, we would have to define each field  as
 * an ObservableValue
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public class TodoDO {
    public int id;
    public String text;
    public boolean completed;

    @JsOverlay
    public static TodoDO make(int id, String text, boolean completed) {
        TodoDO t = new TodoDO();
        t.id = id;
        t.text = text;
        t.completed = completed;
        return t;
    }
}
