package gwt.react.redux.todo_with_undo.client.actions;

import gwt.redux.client.Action;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class Actions {
    public static final String SET_VISIBILITY_FILTER = "SET_VISIBILITY_FILTER";
    public static final String ADD_TODO = "ADD_TODO";
    public static final String TOGGLE_TODO = "TOGGLE_TODO";

    static private int nextTodoId = 0;

    //Actions need to be plain object literals, hence the use of the native Object type
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    public static class VisibilityAction extends Action {
        public String filter;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    public static class TodoAction extends Action {
        public int id;
        public String text;
    }

    //Native types cannot have constructors with parameters so use static factory
    private static TodoAction makeTodoAction(String type, int id, String text) {
        TodoAction a = new TodoAction();
        a.type = type;
        a.id = id;
        a.text = text;
        return a;
    }

    public static VisibilityAction setVisibilityFilter(String filter) {

        VisibilityAction a = new VisibilityAction();
        a.type = SET_VISIBILITY_FILTER;
        a.filter = filter;

        return a;
    }

    public static TodoAction addTodo(String text) {
        return makeTodoAction(ADD_TODO, nextTodoId++, text);
    }

    public static TodoAction toggleTodo(int id) {
        return makeTodoAction(TOGGLE_TODO, id, null);
    }
}
