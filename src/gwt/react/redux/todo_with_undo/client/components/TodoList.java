package gwt.react.redux.todo_with_undo.client.components;

import gwt.interop.utils.shared.functional.JsConsumer;
import gwt.interop.utils.shared.collections.Array;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.redux.todo_with_undo.client.reducers.TodosReducers.TodoState;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.ul;

public class TodoList {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public Array<TodoState> todos;
        public JsConsumer<Integer> onTodoClick;
    }

    public static StatelessComponent<Props> component = (props) -> {

        Array<ReactElement<?, ?>> todoEls = props.todos.map((todo, i, a) -> {
                    Todo.Props p = new Todo.Props();
                    p.key = Integer.toString(todo.id);
                    p.onClick = () -> props.onTodoClick.accept(todo.id);
                    p.text = todo.text;
                    p.completed = todo.completed;

                    return React.createElement(Todo.component, p);
                });

        return ul(null,
                    castAsReactElement(todoEls));
    };
}