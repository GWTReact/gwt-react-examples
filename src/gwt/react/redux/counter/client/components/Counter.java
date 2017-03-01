package gwt.react.redux.counter.client.components;

import com.google.gwt.core.client.Scheduler;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.MouseEvent;
import gwt.react.client.events.MouseEventHandler;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import jsinterop.annotations.JsType;

import static gwt.react.client.api.GwtReact.stringLiteral;
import static gwt.react.client.api.React.DOM.button;
import static gwt.react.client.api.React.DOM.p;

/**
 * This class illustrates how to work with typeless props i.e. no prop class is defined for this component
 */
@JsType
public class Counter extends Component<BaseProps, JsPlainObj> {

    public Counter(BaseProps props) {
        super(props);
        state = null;
    }

    private MouseEventHandler getOnIncrementFnProp() {
        return props.getObj("onIncrement");
    }

    private MouseEventHandler getOnDecrementFnProp() {
        return props.getObj("onDecrement");
    }

    private void incrementIfOdd(MouseEvent event) {
        if (props.getInt("value") % 2 != 0) {
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
    public ReactElement<?, ?> render() {
        return
            p(null,
                stringLiteral("Clicked: " + props.getInt("value") + " times "),
                button(new BtnProps().onClick(getOnIncrementFnProp()), "+"),
                stringLiteral(" "),
                button(new BtnProps().onClick(getOnDecrementFnProp()), "-"),
                stringLiteral(" "),
                button(new BtnProps().onClick(this::incrementIfOdd), "Increment if odd"),
                stringLiteral(" "),
                button(new BtnProps().onClick(this::incrementAsync), "Increment async")
            );
    }
}
