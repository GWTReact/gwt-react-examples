package gwt.react.mobx.todo.client.components;

import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.mobx.todo.client.state.AppState;
import gwt.react.mobx.todo.client.state.AppState.FilterStatus;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.mobx.todo.client.components.Link.link;

class FilterLink {
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public FilterStatus filter;
        public AppState appState;

        @JsOverlay final public static Props make(AppState appState, FilterStatus filter) {
            Props o = new Props();

            o.appState = appState;
            o.filter = filter;
            return o;
        }
    }

    private static StatelessComponent<FilterLink.Props> component = MobXReact.observer((props) -> {
        Link.Props linkProps = new Link.Props();
        linkProps.active = (props.appState.getFilter() == props.filter);
        linkProps.onClick = () -> props.appState.setFilter(props.filter);

        return link(linkProps, props.children);
    });

    static ReactElement<FilterLink.Props, ?> filterLink(FilterLink.Props props, String text) {
        return React.createElement(component, props, text);
    }
}
