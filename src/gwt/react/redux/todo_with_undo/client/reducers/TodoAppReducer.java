package gwt.react.redux.todo_with_undo.client.reducers;

import gwt.interop.utils.shared.collections.Array;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers.TodoState;
import gwt.redux.client.Action;
import gwt.redux.client.Reducer;
import gwt.redux.client.Redux;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$jsPlainObj;


public class TodoAppReducer {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class State extends JsPlainObj {
        public UndoRedoTodoList todos;
        public String visibilityFilter;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class UndoRedoTodoList extends JsPlainObj {
        public Array<TodoState> past;
        public Array<TodoState> present;
        public Array<TodoState> future;
    }

    public static Reducer<State, Action> todoApp = Redux.combineReducers(
            $jsPlainObj("todos", TodosReducers.todos,
                    "visibilityFilter", VisibilityFilterReducer.visibilityFilter));


}
