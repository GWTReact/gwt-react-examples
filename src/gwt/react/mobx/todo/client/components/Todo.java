package gwt.react.mobx.todo.client.components;

import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.CssProps;
import gwt.react.client.proptypes.html.HtmlProps;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.li;

public class Todo {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public JsProcedure onClickToToggle;
        public boolean completed;
        public String text;
    }

    private static StatelessComponent<Props, BaseContext> component = MobXReact.observer((props, context) -> {
        return
            li(new HtmlProps()
                   .style(new CssProps()
                        .textDecoration(props.completed ? "line-through" : "none"))
                   .onClick((e) -> props.onClickToToggle.call()),
               props.text);
    });

    static ReactElement<Props, ?> todo(Props props) {
        return React.createElement(component, props);
    }
}
