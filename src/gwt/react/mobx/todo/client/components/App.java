package gwt.react.mobx.todo.client.components;

import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.ReactClass;
import gwt.react.client.components.ReactClassSpec;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;

import static gwt.react.client.api.React.DOM.div;

public class App extends ReactClassSpec<AppStateProps, BaseContext> {

    @Override
    public BaseContext getInitialState() {
        return null;
    }

    @Override
    public ReactElement render() {
        return
            div(null,
                React.createElement(AddTodo.component, getProps()),
                React.createElement(TodoList.component, getProps()),
                React.createElement(Footer.component, getProps())
            );
    }

    public static ReactClass<AppStateProps> component = MobXReact.observer(React.createClass(new App()));
}
