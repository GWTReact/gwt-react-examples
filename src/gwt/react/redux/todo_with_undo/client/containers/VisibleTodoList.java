package gwt.react.redux.todo_with_undo.client.containers;

import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.utils.JSFunc1Arg;
import gwt.react.client.utils.ObjLiteral;
import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.react.redux.todo_with_undo.client.components.TodoList;
import gwt.react.redux.todo_with_undo.client.reducers.TodoAppReducer.State;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers;
import gwt.react.shared.utils.Array;
import gwt.redux.client.addons.react_redux.MapDispatchToPropsFn;
import gwt.redux.client.addons.react_redux.MapStateToPropsFn;
import gwt.redux.client.addons.react_redux.ReactRedux;

import static gwt.react.client.utils.ObjLiteral.$;

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
            $(new ObjLiteral(), "todos", getVisibleTodos(state.todos.present, state.visibilityFilter));

    private static MapDispatchToPropsFn<TodoList.Props> mapDispatchToProps = (dispatch, ownProps) -> {
        JSFunc1Arg<Integer> onClick = (id) -> dispatch.forward(Actions.toggleTodo(id));

        return $(new ObjLiteral(), "onTodoClick", onClick);
    };

    public static StatelessComponent<TodoList.Props, BaseContext> component =
            ReactRedux.connect(mapStateToPropsFn, mapDispatchToProps).toComponent(TodoList.component);
}