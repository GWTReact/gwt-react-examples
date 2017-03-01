package gwt.react.redux.counter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.client.events.MouseEventHandler;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.redux.counter.client.components.Counter;
import gwt.react.redux.counter.client.reducers.CounterReducer;
import gwt.redux.client.Action;
import gwt.redux.client.Redux;
import gwt.redux.client.Store;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;

public class App implements EntryPoint {

    private Store<Integer> store;

    private Action makeAction(String type) {
        Action a = new Action();
        a.type = type;
        return a;
    }

    private void render() {
        MouseEventHandler onIncrement = (e) -> store.dispatch(makeAction("INCREMENT"));
        MouseEventHandler onDecrement = (e) -> store.dispatch(makeAction("DECREMENT"));

        ReactDOM.render(
                React.createElement(Counter.class,
                        $(new BaseProps(),
                            "value", store.getState(),
                            "onIncrement", onIncrement,
                            "onDecrement", onDecrement)
                ), Document.get().getElementById("root"));
    }

    @Override
    public void onModuleLoad() {
        store = Redux.createStore(CounterReducer::counter);

        render();
        store.subscribe(this::render);
    }
}