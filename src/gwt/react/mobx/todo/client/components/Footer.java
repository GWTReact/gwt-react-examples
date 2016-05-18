package gwt.react.mobx.todo.client.components;

import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.mobx.todo.client.state.AppState.FilterStatus;

import static gwt.react.client.api.GwtReact.stringLiteral;
import static gwt.react.client.api.React.DOM.p;

class Footer {

    public static StatelessComponent<AppStateProps, BaseContext> component = MobXReact.observer((props, context) -> {
        return
            p(null,
                stringLiteral("Show: "),
                React.createElement(FilterLink.component, FilterLink.Props.make(props.appState, FilterStatus.ShowAll), "All"),
                stringLiteral(", "),
                React.createElement(FilterLink.component, FilterLink.Props.make(props.appState, FilterStatus.ShowActive), "Active"),
                stringLiteral(", "),
                React.createElement(FilterLink.component, FilterLink.Props.make(props.appState, FilterStatus.ShowCompleted), "Completed")
            );
    });
}