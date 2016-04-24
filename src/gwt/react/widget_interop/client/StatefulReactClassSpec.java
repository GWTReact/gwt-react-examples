package gwt.react.widget_interop.client;

import com.google.gwt.dom.client.InputElement;
import gwt.react.client.components.ReactClassSpec;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.FormEvent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.utils.ObjLiteral;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.React.DOM.*;

@SuppressWarnings("unused")
@JsType
class StatefulReactClassSpec extends ReactClassSpec<StatefulReactClassSpec.Props, StatefulReactClassSpec.State> {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    static class Props extends BaseProps {
        String aProp;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    static class State extends ObjLiteral {
        String aStateVar;

        @JsOverlay
        static State make(String aStateVar) {
            State o = new State();
            o.aStateVar = aStateVar;
            return o;
        }
    }

    public State getInitialState() {
        return State.make("Initial Value");
    }

    private void doChange(FormEvent event) {
        InputElement e = InputElement.as(event.target);
        String val = e.getValue();
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
                    .placeHolder("What needs to be done?")
                    .value(getState().aStateVar)
                    .onChange(this::doChange))
            );
    }

    private String getDescription() {
        return "Click Me (state=" + getState().aStateVar + ", props=" + getProps().aProp + ")";
    }
}
