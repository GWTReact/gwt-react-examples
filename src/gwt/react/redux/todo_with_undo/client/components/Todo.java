package gwt.react.redux.todo_with_undo.client.components;

import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.CssProps;
import gwt.react.client.proptypes.html.HtmlProps;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.li;

public class Todo {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public JsProcedure onClick;
        public boolean completed;
        public String text;
    }

    public static StatelessComponent<Props> component = (props) -> {
        CssProps style = new CssProps();
        style.textDecoration(props.completed ? "line-through" : "none");

        return
            li(new HtmlProps()
                    .style(style)
                    .onClick((e) -> {
                        props.onClick.call();
                    }), props.text);
    };
}
