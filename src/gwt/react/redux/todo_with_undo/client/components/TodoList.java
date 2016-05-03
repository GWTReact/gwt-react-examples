package gwt.react.redux.todo_with_undo.client.components;

import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.utils.JSFunc1Arg;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers.TodoState;
import gwt.react.shared.utils.Array;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.ul;

public class TodoList {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public Array<TodoState> todos;
        public JSFunc1Arg<Integer> onTodoClick;
    }

    public static StatelessComponent<Props, BaseContext> component = (props, context) -> {

        Array<ReactElement> todoEls = props.todos.map((todo, i, a) -> {
                    Todo.Props p = new Todo.Props();
                    p.key = Integer.toString(todo.id);
                    p.onClick = () -> props.onTodoClick.call(todo.id);
                    p.text = todo.text;
                    p.completed = todo.completed;

                    return React.createElement(Todo.component, p);
                });

        return ul(null,
                    castAsReactElement(todoEls));
    };
}