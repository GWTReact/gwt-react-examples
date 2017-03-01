package gwt.react.mobx.todo.client.components;

import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.react.client.components.Component;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.CssProps;
import gwt.react.client.proptypes.html.HtmlProps;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.li;

@JsType
public class Todo extends Component<Todo.Props, JsPlainObj> {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends BaseProps {
        public JsProcedure onClickToToggle;
        public boolean completed;
        public String text;
    }

    public Todo(Todo.Props props) {
        super(props);
    }

    public ReactElement<?, ?> render() {
        return
            li(new HtmlProps()
                   .style(new CssProps()
                        .textDecoration(props.completed ? "line-through" : "none"))
                   .onClick((e) -> props.onClickToToggle.call()),
               props.text);
    };
}
