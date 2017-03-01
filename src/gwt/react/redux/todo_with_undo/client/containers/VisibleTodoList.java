package gwt.react.redux.todo_with_undo.client.containers;

import gwt.interop.utils.shared.functional.JsConsumer;
import gwt.interop.utils.shared.collections.Array;
import gwt.react.client.components.StatelessComponent;
import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.react.redux.todo_with_undo.client.components.TodoList;
import gwt.react.redux.todo_with_undo.client.reducers.TodoAppReducer.State;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers;
import gwt.redux.client.addons.react_redux.MapDispatchToPropsFn;
import gwt.redux.client.addons.react_redux.MapStateToPropsFn;
import gwt.redux.client.addons.react_redux.ReactRedux;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$jsPlainObj;


public class VisibleTodoList {

    private static Array<TodosReducers.TodoState> getVisibleTodos(Array<TodosReducers.TodoState> todos, String filter) {
        switch (filter) {
            case "SHOW_COMPLETED":
                todos = todos.filter((todo, i, a) -> todo.completed);
                break;
            case "SHOW_ACTIVE":
                todos = todos.filter((todo, i, a) -> !todo.completed);
        }
        return todos;
    }

    private static MapStateToPropsFn<State, TodoList.Props> mapStateToPropsFn = (state, ownProps) ->
            $jsPlainObj("todos", getVisibleTodos(state.todos.present, state.visibilityFilter));

    private static MapDispatchToPropsFn<TodoList.Props> mapDispatchToProps = (dispatch, ownProps) -> {
        JsConsumer<Integer> onClick = (id) -> dispatch.forward(Actions.toggleTodo(id));

        return $jsPlainObj("onTodoClick", onClick);
    };

    public static StatelessComponent<TodoList.Props> component =
            ReactRedux.connect(mapStateToPropsFn, mapDispatchToProps).toComponent(TodoList.component);
}