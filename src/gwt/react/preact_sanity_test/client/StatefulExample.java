package gwt.react.preact_sanity_test.client;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Window;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.PureComponent;
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
class StatefulExample extends PureComponent<StatefulExample.Props, StatefulExample.State> {

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
        this.state = State.make("Initial Value");
    }

    private void doChange(FormEvent event) {
        InputElement e = InputElement.as(event.target);
        String val = e.getValue();
        setState(State.make(val));
    }

    public ReactElement<?, ?> render() {
        return
            div(null,
                button(new BtnProps()
                    .title("Some title")
                    .onClick((e) -> setState(State.make("Updated Value"))),
                    getDescription()),

                input(new InputProps()
                    .placeHolder("What needs to be done?")
                    .value(state.aStateVar)
                    .onChange(this::doChange))
            );
    }

    //Optional lifecycle methods

    public void componentWillMount() {
        Window.alert("componentWillMount called");
    }

    public void componentDidMount() {
        Window.alert("componentDidMount called");
    }

    public void componentWillReceiveProps(Props nextProps) {
        Window.alert("componentWillReceiveProps called (nextProps "+ nextProps.toJSONString() + ")");
    }

    public boolean shouldComponentUpdate(Props nextProps, State nextState) {
        Window.alert("shouldComponentUpdate called (nextProps "+ nextProps.toJSONString() + " nextState " + nextState.toJSONString() + ")");
        return true;
    }

    public void componentWillUpdate(Props nextProps, State nextState) {
        Window.alert("componentWillUpdate called  (nextProps "+ nextProps.toJSONString() + " nextState " + nextState.toJSONString() + ")");
    }

    public void componentDidUpdate(Props prevProps, State prevState) {
        Window.alert("componentDidUpdate called (prevProps "+ prevProps.toJSONString() + " prevState " + prevState.toJSONString() + ")");
    }

    public void componentWillUnmount() {
        Window.alert("componentWillUnmount called");
    }

    private String getDescription() {
        return "Click Me (state=" + state.aStateVar + ", props=" + props.aProp + ")";
    }
}
