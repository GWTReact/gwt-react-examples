package gwt.react.redux.counter.client.components;

import com.google.gwt.core.client.Scheduler;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.api.React;
import gwt.react.client.components.ReactClassSpec;
import gwt.react.client.components.ReactClass;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.MouseEvent;
import gwt.react.client.events.MouseEventHandler;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;

import static gwt.react.client.api.GwtReact.stringLiteral;
import static gwt.react.client.api.React.DOM.button;

/**
 * This class illustrates how to work with typeless props i.e. no prop class if defined for this component
 */
public class Counter extends ReactClassSpec<BaseProps, JsPlainObj> {

    private MouseEventHandler getOnIncrementFnProp() {
        return getProps().getObj("onIncrement");
    }

    private MouseEventHandler getOnDecrementFnProp() {
        return getProps().getObj("onDecrement");
    }

    private void incrementIfOdd(MouseEvent event) {
        if (getProps().getInt("value") % 2 != 0) {
            getOnIncrementFnProp().onMouseEvent(event);
        }
    }

    private void incrementAsync(MouseEvent event) {
        Scheduler.get().scheduleFixedDelay(() -> {
            getOnIncrementFnProp().onMouseEvent(event);
            return false;
        }, 1000);
    }

    @Override
    public JsPlainObj getInitialState() {
        return null;
    }

    @Override
    public ReactElement<?, ?> render() {
        return
            React.DOM.p(null,
                stringLiteral("Clicked: " + getProps().getInt("value") + " times "),
                button(new BtnProps().onClick(getOnIncrementFnProp()), "+"),
                stringLiteral(" "),
                button(new BtnProps().onClick(getOnDecrementFnProp()), "-"),
                stringLiteral(" "),
                button(new BtnProps().onClick(this::incrementIfOdd), "Increment if odd"),
                stringLiteral(" "),
                button(new BtnProps().onClick(this::incrementAsync), "Increment async")
            );
    }

    public static ReactClass<BaseProps> component = React.createClass(new Counter());
}
