package gwt.react.mobx.todo.client.components;

import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.mobx.todo.client.state.AppState.FilterStatus;

import static gwt.react.client.api.GwtReact.stringLiteral;
import static gwt.react.client.api.React.DOM.p;
import static gwt.react.mobx.todo.client.components.FilterLink.filterLink;

class Footer {

    private static StatelessComponent<AppStateProps> component = MobXReact.observer((AppStateProps props) ->
        p(null,
            stringLiteral("Show: "),
            filterLink(FilterLink.Props.make(props.appState, FilterStatus.ShowAll), "All"),
            stringLiteral(", "),
            filterLink(FilterLink.Props.make(props.appState, FilterStatus.ShowActive), "Active"),
            stringLiteral(", "),
            filterLink(FilterLink.Props.make(props.appState, FilterStatus.ShowCompleted), "Completed")
        )
    );

    static ReactElement<AppStateProps, ?> footer(AppStateProps props) {
        return React.createElement(component, props);
    }
}