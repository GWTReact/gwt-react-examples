package gwt.react.mobx.todo.client.components;

import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.elements.ReactElementChildren;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.AnchorProps;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.a;
import static gwt.react.client.api.React.DOM.span;

class Link {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public boolean active;
        public JsProcedure onClick;
    }

    private static StatelessComponent<Props, BaseContext> component = (props, context) -> {
        if (props.active) {
            return
                span(null,
                    castAsReactElement(props.children)
                );
        }
        return
            a(new AnchorProps()
                .href("#")
                .onClick((e) -> {
                    e.preventDefault();
                    props.onClick.call();
                    }),
                castAsReactElement(props.children)
            );
    };

    static ReactElement<Props, ?> link(Props props, ReactElementChildren children) {
        return React.createElement(component, props, castAsReactElement(children));
    }
}