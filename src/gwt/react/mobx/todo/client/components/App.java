package gwt.react.mobx.todo.client.components;

import gwt.react.client.components.Component;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.div;
import static gwt.react.mobx.todo.client.components.AddTodo.addTodo;
import static gwt.react.mobx.todo.client.components.Footer.footer;
import static gwt.react.mobx.todo.client.components.TodoList.todoList;

@JsType
public class App extends Component<AppStateProps, BaseContext> {

    public App(AppStateProps props) {
        super(props);
        state = null;
    }

    @Override
    public ReactElement<?, ?> render() {
        return
            div(null,
                addTodo(props),
                todoList(props),
                footer(props)
            );
    }
}
