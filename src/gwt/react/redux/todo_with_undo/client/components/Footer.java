package gwt.react.redux.todo_with_undo.client.components;

import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.redux.todo_with_undo.client.containers.FilterLink;

import static gwt.react.client.api.GwtReact.stringLiteral;
import static gwt.react.client.api.React.DOM.p;

public class Footer {
    public static StatelessComponent<BaseProps> component = (props) ->
        p(null,
            stringLiteral("Show: "),
            React.createElement(FilterLink.component, new FilterLink.Props().Filter("SHOW_ALL"), "All"),
            stringLiteral(", "),
            React.createElement(FilterLink.component, new FilterLink.Props().Filter("SHOW_ACTIVE"), "Active"),
            stringLiteral(", "),
            React.createElement(FilterLink.component, new FilterLink.Props().Filter("SHOW_COMPLETED"), "Completed")
        );
}