package gwt.react.mobx.todo.client.components;

import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.ReactClass;
import gwt.react.client.components.ReactClassSpec;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.div;
import static gwt.react.mobx.todo.client.components.AddTodo.addTodo;
import static gwt.react.mobx.todo.client.components.Footer.footer;
import static gwt.react.mobx.todo.client.components.TodoList.todoList;

@JsType
public class App extends ReactClassSpec<AppStateProps, BaseContext> {

    @Override
    public BaseContext getInitialState() {
        return null;
    }

    @Override
    public ReactElement<?, ?> render() {
        return
            div(null,
                addTodo(getProps()),
                todoList(getProps()),
                footer(getProps())
            );
    }

    public static ReactClass<AppStateProps> component = MobXReact.observer(React.createClass(new App()));
}
