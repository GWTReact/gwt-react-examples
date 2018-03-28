package gwt.react.widget_interop.client;

import elemental2.dom.HTMLInputElement;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.FormEvent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.InputProps;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.*;

@JsType
class StatefulExample extends Component<StatefulExample.Props, StatefulExample.State> {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    static class Props extends BaseProps {
        String aProp;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    static class State extends JsPlainObj {
        String aStateVar;

        @JsOverlay
        static State make(String aStateVar) {
            State o = new State();
            o.aStateVar = aStateVar;
            return o;
        }
    }

    public StatefulExample(StatefulExample.Props props) {
        super(props);
        state = State.make("Initial Value");
    }

    private void doChange(FormEvent event) {
        HTMLInputElement e = (HTMLInputElement)event.target;
        String val = e.value;
        setState(State.make(val));
    }

    public ReactElement render() {
        return
            div(null,
                button(new BtnProps()
                    .title("Some title")
                    .onClick((e) -> setState(State.make("Updated Value"))),
                    getDescription()),

                input(new InputProps()
                    .placeholder("What needs to be done?")
                    .value(state.aStateVar)
                    .onChange(this::doChange))
            );
    }

    private String getDescription() {
        return "Click Me (state=" + state.aStateVar + ", props=" + props.aProp + ")";
    }
}
