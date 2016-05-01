package gwt.react.redux.todo_with_undo.client.reducers;

import gwt.react.client.utils.ObjLiteral;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers.TodoState;
import gwt.react.shared.utils.Array;
import gwt.redux.client.Action;
import gwt.redux.client.Reducer;
import gwt.redux.client.Redux;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.utils.ObjLiteral.$;

public class TodoAppReducer {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class State extends ObjLiteral {
        public UndoRedoTodoList todos;
        public String visibilityFilter;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class UndoRedoTodoList extends ObjLiteral {
        public Array<TodoState> past;
        public Array<TodoState> present;
        public Array<TodoState> future;
    }

    public static Reducer<State, Action> todoApp = Redux.combineReducers(
            $(new ObjLiteral(),
                    "todos", TodosReducers.todos,
                    "visibilityFilter", VisibilityFilterReducer.visibilityFilter));
}
