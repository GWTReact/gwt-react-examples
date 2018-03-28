package gwt.react.api_sanity_test.client;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLInputElement;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.components.lifecycle.*;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.FormEvent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.InputProps;
import jsinterop.annotations.*;

import static gwt.react.client.api.React.DOM.*;

/**
 * This shows the optional use of lifecycle interfaces. They aren't strictly necessary, however,
 * they help with type checking
 */
@JsType
class StatefulExample extends Component<StatefulExample.Props, StatefulExample.State> implements
        ComponentWillMount,
        ComponentDidMount,
        ComponentWillReceiveProps<StatefulExample.Props>,
        ShouldComponentUpdate<StatefulExample.Props, StatefulExample.State>,
        ComponentDidUpdate<StatefulExample.Props, StatefulExample.State>,
        ComponentWillUnmount {

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
        HTMLInputElement e = (HTMLInputElement) event.target;
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

    //Optional lifecycle methods

    public void componentWillMount() {
        DomGlobal.alert("componentWillMount called");
    }

    public void componentDidMount() {
	    DomGlobal.alert("componentDidMount called");
    }

    public void componentWillReceiveProps(Props nextProps) {
	    DomGlobal.alert("componentWillReceiveProps called (nextProps "+ nextProps.toJSONString() + ")");
    }

    public boolean shouldComponentUpdate(Props nextProps, State nextState) {
	    DomGlobal.alert("shouldComponentUpdate called (nextProps "+ nextProps.toJSONString() + " nextState " + nextState.toJSONString() + ")");
        return true;
    }

    public void componentWillUpdate(Props nextProps, State nextState) {
	    DomGlobal.alert("componentWillUpdate called  (nextProps "+ nextProps.toJSONString() + " nextState " + nextState.toJSONString() + ")");
    }

    public void componentDidUpdate(Props prevProps, State prevState) {
	    DomGlobal.alert("componentDidUpdate called (prevProps "+ prevProps.toJSONString() + " prevState " + prevState.toJSONString() + ")");
    }

    public void componentWillUnmount() {
	    DomGlobal.alert("componentWillUnmount called");
    }

    private String getDescription() {
        return "Click Me (state=" + state.aStateVar + ", props=" + props.aProp + ")";
    }
}
