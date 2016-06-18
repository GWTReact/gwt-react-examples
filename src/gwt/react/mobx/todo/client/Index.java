package gwt.react.mobx.todo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.mobx.todo.client.components.App;
import gwt.react.mobx.todo.client.components.AppStateProps;
import gwt.react.mobx.todo.client.state.AppState;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;

public class Index implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AppState appState = new AppState();
        ReactDOM.render(React.createElement(App.component, $(new AppStateProps(), "appState", appState)),
                        Document.get().getElementById("mainCont"));
    }
}
