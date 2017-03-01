package gwt.react.redux.todo_with_undo.client.components;

import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.redux.todo_with_undo.client.containers.AddTodo;
import gwt.react.redux.todo_with_undo.client.containers.UndoRedo;
import gwt.react.redux.todo_with_undo.client.containers.VisibleTodoList;

import static gwt.react.client.api.React.DOM.div;

public class App {

    public static StatelessComponent<BaseProps> component = (props) ->
        div(null,
            React.createElement(AddTodo.component, null),
            React.createElement(VisibleTodoList.component, null),
            React.createElement(Footer.component, null),
            React.createElement(UndoRedo.component, null)
        );
}
