package gwt.react.redux.todo_with_undo.client.components;

import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.AnchorProps;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.a;
import static gwt.react.client.api.React.DOM.span;

public class Link {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public boolean active;
        public JsProcedure onClick;
    }

    public static StatelessComponent<Props> component = (props) -> {
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
}